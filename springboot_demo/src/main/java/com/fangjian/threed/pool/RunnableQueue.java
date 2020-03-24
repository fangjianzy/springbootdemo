package com.fangjian.threed.pool;
/**
 * @description: 任务队列
 * @author: 
 * @create: 
 **/

public interface RunnableQueue {

    //当有新的任务进来时offer到队列中
    void offer(Runnable runnable);

    //工作线程通过take方法获取Runnable
    Runnable take() throws InterruptedException;

    //获取任务队列中任务的数量
    int size();
}