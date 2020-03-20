package com.fangjian.service.task;

import java.util.concurrent.ExecutionException;

import com.fangjian.dto.UserDTO;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月21日  新建  
 */
public interface TaskServcie {
	
	UserDTO findUser(long userId) throws InterruptedException, ExecutionException;

	UserDTO asyncCallback(long userId)throws InterruptedException, ExecutionException ;
}
