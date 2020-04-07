package com.fangjian.service.facade.impl;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fangjian.dto.PostDTO;
import com.fangjian.dto.UserDTO;
import com.fangjian.service.facade.UserQueryServiceFacade;
import com.fangjian.service.post.PostService;
import com.fangjian.service.user.UserService;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
@Service
public class UserQueryServiceFacadeImpl implements UserQueryServiceFacade{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostService postServicce;
	
	@Override
	public UserDTO findUserInfo(long userId) {
		UserDTO user = this.userService.findByUserId(userId);
		List<PostDTO> userPosts = this.postServicce.fingUserPostList(userId);
		user.setPosts(userPosts);
		return user;
	}
	/**
	 *  这几项数据之间并无强依赖性, 完全可以并行获取嘛, 通过异步线程+CountDownLatch+Future实现,就像下面这样。
	 *  将串行调用改为并行调用, 在有限并发级别下, 能极大提高性能
	 */
	@Override
	public UserDTO getUserDataByParallel(long userId)  throws InterruptedException, ExecutionException{
		ExecutorService executorService = Executors.newFixedThreadPool(2);
	    CountDownLatch countDownLatch = new CountDownLatch(2);
	    Future<UserDTO> userFuture = executorService.submit(() -> {
            try{
                return userService.findByUserId(userId);
            }finally {
                countDownLatch.countDown();
            }
        });
	    
	    Future<List<PostDTO>> postsFuture = executorService.submit(() -> {
            try{
                return postServicce.fingUserPostList(userId);
            }finally {
                countDownLatch.countDown();
            }
        });
	    countDownLatch.await();
	    UserDTO user = userFuture.get();
        user.setPosts(postsFuture.get());
        return user;
	}
	

}
