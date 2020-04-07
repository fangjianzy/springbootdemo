package com.fangjian.dalayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**  
*  延迟队列，一般解决定时器问题，但是重启就完蛋，还是需要存在redis或者mq中 
*  https://blog.csdn.net/dkfajsldfsdfsd/article/details/88966814
* @author Jimmy.Fang
* @date 2020年3月23日  新建  
*/
public class DelayQueueExample2 {
	
	public static void main(String[] args) {
		 
		BlockingQueue<DelayTask> queue = new DelayQueue<DelayTask>();
 
		for (int i = 0; i < 10; i++) {
			try {
				queue.put(new DelayTask("work " + i, 2000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
 
		ThreadGroup g = new ThreadGroup("Consumers");
 
		for (int i = 0; i < 3; i++) {
			new Thread(g, new DelayTaskComsumer(queue)).start();
		}
 
		while (DelayTask.taskCount.get() > 0) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		g.interrupt();
		System.out.println("Main thread finished");
	}
}
