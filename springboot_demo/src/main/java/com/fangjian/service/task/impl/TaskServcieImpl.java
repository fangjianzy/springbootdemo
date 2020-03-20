package com.fangjian.service.task.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangjian.dto.UserDTO;
import com.fangjian.service.task.AbstractTask;
import com.fangjian.service.task.TaskServcie;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月21日  新建  
 */
@Service
public class TaskServcieImpl implements TaskServcie {
	private static final Logger logger = LoggerFactory.getLogger(TaskServcieImpl.class);
	@Autowired
    private AbstractTask task;
	
	@Override
	public UserDTO findUser(long userId) throws InterruptedException, ExecutionException {
		CompletableFuture<UserDTO> future = this.task.findUser(userId);
    	UserDTO dto = future.get();
		return dto;
	}

	@Override
	public UserDTO asyncCallback(long userId) throws InterruptedException, ExecutionException {
		CompletableFuture<UserDTO> future1 = this.task.findUser(1l);
        CompletableFuture<UserDTO> future2 = this.task.findUser(2l);
        CompletableFuture<UserDTO> future3 = this.task.findUser(userId);
        // Wait until they are all done
        //join() 的作用：让“主线程”等待“子线程”结束之后才能继续运行
        CompletableFuture.allOf(future1,future2,future3).join();
        logger.info("--> " + future1.get());
        logger.info("--> " + future2.get());
        logger.info("--> " + future3.get());
		return future3.get();
	}
}
