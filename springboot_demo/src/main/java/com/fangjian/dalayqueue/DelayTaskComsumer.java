package com.fangjian.dalayqueue;

import java.util.concurrent.BlockingQueue;

/**  
*  消费者
*  
* @author Jimmy.Fang
* @date 2020年3月23日  新建  
*/
public class DelayTaskComsumer extends Thread{
	
	private final BlockingQueue<DelayTask> queue;
	 
	public DelayTaskComsumer(BlockingQueue<DelayTask> queue) {
		this.queue = queue;
	}
 
	@Override
	public void run() {
		DelayTask task = null;
		try {
			while (true) {
				task = queue.take();
				task.execTask();
				DelayTask.taskCount.decrementAndGet();
			}
		} catch (InterruptedException e) {
			System.out.println(getName() + " finished");
		}
	}
}
