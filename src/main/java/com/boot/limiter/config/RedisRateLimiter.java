package com.boot.limiter.config;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.ZParams;

import java.util.List;
import java.util.UUID;

/***
 * 因为目前是值存在redis里面，所以一套key只能控制一套削峰
 * */
public class RedisRateLimiter {

        /*private static final String BUCKET = "RATE_LIMITER_BUCKET";
        private static final String BUCKET_COUNT = "RATE_LIMITER_BUCKET_COUNT";
        private static final String BUCKET_MONITOR = "RATE_LIMITER_BUCKET_MONITOR";*/

    public static String acquireTokenFromBucket(Jedis jedis, int limit, long timeout, String BUCKET, String BUCKET_COUNT, String BUCKET_MONITOR) {
        String identifier = UUID.randomUUID().toString();
        long now = System.currentTimeMillis();
        Transaction transaction = jedis.multi();  // jedis的事务
        //删除信号量
        transaction.zremrangeByScore(BUCKET_MONITOR.getBytes(), "-inf".getBytes(), String.valueOf(now - timeout).getBytes());
        ZParams params = new ZParams();
        params.weightsByDouble(1.0, 0.0);   // 每个因子必须乘以1.0  默认就是1
        transaction.zinterstore(BUCKET, params, BUCKET, BUCKET_MONITOR);

        //计数器自增
        transaction.incr(BUCKET_COUNT);
        List<Object> results = transaction.exec();
        long counter = (Long) results.get(results.size() - 1);

        transaction = jedis.multi();
        transaction.zadd(BUCKET_MONITOR, now, identifier);
        transaction.zadd(BUCKET, counter, identifier);
        transaction.zrank(BUCKET, identifier);
        results = transaction.exec();

        //获取排名，判断请求是否取得了信号量
        long rank = (Long) results.get(results.size() - 1);
        if (rank < limit) {
            return identifier;
        } else {//没有获取到信号量，清理之前放入redis 中垃圾数据
            transaction = jedis.multi();
            transaction.zrem(BUCKET_MONITOR, identifier);
            transaction.zrem(BUCKET, identifier);
            transaction.exec();
        }
        return null;
    }

    public static void closeJedisPool(Jedis jedis) {
        jedis.close();
        if (jedis.isConnected()) {
            try {
                System.out.println("退出" + jedis.toString() + ":" + jedis.quit());
                jedis.disconnect();
            } catch (Exception e) {
                System.out.println("退出失败");
                e.printStackTrace();
            }
        }
        jedis.close();
    }

}
