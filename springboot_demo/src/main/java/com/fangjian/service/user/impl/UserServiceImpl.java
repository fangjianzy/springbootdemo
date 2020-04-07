package com.fangjian.service.user.impl;

import org.springframework.stereotype.Service;

import com.fangjian.dto.UserDTO;
import com.fangjian.service.user.UserService;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserDTO findByUserId(long userId) {
		try {Thread.sleep(1000L);} catch (InterruptedException e) {}
        /* mock a user*/
		UserDTO user = new UserDTO();
        user.setUserId(userId);
        user.setUserName("fangjian"+userId);
        return user;
	}

}
