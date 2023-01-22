package fit.yujing.controller;

import fit.yujing.pojo.Demand;
import fit.yujing.pojo.User;
import fit.yujing.pojo.UserInfo;
import fit.yujing.service.DemandService;
import fit.yujing.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/addUserInfo")
    public String addUserInfo(UserInfo userInfo, Model model) {
        System.out.println(userInfo);
        int a = userInfoService.addUserInfo(userInfo);
        if (a > 0) {
            System.out.println("addUserInfo Successful!");
            model.addAttribute("login_msg", "注册成功!请登录");
        }
        return "login";
    }

    @RequestMapping("/home.action")
    public String home(HttpServletRequest req, Model model) {
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        UserInfo userInfo = userInfoService.findById(user.getId());
        model.addAttribute("UserInfo", userInfo);
        return "home";
    }

    @RequestMapping("/charge.action")
    public String charge(HttpServletRequest req, UserInfo userInfo, Model model) {
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        userInfo.setId(user.getId());
        int a = userInfoService.upBalance(userInfo);
        if (a > 0) {
            model.addAttribute("success_msg", "充值成功!您又变强大了");
        } else {
            model.addAttribute("err_msg", "充值好像出了点问题呢");
        }
        return "forward:home.action";
    }
    @Autowired
    private DemandService demandService;

    @RequestMapping("/upUserInfo.action")
    public String upUserInfo(UserInfo userInfo, Demand demand, Model model, HttpServletRequest req){
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        userInfo.setId(user.getId());
        int a = userInfoService.upUserInfo(userInfo);
        //获取
        demand.setId(user.getId());
        demand.setName(userInfo.getName());
        int b = demandService.upName(demand);
        if (a>0){
            model.addAttribute("success_msg","修改成功!");
        }else{
            model.addAttribute("err_msg","修改好像出了点问题呢!");
        }
        return "forward:home.action";
    }

    @RequestMapping("/lookUser.action")
    @ResponseBody
    public UserInfo lookUser(Integer id){
        return userInfoService.findById(id);
    }
}
