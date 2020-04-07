package com.fangjian.threed.bank;
/**  
* 类说明   
*  
* @author Jimmy.Fang
* @date 2020年3月25日  新建  
*/
public class PersonB extends Thread {
	Bank bank;
	
	String mode;
 
	public PersonB(Bank bank, String mode) {
		this.bank = bank;
		this.mode = mode;
	}
 
	public void run() {
		while (bank.money >= 200) {
			try {
				bank.outMoney(200, mode);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}