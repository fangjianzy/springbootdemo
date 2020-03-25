package com.fangjian.threed.bank;
/**  
* 类说明   
*  
* @author Jimmy.Fang
* @date 2020年3月25日  新建  
*/
public class PersonA extends Thread {
	 
	Bank bank;
	
	String mode;
 
	public PersonA(Bank bank, String mode) {
		this.mode = mode;
		this.bank = bank;
	}
 
	public void run (){
		while(bank.money >= 100){
			try {
				bank.outMoney(100, mode);
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
