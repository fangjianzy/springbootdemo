package com.fangjian.threed.demo;
/**  
* 排队叫号程序，还是有问题
*  
* @author Jimmy.Fang
* @date 2020年3月24日  新建  
*/
public class TicketWindowRunnableError  implements Runnable{
	private int index = 1; //不做static修饰

    private final static int MAX = 50;

    @Override
    public void run() {

        while (index <= MAX){

            System.out.println(Thread.currentThread() + " 的号码是： " + (index++));

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {

        final TicketWindowRunnable task = new TicketWindowRunnable();

        Thread windowThread1 = new Thread(task, " 一号窗口");

        Thread windowThread2 = new Thread(task, " 一号窗口");

        Thread windowThread3 = new Thread(task, " 一号窗口");

        Thread windowThread4 = new Thread(task, " 一号窗口");

        windowThread1.start();

        windowThread2.start();

        windowThread3.start();

        windowThread4.start();
    }
}
