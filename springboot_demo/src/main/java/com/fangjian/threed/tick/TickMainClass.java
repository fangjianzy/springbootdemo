package com.fangjian.threed.tick;
/**  
* 三个售票窗口同时出售20张票
*  程序分析：
    (1)票数要使用同一个静态值
    (2)为保证不会出现卖出同一个票数，要java多线程同步锁。
设计思路：
    (1)创建一个站台类Station，继承Thread，重写run方法，在run方法里面执行售票操作！售票要使用同步锁：即有一个站台卖这张票时，其他站台要等这张票卖完！
    (2)创建主方法调用类
————————————————
版权声明：本文为CSDN博主「延陵缥缈」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_34996727/article/details/80416277
* @author Jimmy.Fang
* @date 2020年3月25日  新建  
*/
public class TickMainClass {
	/**
	   * java多线程同步锁的使用
	   * 示例：三个售票窗口同时出售10张票
	   * */
	  public static void main(String[] args) {
	    //实例化站台对象，并为每一个站台取名字
	     Station station1=new Station("窗口1");
	     Station station2=new Station("窗口2");
	     Station station3=new Station("窗口3");
	    // 让每一个站台对象各自开始工作
	     station1.start();
	     station2.start();
	     station3.start();
	  }
}
