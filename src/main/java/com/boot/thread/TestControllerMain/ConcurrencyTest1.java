package com.boot.thread.TestControllerMain;

import com.boot.thread.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ThreadSafe
public class ConcurrencyTest1 {


    private static AtomicBoolean ab = new AtomicBoolean(false);
    public static void main(String[] args) throws InterruptedException {
       if (ab.compareAndSet(false, true)) {
           System.out.println("发生了:  "+ab.get());
       }
    }
}
