package com.fangjian.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fangjian.common.R;
import com.fangjian.web.dto.UserInfo;

@RestController
public class LoginController {
	
	@CrossOrigin
	@PostMapping("/api/login")
	@ResponseBody
	public R login(@RequestBody UserInfo user) {
		System.out.println(user.getUsername()+"正在登陆.... password="+user.getPassword());
		if(user.getUsername().endsWith(user.getPassword())) {
			return R.ok(user);
		}else {
			return R.error("用户密码错误");
		}
    }
}
