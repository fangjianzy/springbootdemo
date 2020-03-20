package com.fangjian.service.task;

import java.util.concurrent.CompletableFuture;

import com.fangjian.dto.UserDTO;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
public interface AbstractTask {
	
	CompletableFuture<UserDTO> findUser(long userId) throws InterruptedException;
}
