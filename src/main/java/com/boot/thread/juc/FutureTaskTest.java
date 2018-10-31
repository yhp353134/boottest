package com.boot.thread.juc;

import java.util.concurrent.*;

public class FutureTaskTest {


    private static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
      /*

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
       /* CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(String::toUpperCase)
                .thenApply(r-> r+"123")
                .thenApply(r-> r+"你好")
                .thenCombine(CompletableFuture.completedFuture("789"),(r1, r2)->r1+"_"+r2)
                // .thenAccept(System.out::println()print(r);)
                .whenComplete((i,e)-> {
                    System.out.println(i);
                });*/


     /*
     run方法会阻塞
     FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("任务执行了");
                Thread.sleep(5000);
                // 里面是异步处理的逻辑
                return "done";
            }
        });
        futureTask.run();  // 这里会阻塞
        System.out.println("执行完成后");

        // 线程池使用不会阻塞
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("任务执行了");
                Thread.sleep(5000);
                System.out.println("任务执行了之后");
                // 里面是异步处理的逻辑
                return "done";
            }
        });
        executorService.submit(futureTask);
        System.out.println("执行完成了");


        */


        // 下面的方法不会阻塞
       /* Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("任务执行了");
                Thread.sleep(5000);
                System.out.println("任务执行了之后");
                // 里面是异步处理的逻辑
                return "done";
            }
        });
        System.out.println("执行完成后");*/





       // main 结束
    }


}
