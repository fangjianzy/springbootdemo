package com.fangjian.threed.volatiletest;
/**  
* Volatile 无法保证原子性
*  
* @author Jimmy.Fang
* @date 2020年3月25日  新建  
*/
public class VolatileAtomicSmple {
	private static volatile int counter = 0;
	
	public static void main(String[] args) {
		for(int i=0;i<10;i++) {
			Thread thread = new Thread(()->{
				for(int j = 0;j<1000;j++) {
					counter ++;
				}
			});
			thread.start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//理论上无法得到意向值
		System.out.println(counter);
	}
}
