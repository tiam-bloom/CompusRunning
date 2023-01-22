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
import java.util.List;

@Controller
public class DemandController {
    @Autowired
    private DemandService demandService;

    //页面跳转
    @RequestMapping("/community")
    public String community(Model model) {
        List<Demand> demands = demandService.findAll();
        model.addAttribute("DEMANDS", demands);
        return "community";
    }

    @RequestMapping("/findDemandByTitle")
    public String findDemandByTitle(String title, Model model) {
        List<Demand> demands = demandService.findDemandByTitle(title);
        if (demands.size() > 0) {
            model.addAttribute("DEMANDS", demands);
        } else {
            model.addAttribute("tips", "查询不到相关标题!换个试试呗");
        }
        return "community";
    }

    @RequestMapping("/publish.action")
    public String publish(HttpServletRequest req, Model model) {
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        List<Demand> demands = demandService.findDemandById(user.getId());
        model.addAttribute("DEMANDS", demands);
        return "publish";
    }

    @RequestMapping("undertake.action")
    public String undertake(HttpServletRequest req, Model model) {
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        List<Demand> demands = demandService.findDemandByItd(user.getId());
        model.addAttribute("DEMANDS", demands);
        return "undertake";
    }

    @Autowired
    private UserInfoService userInfoService;

    //接单
    @RequestMapping("/undertakeDemand.action")
    public String undertakeDemand(UserInfo userInfo, Demand demand, HttpServletRequest req) {
        String did = req.getParameter("did"); //获取订单编号
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        System.out.println("Demand=" + did);
        demand.setLtd(user.getId());
        int u = demandService.addStateAndLtd(demand);
        Demand demand1 = demandService.findDemandByDid(did);
        userInfo.setId(user.getId());
        userInfo.setBalance(demand1.getReward());
        int b = userInfoService.upBalance(userInfo);
        if (u > 0) {
            System.out.println("Nice!");
        }
        return "redirect:community";
    }

    @RequestMapping("/publishDemand")
    public String publishDemand(Demand demand, UserInfo userInfo, HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        UserInfo userInfo1 = userInfoService.findById(user.getId());
        //发布订单
        demand.setId(user.getId());
        demand.setName(userInfo1.getName());
        int a = demandService.addDemand(demand);
        //更新账户余额
        userInfo.setId(user.getId());
        userInfo.setBalance(demand.getReward());
        int b = userInfoService.lowerBalance(userInfo);
        return "redirect:community";
    }

    @RequestMapping("/findDemandByDid")
    @ResponseBody
    public Demand DisplayDemand(String did) {
        return demandService.findDemandByDid(did);
    }

    @RequestMapping("/upDemand")
    public String upDemand(Demand demand) {
        int a = demandService.upDemand(demand);
        return "redirect:publish.action";
    }

    //管理员编辑
    @RequestMapping("/upDemand1")
    public String upDemand1(Demand demand) {
        int a = demandService.upDemand(demand);
        return "redirect:admin_demand";
    }

    @RequestMapping("/delDemand.action")
    public String delDemand(Integer did, Model model) {
        int a = demandService.delDemand(did);
        if (a > 0) {
            model.addAttribute("success_msg", "删除成功!");
        }
        //根据是否为管理员判断,删除后的转发页面
        return "forward:publish.action";
    }

    // 管理员删除
    @RequestMapping("/delDemand1.action")
    public String delDemand1(Integer did, Model model) {
        int a = demandService.delDemand(did);
        if (a > 0) {
            model.addAttribute("success_msg", "删除成功!");
        }
        //根据是否为管理员判断,删除后的转发页面
        return "forward:admin_demand";
    }


    @RequestMapping("/Pdemand.action")
    public String Pdemand(Integer id, Model model) {
        List<Demand> demands = demandService.findDemandById(id);
        model.addAttribute("Demands", demands);
        return "admin_demand";
    }

    @RequestMapping("/Udemand.action")
    public String Udemand(Integer id, Model model) {
        List<Demand> demands = demandService.findDemandByItd(id);
        model.addAttribute("Demands", demands);
        return "admin_demand";
    }

    @RequestMapping("/admin_demand")
    public String admin_demand(Model model) {
        List<Demand> demands = demandService.findAllDemand();
        model.addAttribute("Demands", demands);
        return "admin_demand";
    }

}
