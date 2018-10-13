package com.boot.thread.printRequest;

public class RequestHolder {

    // ThreadLocal 是本地线程变量，如果我们需要一个从开始到结束都需要的数据，那么跨域放在里面
    // 需要的时候可以随时获取，防止了参数的不停传入，直接就可以获取到，代码更优雅
    // 例如 用户的登录信息，可以在拦截器里面获取放在这里面，那么我们获取的时候就更方便了
    private final static ThreadLocal<String> requestHolder = new ThreadLocal<>();

    public static void set(Long threadId, String requestUrl) {
        System.out.println("threadId="+threadId+", requestUrl="+requestUrl);
        requestHolder.set("threadId="+threadId+", requestUrl="+requestUrl);
    }

    public static String getRequestId() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }

}
