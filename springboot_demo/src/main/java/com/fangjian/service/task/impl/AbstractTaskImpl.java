package com.fangjian.service.task.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.fangjian.dto.UserDTO;
import com.fangjian.service.facade.UserQueryServiceFacade;
import com.fangjian.service.task.AbstractTask;

/**  
 * task异步实现
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
@Service
public class AbstractTaskImpl implements AbstractTask{
	
	@Autowired
	private UserQueryServiceFacade userQueryServiceFacade;
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractTaskImpl.class);
	
	public long currentTimeMillis(){
		//获得系统的时间，单位为毫秒,转换为妙
        long totalMilliSeconds = System.currentTimeMillis();
		return totalMilliSeconds;
	}
	
	/**
	 * 这里进行标注为异步任务，在执行此方法的时候，会单独开启线程来执行(并指定线程池的名字)
	 * findUser 方法被标记为Spring的 @Async 注解，表示它将在一个单独的线程上运行。该方法的返回类型是 CompleetableFuture。
	 */
	@Async("taskExecutor")
	@Override
	public CompletableFuture<UserDTO> findUser(long userId)throws InterruptedException {
		logger.info("Looking up " + userId);
		UserDTO userDto = this.userQueryServiceFacade.findUserInfo(userId);
		// Artificial delay of 3s for demonstration purposes
        Thread.sleep(3000L);
        return CompletableFuture.completedFuture(userDto);
	}
	
}
