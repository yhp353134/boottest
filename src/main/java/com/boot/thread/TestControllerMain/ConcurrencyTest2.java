package com.boot.thread.TestControllerMain;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class ConcurrencyTest2 {

    // 下面两个集合都不可以被修改，是线程安全的
    private final static ImmutableList<Integer> list = ImmutableList.of(1,2,3); //初始化值，支持无限多个
    private final static ImmutableSet<Integer> set = ImmutableSet.copyOf(list); // 从list里面拷贝，也可以用of初始化
    // map第一个值是key(k*)，第二个值是value(v*)
    private final static ImmutableMap<Integer, Integer> map = ImmutableMap.of(1,2,3,4);
    private final static ImmutableMap<Integer, Integer> map2 = ImmutableMap.<Integer, Integer>builder().put(1,2).put(3,4).build();
}
