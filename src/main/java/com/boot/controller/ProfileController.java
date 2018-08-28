package com.boot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/profileTest/")
public class ProfileController {

	@Value("${mrYu}")
	private String myValue;

	@RequestMapping(value = "getValue")
	@ResponseBody
	public String getValue() {
		return "接受到的参数为"+myValue;
	}

}
