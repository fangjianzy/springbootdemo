package com.fangjian.threed.pool;

import java.util.LinkedList;

/**
 * @description: 任务队列实现类
 * @author: 
 * @create: 
 **/

public class LinkedRunnableQueue implements RunnableQueue {

    //任务队列的最大容量，在构造时传入
    private final int limit;

    //任务队列满时的拒绝策略
    private final DenyPolicy denyPolicy;

    //存放任务的队列
    private final LinkedList<Runnable> runnableList = new LinkedList<>();

    private final ThreadPool threadPool;

    public LinkedRunnableQueue(int limit, DenyPolicy denyPolicy, ThreadPool threadPool){

        this.limit = limit;
        this.denyPolicy = denyPolicy;
        this.threadPool = threadPool;
    }


    @Override
    public void offer(Runnable runnable) {

        synchronized(runnableList){
            if (runnableList.size() >= limit){

                denyPolicy.reject(runnable, threadPool);
            }else {
                runnableList.addLast(runnable);
                runnableList.notifyAll();
            }
        }
    }

    @Override
    public Runnable take() throws InterruptedException {

        synchronized(runnableList){

            while (runnableList.isEmpty()){
                try {
                    runnableList.wait();
                } catch (InterruptedException e) {
                    throw e;
                }
            }
        }
        return runnableList.removeFirst();
    }

    @Override
    public int size() {

        synchronized (runnableList){

            return runnableList.size();
        }
    }
}