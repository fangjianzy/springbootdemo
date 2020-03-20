package test.com.fangjian.service;

import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fangjian.SpringBootBusinessApplication;
import com.fangjian.common.JimmyBeanUtil;
import com.fangjian.dto.UserDTO;
import com.fangjian.service.facade.UserQueryServiceFacade;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月20日  新建  
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootBusinessApplication.class)
public class UserServiceTest {
	
	@Autowired
	private UserQueryServiceFacade userQueryServiceFacade;
	
	
	@Test
	public void testFindUserInfo(){
		long userId = 12L;
		UserDTO user = this.userQueryServiceFacade.findUserInfo(userId);
		System.out.println(JimmyBeanUtil.toJson(user));
	}
	
	@Test
	public void testGetUserDataByParallel(){
		long userId = 12L;
		UserDTO user;
		try {
			user = this.userQueryServiceFacade.getUserDataByParallel(userId);
			System.out.println(JimmyBeanUtil.toJson(user));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
}
