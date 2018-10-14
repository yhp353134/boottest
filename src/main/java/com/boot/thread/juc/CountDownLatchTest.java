package com.boot.thread.juc;

import java.util.concurrent.*;

public class CountDownLatchTest {
    // 请求总数
    private static final int clientTotal = 1000;
    // 同时间的请求数
    private static final int threadTotal = 50;
    private static int count =0;

    public  static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0;i<clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        count++;
                    } catch (Exception e) {

                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        countDownLatch.await(10, TimeUnit.SECONDS);
        System.out.print(count);
        executorService.shutdown(); // 当上面没有执行完 其实是不会关闭所有的线程的
    }
}
