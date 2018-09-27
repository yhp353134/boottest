package com.boot.controller;

import com.boot.limiter.RateLimiter;
import com.boot.limiter.RedisRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/test/")
public class TestController {

	@Value("${spring.test}")
	private String myValue;

	@Autowired
	private JedisPool jedisPool;

	@Autowired
	RedisTemplate redisTemplate;

	@RequestMapping(value = "getValue")
	@ResponseBody
	public String getValue() {
		return "接受到的参数为"+myValue;
	}

	@RequestMapping(value = "getRedisValue")
	@ResponseBody
	public String getRedisValue() {
		redisTemplate.opsForValue().set("yu", "123");
		String vl = redisTemplate.opsForValue().get("yu").toString();
		System.out.println("返回的参数为："+ vl);
		return vl;
	}

	@RateLimiter(limit = 2)
	@RequestMapping(value = "testLimitRate")
	public ModelAndView testLimitRate(HttpServletRequest request) {
		return new ModelAndView("/");
	}

	@RequestMapping(value = "testRateLimiter")
	public void testRateLimiter(HttpServletResponse response) throws IOException {
		Jedis jedis = jedisPool.getResource();
		/**每秒限制5个人，多了就返回系统繁忙**/
		String token = RedisRateLimiter.acquireTokenFromBucket(jedis, 5, Long.valueOf(1000), "yu", "hai", "ping");
		if (token == null) {
			System.out.println("超过限流，系统繁忙");
			response.sendError(500);
		}else{
			System.out.println("业务逻辑");
		}
		jedisPool.returnResource(jedis);
	}

	@RequestMapping(value = "testRateLimiter2")
	public void testRateLimiter2(HttpServletResponse response) throws IOException {
		Jedis jedis = jedisPool.getResource();
		/**每秒限制5个人，多了就返回系统繁忙**/
		String token = RedisRateLimiter.acquireTokenFromBucket(jedis, 5,  Long.valueOf(1000), "tan", "yao", "mei");
		if (token == null) {
			System.out.println("超过限流，系统繁忙");
			response.sendError(500);
		}else{
			System.out.println("业务逻辑");
		}
		RedisRateLimiter.closeJedisPool(jedis);
		// jedisPool.returnResource(jedis);
	}

}
