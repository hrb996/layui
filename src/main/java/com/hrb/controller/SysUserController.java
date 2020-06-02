package com.hrb.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrb.entity.SysDept;
import com.hrb.entity.SysRole;
import com.hrb.entity.SysUser;
import com.hrb.mapper.SysDeptMapper;
import com.hrb.mapper.SysRoleMapper;
import com.hrb.mapper.SysUserMapper;
import com.hrb.service.SysRoleService;
import com.hrb.service.SysUserService;
import com.hrb.util.DataGridView;
import com.hrb.util.TreeNode;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
@Controller
public class SysUserController {
   @Resource
   private SysUserMapper sysUserMapper;

   @Resource
   private SysUserService sysUserService;
   @Resource
   private SysDeptMapper sysDeptMapper;
@Resource
  private SysRoleMapper sysRoleMapper;

@RequestMapping("/user/cheloginname")
@ResponseBody
  public Object yy(String name){
    HashMap<String, Object> map = new HashMap<>();
    QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("loginname",name);
    List<SysUser> list = sysUserMapper.selectList(queryWrapper);
    String qq = "";
    if(list.size()==0){
      qq="ok";
    }else{
        qq="no";
    }
    return JSON.toJSON(qq);
}

    /**
     * 添加
     * @param address
     * @param name
     * @return
     */
        @RequestMapping("/user/add")
        @ResponseBody
         public Object save(SysUser sysUser,Integer id,String address,String name,int ordernum,int mgr,String loginname,String remark,int sex,int available,int deptid){
            System.out.println("/user/update==="+address+"id==\t"+id);
            System.out.println(sysUser.toString());
            Map<String,Object> map = new HashMap<>();
            SysUser sysUser2 = null;
            try{
                if(id != null){
                    sysUser2 = new SysUser(id,name,loginname, address, sex, remark,deptid, mgr, ordernum);
                }else{
                    sysUser2 = new SysUser(name,loginname, address, sex, remark,deptid, mgr, ordernum);
                }
                sysUserService.saveOrUpdate(sysUser);
                map.put("code",200);
            }catch (Exception e){
                map.put("code",-100);
            }
            return JSON.toJSON(map);
        }
    /**
     * 根据部门查直属领导
     * @param id
     * @return
     */
    @RequestMapping("/user/getUserallByMgrDeptId")
@ResponseBody
  public Object enne(Integer id){
    System.out.println("/user/getUserallByMgrDeptId"+id);
    QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("deptid",id);
    List<SysUser> list = sysUserMapper.selectList(queryWrapper);
    return JSON.toJSON(list);
}

    /**
     * 排序码
     * @param
     * @return
     */
    @RequestMapping("/user/loadUserMaxOrderNum")
    @ResponseBody
    public Object paixuma(Map<String,Object> map){
        try{
            int i = sysUserService.maxOrdernum();
            int aa = i+1;
            map.put("value",aa);
        }catch (Exception e){
            map.put("value","-1");
        }
        return JSON.toJSON(map);
    }
    //删除用户
    @RequestMapping("/user/delete")
    @ResponseBody
    public Object deleteUser(Integer id){
        Map<String,Object> map = new HashMap<>();
        int i = sysUserMapper.deleteById(id);
        if (i>0){
            map.put("code",200);
        }else{
            map.put("code",100);
        }
        return JSON.toJSON(map);
    }


   @RequestMapping("/loadDeptManage")
   public DataGridView saveUserRole(String role,Integer uid){
      System.out.println(uid+"============="+role);
      System.out.println("JSON.toJSON(role)"+JSON.toJSON(role));
//查询全部角色
      List<SysRole> sysRoles = sysRoleMapper.selectList(null);
//根据用户的id查询角色
      return new DataGridView(sysRoles);
   }

   /**
    * 跳转用户管理
    * @return
    */
   @RequestMapping("/sys/toUserManager")
   public String selUser(){
       System.out.println("用户管理");
       return "system/user/userManager";
   }

   @RequestMapping("/sys/toUserLeft")
   public String toUserLeft(){
      System.out.println("left");
      return "system/user/userLeft";
   }
   @RequestMapping("/sys/toUserRight")
   public String toUserRight(){
      System.out.println("right");
      return "system/user/userRight";
   }

   /**
    * 查询所有用户
    * @param name
    * @param address
    * @param request
    * @return
    */
   @RequestMapping("/user/getUserAll")
   @ResponseBody
   public DataGridView getUserAll(String name, String address, HttpServletRequest request,SysUser sysUser,int page,int limit){
        /* SysUser sysUser = new SysUser();
       sysUser.setName(name);
       sysUser.setAddress(address);
       int count = sysUserMapper.count(name,address);  //计算总记录数
       System.out.println("count =========="+count);
       int dqy = 1;
       Page page = new Page(1,count); //计算分页
       sysUser.setCurrIndex(page.getDqy());
       sysUser.setPageSize(page.getYdx());
       List<SysUser> lists = sysUserMapper.lists(name,address,page.getDqy(),page.getYdx());
       System.out.println("list===="+JSON.toJSON(lists));
       return new DataGridView((long)page.getZjr(),JSON.toJSON(lists));*/


      System.out.println("sysUser.getName()"+sysUser.getName()+"\tqq"+sysUser.getAddress());
      QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>();
//      queryWrapper.like(StringUtils.isNotBlank(name),"name",sysUser.getName());
//      queryWrapper.like(StringUtils.isNotBlank(address),"address",sysUser.getAddress());
      if(name != null  && !"".equals(name)){
         queryWrapper.like("name",name);
      }
      if(address != null && !"".equals(address)) {
         queryWrapper.like("address",address);
      }
      if (sysUser.getDeptid() != null && !sysUser.getDeptid().equals("")){
          queryWrapper.eq("deptid",sysUser.getDeptid());
      }
      Page<SysUser> pages = new Page<SysUser>(page,limit);
      sysUserService.page(pages,queryWrapper);
      List<SysUser> records = pages.getRecords();
      for (SysUser sysUse: records){
         SysDept sysDept = sysDeptMapper.selectById(sysUse.getDeptid());
         if(sysDept!=null){
            sysUse.setDeptTitle(sysDept.getTitle());
         }
      }
      //IPage<SysUser> iPage = sysUserMapper.selectPage(page, queryWrapper);
       System.out.println("iPage.getTotal()========"+pages.getTotal());
      return new DataGridView(pages.getTotal(),pages.getRecords());
//
//       System.out.println("list.............");
//       Page<SysUser> pageList=new Page<>(page,limit);
//       QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
//       queryWrapper.like(StringUtils.isNotBlank(sysUser.getName()),"name",sysUser.getName());
//       queryWrapper.like(StringUtils.isNotBlank(sysUser.getAddress()),"address",sysUser.getAddress());
//       sysUserService.page(pageList, queryWrapper);
//       System.out.println("pageList.getTotal()"+pageList.getTotal());
//       return  new  DataGridView(pageList.getTotal(),pageList.getRecords());//Method call expected

}
}

