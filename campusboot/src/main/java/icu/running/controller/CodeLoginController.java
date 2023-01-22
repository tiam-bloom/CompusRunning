package icu.running.controller;

import icu.running.pojo.User;
import icu.running.service.UserInfoService;
import icu.running.service.impl.SendEmailService;
import icu.running.service.impl.SendSmsService;
import icu.running.utils.RondomCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 验证码登录控制类
 */
@Controller
public class CodeLoginController {
    @RequestMapping("/codeLogin")
    public String codeLogin() {
        return "code_login";
    }

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SendEmailService sendEmailService;

    //获取验证码--发送邮件-传入收件人的邮箱
    @RequestMapping("/getCode")
    @ResponseBody
    public String getCode(HttpSession session, String to) {
        User user = userInfoService.findByEmail(to);
        if (user == null) {
            return "该邮箱还未注册!请先注册";
        }
        session.setAttribute("USER_SESSION", user); //应验证验证码后再存入
        String code = RondomCode.randomCode();  //系统随机生成6位验证码
        System.out.println("发送的验证码：" + code);
        session.setAttribute("RandomCode", code);  //存入session
        String subject = "嘿！靓仔";
        String text = "您的验证码是：" + code + "\n 该邮件由系统自动发出，请勿回复\n个人网站：http://www.yujing.fit";
        //+"\n 该邮件由系统自动发出，请勿回复\n个人网站：http://www.yujing.fit"
        //发送邮件,返回提示信息
        String msg = sendEmailService.sendSimpleEmail(to, subject, text);
        return msg;
    }


    @RequestMapping("/doLogin")
    public String doLogin(HttpSession session, String InputCode, Model model) {
        String code = (String) session.getAttribute("RandomCode");  //获取验证码
        //限制用户必须先输入验证码,才能提交请求
        if (!InputCode.equals(code)) {
            System.out.println("验证码有误");
            model.addAttribute("msg", "验证码有误");
            return "code_login";
        }
        return "forward:community";
    }


    /*
        短信验证码
     */
    @Autowired
    private SendSmsService sendSmsService;

    @RequestMapping("/getSmsCode")
    @ResponseBody
    public String getSmsCode(HttpSession session, String tel) {
        User user = userInfoService.findByTel(tel);
        if (user == null) {
            return "该手机号还未注册!请先注册";
        }
        session.setAttribute("USER_SESSION", user);
        String SmsCode = RondomCode.randomCode();
        System.out.println("发送的验证码：" + SmsCode);
        session.setAttribute("SmsCode", SmsCode);
        String tip = sendSmsService.sendSms(SmsCode, new String[]{tel});
        return tip;
    }

    @RequestMapping("/doRegister")
    public String doRegister(HttpSession session, String InputSmsCode, Model model) {
        String SmsCode = (String) session.getAttribute("SmsCode");
        //限制用户必须先输入验证码,才能提交请求
        if (!InputSmsCode.equals(SmsCode)) {
            model.addAttribute("tip", "验证码有误");
            return "code_login";
        }
        return "forward:community";
    }
}
