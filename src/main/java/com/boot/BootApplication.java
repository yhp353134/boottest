package com.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {

		SpringApplication.run(BootApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return "Hello Spring Boot";
	}

}
