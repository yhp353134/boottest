package com.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class BoottestApplication {

	@Autowired
    private Environment env;

	public static void main(String[] args) {

		SpringApplication.run(BoottestApplication.class, args);
	}

	@RequestMapping("/")
	@ResponseBody
	public String index() {
		return env.getProperty("profile");
	}
}
