package com.fangjian.threed.demo;

import java.util.stream.IntStream;

/**  
*  给线程命名
*  
* @author Jimmy.Fang
* @date 2020年3月24日  新建  
*/
public class NamedThreads {
    public static final String PREFIX = "NO-";

    public static void main(String[] args) {
        IntStream.range(0, 5).boxed().map(NamedThreads::createThread).forEach(Thread::start);
    }

    private static Thread createThread(final int intName){
        return new Thread(() -> System.out.println(Thread.currentThread().getName()),
                PREFIX + intName);
    }
}