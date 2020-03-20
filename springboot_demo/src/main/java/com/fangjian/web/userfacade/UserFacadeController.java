package com.fangjian.web.userfacade;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fangjian.common.R;
import com.fangjian.dto.UserDTO;
import com.fangjian.service.facade.UserQueryServiceFacade;

/**  
 * 线程池异步。通过异步线程+CountDownLatch+Future实现高性能首页查询 （实际也只有一个接口, 一次性拉下所有数据, 客户端开发也简单）
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
@RestController
@RequestMapping("/user/facade")
public class UserFacadeController {
	
	@Autowired
	private UserQueryServiceFacade  userQueryServiceFacade;
	/**
	 * 普通串行方法
	 * @param userId
	 * @return
	 */
	@ResponseBody
	@GetMapping("/info")
	public R getUser(long userId){
		UserDTO userDto = this.userQueryServiceFacade.findUserInfo(userId);
		return R.ok(userDto);
	}
	/**
	 * 线程池异步。性能更高的查询（ 通过异步线程+CountDownLatch+Future实现）
	 * @param userId
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@ResponseBody
	@GetMapping("/getUserDataByParallel")
	public R getUserDataByParallel(long userId) throws InterruptedException, ExecutionException{
		UserDTO userDto = this.userQueryServiceFacade.getUserDataByParallel(userId);
		return R.ok(userDto);
	}
}
