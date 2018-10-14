package com.boot.thread.juc;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> result = executorService.submit(new MyCallable());

        String returnString = result.get(); //获取结果是需要阻塞的，但是在调用的时候是不会阻塞的
        System.out.println("返回结果为："+ returnString);
    }
}
