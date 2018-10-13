package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class BoottestApplication {

	public static void main(String[] args) {

		SpringApplication.run(BoottestApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "Hello Spring Boot";
	}

}
