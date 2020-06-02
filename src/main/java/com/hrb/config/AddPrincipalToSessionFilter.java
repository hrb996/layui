package com.hrb.config;


import com.hrb.entity.SysUser;
import com.hrb.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 解决session丢失
 */
public class AddPrincipalToSessionFilter extends OncePerRequestFilter {
    @Autowired
    SysUserService sysUserService;

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        //查询当前用户的信息
        Subject subject = SecurityUtils.getSubject();
        //判断用户是不是通过自动登录进来的
        if (subject.isRemembered()) {
            SysUser user2=(SysUser)subject.getPrincipal();
            String userName = user2.getName();
            System.out.println(userName+"..........");
            if(userName==null){
                return;
            }
            //根据用户名查询该用户的信息
            SysUser user=sysUserService.login(userName);
            if (user == null) return;
            //由于是继承的OncePerRequestFilter，没办法设置session
            //这里发现可以将servletReques强转为子类，所以使用request.getsiion())
            HttpServletRequest request=(HttpServletRequest) servletRequest;
            HttpSession session=request.getSession();
            //当session为空的时候
            if (session.getAttribute("user")==null){
                //把查询到的用户信息设置为session，时效为3600秒
                session.setAttribute("user",user);
                session.setMaxInactiveInterval(3600);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}