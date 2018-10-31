package com.boot.thread.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        /*FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
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




        ExecutorService executorService = Executors.newCachedThreadPool();
          Future future = executorService.submit(new Callable<Object>() {
              @Override
              public Object call() throws Exception {
                  Long start = System.currentTimeMillis();
                  while (true) {
                      Long current = System.currentTimeMillis();
                     if ((current - start) > 1000) {
                         return 1;
                     }
                 }
             }
         });
          try {
             Integer result = (Integer)future.get();
             System.out.println(result);
         }catch (Exception e){
             e.printStackTrace();
         }
*/
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(String::toUpperCase)
                .thenApply(r-> r+"123")
                .thenApply(r-> r+"你好")
                .thenCombine(CompletableFuture.completedFuture("789"),(r1, r2)->r1+"_"+r2)
                // .thenAccept(System.out::println()print(r);)
                .whenComplete((i,e)-> {
                    System.out.println(i);
                });
    }


}
