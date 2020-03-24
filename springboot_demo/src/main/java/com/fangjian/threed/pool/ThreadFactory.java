package com.fangjian.threed.pool;
/**
 * @description: 创建线程的接口
 * @author: 
 * @create: 
 **/

@FunctionalInterface
public interface ThreadFactory {

    Thread createThread(Runnable runnable);
}
