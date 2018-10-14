package com.boot.thread.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(5000);
                // 里面是异步处理的逻辑
                return "done";
            }
        });
        new Thread(futureTask).start();
        Thread.sleep(1000);
        System.out.println("处理逻辑");
        String str = futureTask.get(); // 这里会阻塞
        System.out.println(str);
    }


}
