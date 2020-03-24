package com.fangjian.threed.pool;

import java.util.concurrent.TimeUnit;

/**  
* @description:shutdown线程池测试类
*  
* @author Jimmy.Fang
* @date 2020年3月24日  新建  
*/
public class ThreadPoolTestShutdown {
	public static void main(String[] args) throws InterruptedException {

        //定义线程池
        //初始化线程数量为2，
        //核心线程数量为4，
        //最大线程数量为6，
        //任务队列最多容纳1000个任务
        final ThreadPool threadPool = new BasicThreadPool(2, 6, 4, 1000);

        //定义20个任务并且提交到线程池
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + " is runnning and done.");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        TimeUnit.SECONDS.sleep(12);
        threadPool.shutdown();
        //Thread.currentThread().join();


        for (;;){

            System.out.println("getActiveCount: " + threadPool.getActiveCount());
            System.out.println("getQueueSize: " + threadPool.getQueueSize());
            System.out.println("getCoreSize: " + threadPool.getCoreSize());
            System.out.println("getMaxSize: " + threadPool.getMaxSize());
            System.out.println("=========================================");
            TimeUnit.SECONDS.sleep(1);
        }


    }
}
