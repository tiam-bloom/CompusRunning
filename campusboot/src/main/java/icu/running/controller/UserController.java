package icu.running.controller;

import icu.running.pojo.User;
import icu.running.pojo.UserInfo;
import icu.running.service.UserInfoService;
import icu.running.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "UserController", tags = {"用户查询相关接口"})
@CrossOrigin  //解决跨域问题
//@RestController  //Controller+ResponseBody
//@RequestMapping("/UserController")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String hello(){
        return "login";
    }

    @RequestMapping("/login.action")
    public String login(User user, Model model, HttpSession session) {
        //获取用户输入的账号和密码
        String username = user.getUsername();
        String password = user.getPassword();
        //管理员登录页面
        if (username.equals("admin")&&password.equals("zxcvbnm.123")){
            session.setAttribute("USER_SESSION", user);
            return "forward:admin";
        }
        User user1 = userService.findUserByName(username);
        if (user1 == null) {
            model.addAttribute("login_msg", "您还未注册，请先注册！");
            return "login";
        } else {
            //获取查询到用户密码进行比对
            String password1 = user1.getPassword();
            if (!password1.equals(password)) {
                model.addAttribute("login_msg", "您输入的密码有误,请检查您的密码！");
                return "login";
            } else {
                session.setAttribute("USER_SESSION", user1);
                return "forward:community";
            }
        }
    }

    @RequestMapping("/admin")
    public String admin(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("USERS",users);
        return "admin_user";
    }

    @RequestMapping("/upUser.action")
    public String upUser(User user,UserInfo userInfo){
        int c = userInfoService.updateBl(userInfo);
        int b = userInfoService.upUserInfo(userInfo);
        int a = userService.upUser(user);
        return "forward:admin";
    }

    @RequestMapping("/delUser.action")
    public String delUser(Integer id){
        int b = userInfoService.delUserInfo(id);
        int a = userService.delUser(id);
        return "forward:admin";
    }

    @RequestMapping("/logout.action")
    public String logout(HttpSession session) {
        session.invalidate();  //清除session
        return "login";
    }

    @Autowired
    private UserInfoService userInfoService;
    //注册用户
    @RequestMapping("/register.action")
    public String register(User user, UserInfo userInfo, Model model) {
        User user1 = userService.findUserByName(user.getUsername());
        if (user1 != null){
            model.addAttribute("login_msg","您已注册!请直接登录");
        }else{
            int a = userService.addUser(user);
            User user2 = userService.findUserByName(user.getUsername());
            userInfo.setId(user2.getId());
            int i = userInfoService.addUserInfo(userInfo);
            if (a>0 && i>0){
                model.addAttribute("register_msg","注册成功!请登录");
            }else{
                model.addAttribute("login_msg","注册发生异常!请重试");
            }
        }
        return "login";
    }

    @RequestMapping("/upPassword.action")
    public String upPassword(@RequestParam("password1")String password1, User user, HttpServletRequest req, Model model){
        //password1 新密码
        //user.getPassword() 传入的"原密码"
//        System.out.println(password1);
//        System.out.println(user.getPassword());
        User user1 = (User) req.getSession().getAttribute("USER_SESSION");
        User user2 = userService.findUserByName(user1.getUsername());

        //校验原密码是否正确
        if (!user2.getPassword().equals(user.getPassword())){
            model.addAttribute("err_msg","输入的原密码有误!请检查密码");
            return "forward:home.action";
        }else{
            user.setId(user1.getId());
            user.setPassword(password1);
            int a = userService.upPassword(user);
            if (a>0){
                model.addAttribute("success_msg","密码修改成功!");
            }
            return "forward:home.action";
        }
    }

    @ApiOperation(value = "zzzz",notes = "接口说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", paramType = "query", value = "姓名"),
    })
    @RequestMapping("/findUser")
    public String findUser(@RequestParam(required = false) String name, Model model){
        List<User> users = userService.findUserAndInfo1(name);
        model.addAttribute("USERS",users);
        return "admin_user";
    }
}
