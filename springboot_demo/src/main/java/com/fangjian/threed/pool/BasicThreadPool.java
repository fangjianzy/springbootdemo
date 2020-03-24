package com.fangjian.threed.pool;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**  
* 初始化线程池  
*  
* @author Jimmy.Fang
* @date 2020年3月24日  新建  
*/
public class BasicThreadPool extends Thread implements ThreadPool {

    //初始化线程数量
    private final int initSize;

    //线程池最大线程数
    private final int maxSize;

    //线程池核心线程数
    private final int coreSize;

    //当前活跃线程数
    private int activeCount;

    //线程工厂
    private final ThreadFactory threadFactory;

    //线程池是否已经shutdown
    private volatile boolean isShutdown = false;

    //任务队列
    private final RunnableQueue runnableQueue;

    //工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private final static DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private final static ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    //构造时需要传递的参数：初始线程的数量，最大线程数，核心线程数，任务队列的最大数量
    public BasicThreadPool(int initSize, int maxSize, int coreSize, int queueSize){

        this(initSize, maxSize, coreSize,
                DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    //构造线程池
    public BasicThreadPool(int initSize, int maxSize, int coreSize,
                           ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime, TimeUnit timeUnit){

        this.initSize = initSize;
        this.maxSize = maxSize;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy,this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {

        start();

        for (int i = 0; i < initSize; i++){
            newThread();
        }
    }

    private void newThread(){

        //创建任务线程，并启动
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();

    }

    @Override
    public void execute(Runnable runnable) {

        if (this.isShutdown){
            throw new  IllegalArgumentException("The thread pool is destory");

        }
        this.runnableQueue.offer(runnable);
    }

    private void removeThread(){

        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run(){

        //run 方法继承自thread,主要用于维护线程数量，比如扩容、回收等工作
        while (!isShutdown && !isInterrupted()){

            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }

            synchronized (this){

                if (isShutdown){

                    break;
                }

                if (runnableQueue.size() > 0 && activeCount < coreSize){

                    for (int i = initSize; i < coreSize; i++){
                        newThread();
                    }
                    continue;
                }

                if (runnableQueue.size() > 0 && activeCount < maxSize){

                    for (int i = coreSize; i < maxSize; i++){
                        newThread();
                    }
                }

                if (runnableQueue.size() == 0 && activeCount > coreSize){

                    for (int i = coreSize; i < activeCount; i++){

                        removeThread();
                    }
                }
            }
        }
    }
    @Override
    public void shutdown() {

        synchronized (this){

            if (isShutdown) return;
            isShutdown = true;
            threadQueue.forEach(threadTask -> {

                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });

            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {

        if (isShutdown){
            throw new IllegalArgumentException("The thread pool is destory.");
        }

        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown){
            throw new IllegalArgumentException("The thread pool is destory.");
        }

        return this.maxSize;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown){
            throw new IllegalArgumentException("The thread pool is destory.");
        }

        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown){
            throw new IllegalArgumentException("The thread pool is destory.");
        }

        return runnableQueue.size();
    }

    @Override
    public int getActiveCount(){

        synchronized (this){
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return this.isShutdown;
    }

    private static class DefaultThreadFactory implements ThreadFactory {

        private static final AtomicInteger GROUT_COUNTER = new AtomicInteger(1);

        private  static final ThreadGroup group = new ThreadGroup("MyThreadPool-" + GROUT_COUNTER.getAndDecrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);
        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndDecrement());
        }
    }

    private class ThreadTask {

        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask){
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }
}