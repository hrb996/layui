package com.hrb.config;


import com.hrb.entity.SysPermission;
import com.hrb.entity.SysUser;
import com.hrb.mapper.SysUserMapper;
import com.hrb.service.SysPermissionService;
import com.hrb.service.SysRolePermissionService;
import com.hrb.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import sun.security.provider.MD5;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //用户登陆次数计数
    private String SHIRO_LOGIN_COUNT = "shiro_login_count_";

    //用户登录是否被锁定    一小时 redisKey 前缀
    private String SHIRO_IS_LOCK = "shiro_is_lock_";

    private HttpServletRequest request;

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Resource
    private SysPermissionService sysPermissionService;
    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。AuthenticationToken*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("1：********************************身份认证：MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String usrName = token.getUsername();
        String usrPassword = new String(token.getPassword());
        System.out.println("usrName:"+usrName);
        System.out.println("usrPassword:"+usrPassword);

        //访问一次，计数一次
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.increment(SHIRO_LOGIN_COUNT+usrName, 1);  //每次增加1
        System.out.println(usrName+"：账号登陆的次数是："+opsForValue.get(SHIRO_LOGIN_COUNT+usrName)) ;

//如果是这个账号登陆异常，则登陆页面提醒。
        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+usrName))>=3) {
            if ("LOCK".equals(opsForValue.get(SHIRO_IS_LOCK + usrName))) {
                //计数大于3次，设置用户被锁定一分钟
                throw new DisabledAccountException("由于输入错误次数大于3次，帐号1分钟内已经禁止登录！");
            }
        }

        if(Integer.parseInt(opsForValue.get(SHIRO_LOGIN_COUNT+usrName))>=3){
            opsForValue.set(SHIRO_IS_LOCK+usrName, "LOCK");  //锁住这个账号，值是LOCK。
            stringRedisTemplate.expire(SHIRO_IS_LOCK+usrName, 1, TimeUnit.MINUTES);  //expire  变量存活期限
        }

        //通过username从数据库中查找 User对象，如果找到，没找到.
        SysUser user = sysUserService.login(usrName);
        System.out.println("----->>user="+user);

        String salt = "mr";;//盐

  /*      Integer hashIterations = 2;//散列次数
        //加盐 在家散列次数的密码
//        Md5Hash md5Hash = new Md5Hash(usrPassword,salt);
//        usrPassword=md5Hash.toString();*/
//        System.out.println("加盐 在家散列次数的密码"+ md5Hash);
//        System.out.println("相等？？？didi"+"弟弟".equals(user.getName()));
//        System.out.println("相等mima？？？"+user.getPwd().toString().equals(usrPassword));

        if(user == null){
            throw new UnknownAccountException("账号不存在！");
        }else if(!user.getPwd().equals(usrPassword) ){
            System.out.println();
            System.out.println("mima:"+user.getPwd());
            System.out.println("mima:"+usrPassword);
            throw new IncorrectCredentialsException("密码不正确！");
        }
        SecurityUtils.getSubject().getSession().setAttribute("user",user);//保存session
        //认证成功,给用户添加权限

      /*  Role role = userService.getRoleByUser(user);
        List<Right> rights = userService.findRightsByRole(role);
        role.getRights().addAll(rights);
        user.setRole(role);*/

        List<SysPermission> yiji = sysPermissionService.findYiji(user.getId());
        List<SysPermission> erji = null;
        SysPermission sysPermission = new SysPermission();
        for (SysPermission yi:yiji){
            System.out.println("yi.getId()=========="+yi.getId());
            erji = sysPermissionService.finderji(yi.getId());   //查询二级
            sysPermission.setSysPermissions(erji);    //存储
        }
        user.setPermissions(yiji);
        user.setSysPermission(sysPermission);
        SecurityUtils.getSubject().getSession().setAttribute("user",user);//保存session

        //认证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, //用户
                user.getPwd(), //密码
                getName()  //realm name
        );
        //清空登录计数
        opsForValue.set(SHIRO_LOGIN_COUNT+usrName, "0");
//清空锁
        opsForValue.set(SHIRO_IS_LOCK+usrName, "");

        return authenticationInfo;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("2：权限认证-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//授权信息
        //设置权限，如果用户没有响应的权限，会访问ShiroConfig过滤器中shiroFilete方法配置的未授权路径
        //将来这里应该是通过数据库动态授权
        //   SysUser user =   (SysUser) SecurityUtils.getSubject().getSession().getAttribute("user");


        return authorizationInfo;
    }

}
