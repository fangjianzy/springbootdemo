package com.fangjian.threed.demo;

import java.util.concurrent.TimeUnit;

/**  
*  模拟窗口叫号系统（OK的写法）
*  
* @author Jimmy.Fang
* @date 2020年3月24日  新建  
*/
public class TicketWindowRunnable implements Runnable {

	private int index = 1;
	private boolean change = true;

	private final static int MAX = 50;

	private final static Object MUTEX = new Object();

	@Override
	public void run() {
		synchronized (MUTEX) {
			while (index <= MAX && change) {
				System.out.println(Thread.currentThread().getName() + " 的号码是： " + (index++));

				try {
//                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				change = false;
			}
			change = true;

		}
	}

	public static void main(String[] args) {
		final TicketWindowRunnable task = new TicketWindowRunnable();

		for (int i = 0; i < 10; i++) {

			Thread windowThread1 = new Thread(task, "一号窗口");
			Thread windowThread2 = new Thread(task, "二号窗口");
			Thread windowThread3 = new Thread(task, "三号窗口");
			Thread windowThread4 = new Thread(task, "四号窗口");
			Thread windowThread5 = new Thread(task, "五号窗口");

			windowThread1.start();
			windowThread2.start();
			windowThread3.start();
			windowThread4.start();
			windowThread5.start();

		}

	}
}