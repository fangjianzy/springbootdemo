package com.fangjian.web.userfacade;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fangjian.common.R;
import com.fangjian.dto.UserDTO;
import com.fangjian.service.task.TaskServcie;

/**  
 * 类说明   
 *  
 * @author Jimmy.Fang
 * @date 2020年3月21日  新建  
 */
@RestController
@RequestMapping("/user/task")
public class TaskController {
	
	@Autowired
    private TaskServcie task;
	
	@ResponseBody
	@GetMapping("/info")
	public R getUser(long userId) throws InterruptedException, ExecutionException{
		UserDTO userDto = this.task.findUser(userId);
		return R.ok(userDto);
	}
	
	@ResponseBody
	@GetMapping("/asyncCallback")
	public R getAsyncCallbackUser(long userId) throws InterruptedException, ExecutionException{
		UserDTO userDto = this.task.asyncCallback(userId);
		return R.ok(userDto);
	}
}
