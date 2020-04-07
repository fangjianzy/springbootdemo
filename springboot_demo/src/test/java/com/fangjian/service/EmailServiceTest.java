package com.fangjian.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fangjian.service.email.EmailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceTest {
	
	@Autowired
    private EmailService emailService;
    @Test
    public void testSimple(){
        emailService.sendSimpleMessage("yyjcf1986@163.com","【询盘】又有新的询盘信息产生","你好，又有新的询盘信息产生，请留意联系电话！");
    }
}
