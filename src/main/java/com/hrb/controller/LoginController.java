package com.hrb.controller;

import com.alibaba.fastjson.JSON;
import com.hrb.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 登录的
 */
@Controller
public class LoginController {
    @RequestMapping("login")
    public String login(){
        return "system/index/login";
    }

    @RequestMapping("index")
    public String index(){
        System.out.println("跳转system/index/index");
        return "system/index/index";
    }

    @RequestMapping("/sys/todeskManager")
    public String toTime(){
        return "system/index/deskManager";
    }


    @RequestMapping("dologin")
    @ResponseBody
    public String dologin(String loginname, String pwd, boolean rememberMe, Map<String,Object> map, Session session, HttpServletResponse response) throws IOException {
        System.out.println(loginname+"\t=="+pwd);
        String salt = "mr";
        Md5Hash md5Hash = new Md5Hash(pwd,salt);
        pwd=md5Hash.toString();
        PrintWriter out  = response.getWriter();
        response.setContentType("appplication/csv;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.reset();
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(loginname, pwd, rememberMe);
            SecurityUtils.getSubject().login(token); //调用Shiro认证
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();

            map.put("msg","登录成功");
            map.put("code","200");
            out.print("ok");
            out.flush();
            out.close();
        }catch (Exception e){
            System.out.println("出错了==========="+e.getMessage());
            map.put("msg",e.getMessage());
            map.put("msg","登录失败");
            map.put("code","100");
            out.print("no");
            out.flush();
            out.close();
        }
        System.out.println(map);
        return "index";
    }

    @RequestMapping("/sys/loginOut")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        session.removeAttribute("user");
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/login";
    }
}
