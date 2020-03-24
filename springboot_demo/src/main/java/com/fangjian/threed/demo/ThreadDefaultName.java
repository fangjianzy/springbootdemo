package com.fangjian.threed.demo;

import java.util.stream.IntStream;

/**  
*  多线程默认命名
*  
* @author Jimmy.Fang
* @date 2020年3月24日  新建  
*/
public class ThreadDefaultName {
	 public static void main(String[] args) {
	        IntStream.range(0, 5).boxed().map(
	        		i -> new Thread(() -> System.out.println(Thread.currentThread().getName())
	        )
	        ).forEach(Thread:: start);
	 }
}
