package com.boot.thread.juc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 计算1加到100的运算， 可以拆分成
 */
public class ForkJoinTest extends RecursiveTask<Integer> {

    private final int scopeCount = 2;
    private int start;
    private int end;

    ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        ForkJoinPool fp = new ForkJoinPool();
        ForkJoinTest task = new ForkJoinTest(1,100);
        Future<Integer> result = fp.submit(task);
        try {
            System.out.println(result.get());
        } catch (Exception e) {

        }
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        // 如果传进来的数值很小，就没有必要去两个任务处理了
        boolean isCom = (end - start) <= scopeCount;
        if (isCom) {
            for (int i = start; i < end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinTest left = new ForkJoinTest(start, middle);
            ForkJoinTest right = new ForkJoinTest(middle + 1, end);
            left.fork(); // 执行任务
            right.fork();
            int leftNum = left.join();
            int rightNum = right.join();
            sum = leftNum + rightNum;
        }
        return sum;
    }
}
