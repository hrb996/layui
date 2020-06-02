package com.hrb.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hrb.entity.SysDept;
import com.hrb.mapper.SysDeptMapper;
import com.hrb.service.SysDeptService;
import com.hrb.util.DataGridView;
import com.hrb.util.TreeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Contended;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SysDeptController {
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysDeptService sysDeptService;

    @RequestMapping("/Dept/getDeptAll")
    @ResponseBody
    public Object dd(SysDept sysDept,int page,int limit){
        System.out.println("/Dept/getDeptAll");
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        Page pageList = new Page(page,limit);
        IPage page1 = sysDeptService.page(pageList, queryWrapper);
        return new  DataGridView(pageList.getTotal(),pageList.getRecords());
    }

    /**
     * 用户管理的树
     * @return
     */
    @RequestMapping("/Dept/loadDeptManagerLeftTreeJson")
    @ResponseBody
    public Object findAddress(){
        List<SysDept> dept = sysDeptMapper.selectList(null);
        List<TreeNode> list = new ArrayList<>();
        if (dept != null){
            for (SysDept sysDept : dept) {
                list.add(new TreeNode(sysDept.getId(),sysDept.getPid(),sysDept.getTitle(),"0"));
            }
        }
        return new DataGridView(list);
    }

    @RequestMapping("/sys/toDeptManager")
    public Object toDeptManager(){
        return  "system/dept/deptManager";
    }

    @RequestMapping("/sys/toDeptLeft")
    public Object toDeptManager2(){
        return  "system/dept/deptLeft";
    }

    @RequestMapping("/sys/toDeptRight")
    public Object toDeptManager3(){
        return  "system/dept/deptRight";
    }
}
