package com.fangjian.dalayqueue;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**  
* 类说明   
*  DalayQueue 是一个支持延时获取元素的无界阻塞队列,队列使用PriorityQueue来实现,队列中的元素必须实现Delayed接口<BR>
*  1、缓存系统的设计,可以保存缓存元素的有效期,一旦能从DelayQueue中获取元素,表示缓存有效期到了<BR>
         2、定时任务调度,使用DelayQueue保存当天将会执行的任务和执行时间,一旦从DelayQueue中获取到任务就开始执行<BR>
* @author Jimmy.Fang
* @date 2020年3月23日
*/
public class DelayQueueExample {
	
	static class DelayTask implements Delayed{
        private Date date;
 
        DelayTask(Date date){
            this.date = date;
        }
 
        /**
         * 根据时间单位获取该任务被执行的剩余时间，如果取出是负数，将会被DelayQueue取出
         * @param unit
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            long now = date.getTime() - new Date().getTime();
            return unit.convert(now,TimeUnit.MILLISECONDS);
        }
 
        /**
         * DelayQueue中的 priorityQueue将会根据这个方法对元素进行排序
         * @param o
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            if(this == o)
                return 0;
            long d = (getDelay(TimeUnit.NANOSECONDS) -
                    o.getDelay(TimeUnit.NANOSECONDS));
            return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
        }
    }
 
    public static  void main(String[] args) throws InterruptedException {
        DelayQueue<DelayTask> delayQueue = new DelayQueue();
        for(int i=10;i>0;i--){
            Date date = new Date(new Date().getTime()+i*1000);
            DelayTask delayTask = new DelayTask(date);
            delayQueue.put(delayTask);
        }
        while(!delayQueue.isEmpty()){
            System.out.println(delayQueue.take().date);
        }
 
    }
}
