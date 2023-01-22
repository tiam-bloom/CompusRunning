package icu.running.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/*
    邮件发送
 */
@Service
public class SendEmailService {
    @Autowired(required=false)
    private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public String sendSimpleEmail(String to,String subject,String text){
        //纯文本简单邮件
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);  //收件人地址
        message.setSubject(subject);  //邮件标题
        message.setText(text);  //邮件内容
        try {
            //发送邮件
            mailSender.send(message);
            System.out.println("邮件发送成功!");
            return "邮件发送成功!";
        }catch (MailException e){
            System.out.println("发送失败"+e.getMessage());
            e.printStackTrace();
            return "发送失败!";
        }
    }
}
