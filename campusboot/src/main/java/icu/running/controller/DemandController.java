package icu.running.controller;

import icu.running.pojo.DataStatus;
import icu.running.pojo.Demand;
import icu.running.pojo.User;
import icu.running.pojo.UserInfo;
import icu.running.service.DemandService;
import icu.running.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import net.sf.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin  //解决跨域问题
@Api(value = "DemandController", tags = {"Demand相关接口"})
@RestController  //Controller+ResponseBody
@Slf4j  //日志
public class DemandController {
    @Autowired
    private DemandService demandService;

    //页面跳转
    @ApiOperation(value = "3.2.1-community", notes = "3.2.1-查询全部")
    @GetMapping("/community")
    public JSONObject community() {
        //List<Demand> demands = demandService.findAll();
        List<Demand> demands = demandService.findBigHeadAndDemand();
        JSONObject jo = new JSONObject();
        DataStatus ds = new DataStatus();
        if (demands.size() > 0) {
            ds.setStatusCode(100);
            ds.setStatusDescription("查询全部成功");
            jo.put("DataStatus", ds);
            jo.put("Data", demands);
            return jo;
        }
        ds.setStatusCode(104);
        ds.setStatusDescription("查询全部失败");
        jo.put("DataStatus", ds);
        return jo;
    }

    @ApiOperation(value = "3.2.1-findDemandByTitle", notes = "3.2.1-根据标题模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", paramType = "query", value = "标题"),
    })
    @GetMapping("/findDemandByTitle")
    public JSONObject findDemandByTitle(@RequestParam(required = false) String title) {
        List<Demand> demands = demandService.findDemandByTitle(title);
        JSONObject jo = new JSONObject();
        DataStatus ds = new DataStatus();
        if (demands.size() > 0) {
            ds.setStatusCode(100);
            ds.setStatusDescription("查询标题成功");
            jo.put("DataStatus", ds);
            jo.put("Data", demands);
            return jo;
        }
        ds.setStatusCode(104);
        ds.setStatusDescription("查询标题失败");
        jo.put("DataStatus", ds);
        return jo;
    }

    @ApiOperation(value = "3.2.1-publish.action", notes = "3.2.1-查询我的发布")
    @GetMapping("/publish.action")
    public JSONObject publish(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        List<Demand> demands = demandService.findDemandById(user.getId());
        JSONObject jo = new JSONObject();
        DataStatus ds = new DataStatus();
        if (demands.size() > 0) {
            ds.setStatusCode(100);
            ds.setStatusDescription("查询我的发布成功");
            jo.put("DataStatus", ds);
            jo.put("Data", demands);
            return jo;
        }
        ds.setStatusCode(104);
        ds.setStatusDescription("查询我的发布失败");
        jo.put("DataStatus", ds);
        return jo;
    }

    @ApiOperation(value = "3.2.1-undertake.action", notes = "3.2.1-查询我的承接")
    @GetMapping("undertake.action")
    public JSONObject undertake(HttpServletRequest req) {
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        List<Demand> demands = demandService.findDemandByItd(user.getId());
        JSONObject jo = new JSONObject();
        DataStatus ds = new DataStatus();
        if (demands.size() > 0) {
            ds.setStatusCode(100);
            ds.setStatusDescription("查询我的承接成功");
            jo.put("DataStatus", ds);
            jo.put("Data", demands);
            return jo;
        }
        ds.setStatusCode(104);
        ds.setStatusDescription("查询我的承接失败");
        jo.put("DataStatus", ds);
        return jo;
    }

    @Autowired
    private UserInfoService userInfoService;

    //接单
    @ApiOperation(value = "3.2.1-undertakeDemand.action", notes = "3.2.1-承接订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userInfo", paramType = "query", value = "userInfo"),
            @ApiImplicitParam(name = "demand", paramType = "query", value = "demand")
    })
    @RequestMapping("/undertakeDemand.action")
    public String undertakeDemand(@RequestParam UserInfo userInfo,@RequestParam Demand demand, HttpServletRequest req) {
        log.info("[3.1.1-接单]-开始-获取订单编号");
        String did = req.getParameter("did");
        User user = (User) req.getSession().getAttribute("USER_SESSION");
        demand.setLtd(user.getId());
        int u = demandService.addStateAndLtd(demand);
        Demand demand1 = demandService.findDemandByDid(did);
        userInfo.setId(user.getId());
        userInfo.setBalance(demand1.getReward());
        int b = userInfoService.upBalance(userInfo);
        log.info("[3.1.1-接单]-结束");
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
