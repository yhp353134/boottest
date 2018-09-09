package com.boot.common.thread.TestMain;

import com.boot.common.thread.annoations.NoThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Executor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NoThreadSafe
public class ConcurrencyTest {

    // 请求总数
    private static final int clientTotal = 1000;
    // 同时间的请求数
    private static final int threadTotal = 50;
    private static int count =0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i=0;i<clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        count++;
                        semaphore.release();
                    } catch (Exception e) {

                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.print(count);
    }
}
