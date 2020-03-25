package com.fangjian.jvm8;

import java.util.ArrayList;

/**  
*  CMD  jvisualvm 自带调优工具（https://www.cnblogs.com/AryaZ/p/10492275.html）
*  在命令行直接输入即可
* @author Jimmy.Fang
* @date 2020年3月25日  新建  
*/
public class JvisualvmDemo {
	
	byte[] a = new byte[2012*100]; //100k
	
	public static void main(String[] args) throws InterruptedException {
		ArrayList<JvisualvmDemo> heapTest = new ArrayList<JvisualvmDemo>();
		while(true) {
			System.out.println("向内存中添加100K内容数据，强引用，无法GC");
			heapTest.add(new JvisualvmDemo());
			Thread.sleep(10);//10毫秒
		}
	}
}
