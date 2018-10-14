package com.boot.thread.juc;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        // 里面是异步处理的逻辑
        return "done";
    }

}
