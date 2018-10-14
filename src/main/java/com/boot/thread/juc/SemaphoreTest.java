package com.boot.thread.juc;

import java.util.concurrent.*;

public class SemaphoreTest {
    // 请求总数
    private static final int clientTotal = 50;
    private static int count =0;

    public  static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5);
        for (int i=0;i<clientTotal; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // semaphore.acquire();
                        // 尝试获取许可,下面只会输出5条记录，因为只能获取5个 其他的线程丢弃掉了
                        if (semaphore.tryAcquire()) {
                            Thread.sleep(1000);
                            count++;
                            System.out.println(count);
                            semaphore.release();
                        } else {
                            System.out.println("未获取到");
                        }
                    } catch (Exception e) {

                    }
                }
            });
        }
        System.out.print(count);
        executorService.shutdown();
    }
}
