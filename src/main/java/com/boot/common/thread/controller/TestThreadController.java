package com.boot.common.thread.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/thread/test/")
@Slf4j
public class TestThreadController {

 @RequestMapping("getString")
 @ResponseBody
 public String getString() {
     return "hello";
 }

}
