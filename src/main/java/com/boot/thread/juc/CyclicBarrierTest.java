package com.boot.thread.juc;

import java.util.concurrent.*;

public class CyclicBarrierTest {

    private final static CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("回调");
        }
    });

    public  static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i=0;i<10; i++) {
            final int ints = i;
            Thread.sleep(1000);
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(ints);
                    } catch (Exception e) {

                    }
                }
            });
        }
        executorService.shutdown(); // 当上面没有执行完 其实是不会关闭所有的线程的
    }

    public static void test(int i) throws Exception {
        System.out.println("开始了"+i);
        barrier.await(2000, TimeUnit.MILLISECONDS);
        System.out.println("继续"+i);
    }
}
