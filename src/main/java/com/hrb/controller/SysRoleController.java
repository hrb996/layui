package com.hrb.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrb.entity.SysPermission;
import com.hrb.entity.SysRole;
import com.hrb.entity.SysRolePermission;
import com.hrb.entity.SysRoleUser;
import com.hrb.mapper.SysPermissionMapper;
import com.hrb.mapper.SysRoleMapper;
import com.hrb.mapper.SysRolePermissionMapper;
import com.hrb.mapper.SysRoleUserMapper;
import com.hrb.service.SysRolePermissionService;
import com.hrb.service.SysRoleService;
import com.hrb.util.DataGridView;
import com.hrb.util.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
@Controller
public class SysRoleController {
@Resource
 private SysRoleMapper sysRoleMapper;
@Resource
 private SysRoleUserMapper sysRoleUserMapper;
@Resource
private SysRoleService sysRoleService;
@Resource
private SysPermissionMapper sysPermissionMapper;
@Resource
private SysRolePermissionService sysRolePermissionService;

@Resource
private  SysRolePermissionMapper sysRolePermissionMapper;

@RequestMapping("/sys/toRoleManager")
 public String toRoleManager(){
    return "system/role/roleManagers";
}


  @RequestMapping("/role/savePermission")
  @ResponseBody
  public Object street(String ids,int rid){
      System.out.println("=r4g=============="+ids);
      System.out.println("=====qqqqqqqqqqq==========="+rid);
      String substring = ids.substring(0, ids.length() - 1);
      String[] strs = substring.split(",");
      sysRolePermissionMapper.deleteById(rid);
      for (String str : strs){
         sysRolePermissionMapper.addsp(rid,Integer.parseInt(str));
      }

      HashMap<String, Object> map = new HashMap<>();


      return  JSON.toJSON(map);
  }

    /**
     * 角色管理
     * @param sysRole
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/role/getRoleByPage")
    @ResponseBody
 public Object toRoleManager(SysRole sysRole,int page,int limit){
        System.out.println("sysRole.getAvailable()"+sysRole.getAvailable());
    QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
    if(page == 0){
        page = 1;
    }
    if(limit==0){
        limit=10;
    }
        Page pageLis = new Page(page,limit);
    queryWrapper.like(StringUtils.isNotBlank(sysRole.getName()),"name",sysRole.getName());
    queryWrapper.like(StringUtils.isNotBlank(sysRole.getRemark()),"remark",sysRole.getRemark());
if(sysRole.getAvailable()!= null)
       queryWrapper.eq("available",sysRole.getAvailable());
    sysRoleService.page(pageLis,queryWrapper);
    return new DataGridView(pageLis.getTotal(),pageLis.getRecords());
}




@RequestMapping("/user/saveUserRole")
@ResponseBody
  public DataGridView save(String role){
    System.out.println("role=================="+role);
     int num =  role.indexOf(",");
     int dd = role.indexOf("=");
    System.out.println("ddddd"+dd+"num====="+num);
    int uidindex = role.indexOf(",");
    String uu =  role.substring(dd+1,uidindex);
    System.out.println("uid========"+uu);
     String roless = role.substring(num+1,role.length());
    System.out.println(roless);
    String[] strs = roless.split(",");
    QueryWrapper<SysRoleUser> queryWrapper = new QueryWrapper<>();
    int uid = Integer.parseInt(uu);
    queryWrapper.eq("uid",uid);
    sysRoleUserMapper.delete(queryWrapper);    //删除
    for(String str : strs){
        System.out.println("rid============"+str);
        int rid = Integer.parseInt(str);
            System.out.print("ids===="+str+"\t");
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRid(rid);
            sysRoleUser.setUid(uid);
            sysRoleUserMapper.addSysRoleUser(rid,uid);  //添加的方法
        }
    return null;
}


    /**
     * 根据用户登录的uid查权限
     * @param uid
     * @return
     */
    @RequestMapping("/user/loadDeptManagerLeftTreeJson")
    @ResponseBody
    public DataGridView roleall(Integer uid){
        System.out.println("threed");
      //查询所有的角色
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        //根据登录的用户ID查询所拥有的权限
        List<SysRole> list = sysRoleMapper.findByuid(uid);
      //构造返回的数据格式
        List<TreeNode> treeNodelist=new ArrayList<>();
        if(null != list && list.size()>0){
           for(SysRole sysRole : sysRoles){
               String checkArr="0";
               for (SysRole role : list){
                   if(sysRole.getId() == role.getId()){
                       checkArr = "1";
                   }
               }
               treeNodelist.add(new TreeNode(sysRole.getId(),0,sysRole.getName(),checkArr));
           }
            return new DataGridView(treeNodelist);
        }else{
            treeNodelist.add(new TreeNode(1,0,"没有角色", "0"));
            return new DataGridView(treeNodelist);
        }

    }


}

