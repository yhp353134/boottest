package com.boot.controller;

import com.boot.thread.printRequest.RequestHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/withhold/")
public class TestController {

    public static void main(String[] args) {

    }

    @RequestMapping("getString")
    @ResponseBody
    public String getString() {
        return RequestHolder.getRequestId();  // 获取threadLocal里面的值
    }
}
