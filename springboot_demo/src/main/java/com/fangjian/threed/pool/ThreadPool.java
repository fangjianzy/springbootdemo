package com.fangjian.threed.pool;
/**  
* 类说明   
*  
* @author Jimmy.Fang
* @date 2020年3月24日  新建  
*/
/**
 * @description: 线程池的基本方法
 * @author: 
 * @create: 
 **/

public interface ThreadPool {

    //提交任务到线程池
    void execute(Runnable runnable);

    //关闭线程池
    void shutdown();

    //获取线程池的初始大小
    int getInitSize();

    //获取线程池的最大线程数
    int getMaxSize();

    //获取线程池的核心线程数
    int getCoreSize();

    //获取线程池中用于缓存任务队列大小
    int getQueueSize();

    //获取线程池中活跃线程数
    int getActiveCount();

    //查看线程池是否已经被shutdown
    boolean isShutdown();
}
