package com.fangjian.service.facade;

import java.util.concurrent.ExecutionException;

import com.fangjian.dto.UserDTO;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
public interface UserQueryServiceFacade {
	UserDTO findUserInfo(long userId);
	UserDTO getUserDataByParallel(long userId) throws InterruptedException, ExecutionException;
}
