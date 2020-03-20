package com.fangjian.service.user;

import com.fangjian.dto.UserDTO;

/**  
 * UserService接口类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
public interface UserService {
	
	public UserDTO findByUserId(long userId);
}
