package com.boot.common.thread.TestMain;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ErrorThread {

    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static long count = 0;

    public static void main(String[] args) {
        ExecutorService ecex = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            ecex.execute(new Runnable() {
                             public void run() {
                                 try {
                                     semaphore.acquire();
                                     count++;
                                     semaphore.release();
                                 } catch (Exception e) {
                                     e.getStackTrace();
                                 }
                             }
                         }
            );
        }
        System.out.println(count);
    }

}
