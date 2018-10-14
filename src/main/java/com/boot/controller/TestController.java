package com.boot.controller;

import com.boot.thread.printRequest.RequestHolder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

@Controller
@RequestMapping("/withhold/")
public class TestController {

    private final static List<Integer> list = new CopyOnWriteArrayList<>();
    private final static Set<Integer> sets = new ConcurrentSkipListSet<>();
    private final static Map<Integer, Integer> maps = new ConcurrentSkipListMap<>();

    public static void main(String[] args) {
        DateTimeFormatter d = DateTimeFormat.forPattern("yyy-MM-dd");

    }

    @RequestMapping("getString")
    @ResponseBody
    public String getString() {
        return RequestHolder.getRequestId();  // 获取threadLocal里面的值
    }
}
