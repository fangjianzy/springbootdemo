package com.fangjian.service.email.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fangjian.config.EmailConfig;
import com.fangjian.service.email.EmailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
	
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    private EmailConfig emailConfig;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailConfig.getTo());
            message.setFrom(emailConfig.getFrom());
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (MailException exception) {
            log.error("邮件发送异常");
        }
    }
}
