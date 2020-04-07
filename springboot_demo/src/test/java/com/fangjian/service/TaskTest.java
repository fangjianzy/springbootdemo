package com.fangjian.service;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fangjian.SpringBootBusinessApplication;
import com.fangjian.common.JimmyBeanUtil;
import com.fangjian.dto.UserDTO;
import com.fangjian.service.task.AbstractTask;

/**  
 * 使用@Async进行异步调用
 *  https://blog.csdn.net/weixin_34248258/article/details/88018956?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootBusinessApplication.class)
public class TaskTest {
	
	@Autowired
    private AbstractTask task;
	
	public long currentTimeMillis(){
		long currentTimeMillis = System.currentTimeMillis();
		return currentTimeMillis;
	}
 
    @Test
    public void testSyncTasks() throws Exception {
    	long userId = 10l;
    	CompletableFuture<UserDTO> future = this.task.findUser(userId);
    	UserDTO dto = future.get();
    	System.out.println(JimmyBeanUtil.toJson(dto));
    }
    
    @Test
    public void testAsyncCallbackTask() throws Exception {
    	long userId = 10l;
    	long start = currentTimeMillis();
        CompletableFuture<UserDTO> future1 = this.task.findUser(userId);
        CompletableFuture<UserDTO> future2 = this.task.findUser(userId);
        CompletableFuture<UserDTO> future3 = this.task.findUser(userId);
 
        // 三个任务都调用完成，退出循环等待
        while (!future1.isDone() || !future2.isDone() || !future3.isDone()) {
        	Thread.sleep(3000);
        }
        long end = currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

}
