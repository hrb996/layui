package com.hrb.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrb.entity.SysPermission;
import com.hrb.entity.SysRolePermission;
import com.hrb.mapper.SysPermissionMapper;
import com.hrb.mapper.SysRolePermissionMapper;
import com.hrb.service.SysPermissionService;
import com.hrb.util.DataGridView;
import com.hrb.util.TreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class SysPermissionController {
@Resource
  private SysPermissionService sysPermissionService;
@Resource
  private SysPermissionMapper sysPermissionMapper;
@Resource
 private SysRolePermissionMapper sysRolePermissionMapper;



    /**
     * 根据uid 查他的权限
     * @param
     * @return
     */
    @RequestMapping("/menu/loadmenuManagerLeftTreeJson2")
    @ResponseBody
    public Object TreeJson2(Integer rid){
        System.out.println("/menu/loadmenuManagerLeftTreeJson2"+rid);

        QueryWrapper<SysRolePermission> queryWrapper = new QueryWrapper<>();

        List<SysPermission> list = sysPermissionMapper.selectList(null);

        List<SysPermission> finderji = sysPermissionMapper.findbyrid(rid);
        System.out.println("[][][][]"+finderji.size()+"[][]"+finderji);
        String checkArr="0";
        //  List<SysPermission> finderji = sysPermissionMapper.findYiji2(rid);
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        if (list !=null && list.size() >0){
            for (SysPermission li : list){
                System.out.println("[][][]"+li);
                for(SysPermission you : finderji){
                    System.out.println("========"+you);
                    checkArr = "0";
                    if (li.getId() == you.getId()){
                        checkArr = "1";
                    }
                }
                treeNodes.add(new TreeNode(li.getId(),li.getPid(),li.getTitle(),checkArr));
            }
        }
        return  new DataGridView(treeNodes);
    }


        @RequestMapping("/menu/loadmenuManagerLeftTreeJson")
        @ResponseBody
          public Object TreeJson(){
            QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
            List<SysPermission> list = sysPermissionMapper.selectList(queryWrapper);
            ArrayList<TreeNode> treeNodes = new ArrayList<>();
            for (SysPermission li : list){
                treeNodes.add(new TreeNode(li.getId(),li.getPid(),li.getTitle(),"0"));
            }
            return  new DataGridView(treeNodes);
        }

   @RequestMapping("/menu/getmenuAll")
  @ResponseBody
   public Object menuAll(SysPermission sysPermission,int page,int limit){
       System.out.println("sysPermission.getId()"+sysPermission.getId());
       QueryWrapper<SysPermission> queryWrapper = new QueryWrapper<>();
       queryWrapper.like(StringUtils.isNotBlank(sysPermission.getTitle()),"title",sysPermission.getTitle());
      if(sysPermission.getId() != null){
          queryWrapper.eq("pid",sysPermission.getId());
      }

       Page pageList = new Page(page,limit);
       sysPermissionService.page(pageList,queryWrapper);
       return new DataGridView(pageList.getTotal(),pageList.getRecords());
   }

    /**
     * 菜单管理
     * @return
     */
    @RequestMapping("/sys/toMenuManager")
        public String MenuManage(){
        return "system/menu/MenuManager";
    }

    @RequestMapping("/sys/toMenuLeft")
    public String MenuManage2(){
        return "system/menu/MenuLeft";
    }
    @RequestMapping("/sys/toMenuRight")
    public String MenuManage3(){
        return "system/menu/MenuRight";
    }

    @RequestMapping("/sysPermission/erji")
    @ResponseBody
    public void erji(Integer id, HttpServletResponse response){
        System.out.println("异步获取数据"+id);
        response.setCharacterEncoding("utf-8");
        List<SysPermission> finderji = null;
        PrintWriter out = null;
        try{
            out = response.getWriter();
            finderji  = sysPermissionService.finderji(id);
        }catch (Exception e){
            System.out.println("出错了============="+ e.getMessage());
              e.getMessage();
        }
        System.out.println(JSON.toJSON(finderji));
        out.print(JSON.toJSON(finderji));
        out.flush();
        out.close();
    }
}

