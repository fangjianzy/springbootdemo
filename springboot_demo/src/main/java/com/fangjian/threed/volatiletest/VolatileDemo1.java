package com.fangjian.threed.volatiletest;
/**  
* 线程之间通讯-（错误做法），数据不可见
*  
* @author Jimmy.Fang
* @date 2020年3月25日  新建  
*/
public class VolatileDemo1 {
	
	private static boolean initFlag = false;
	
	public static void loadData() {
		while(!initFlag) {
			
		}
		String current = Thread.currentThread().getName();
		System.out.println("线程： "+current +"发现initFlag值的改变");
	}
	
	public static void refresh() {
		System.out.println("refresh data init");
		initFlag = true;
		System.out.println("refresh data success....");
	}
	public static void main(String[] args) {
		Thread ThreadA = new Thread(()->{
			loadData();
		});
		
		Thread ThreadB = new Thread(()->{
			refresh();
		});
		
		ThreadA.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ThreadB.start();
	}
}
