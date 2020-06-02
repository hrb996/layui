package com.hrb.service;

import com.hrb.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
public interface SysPermissionService extends IService<SysPermission> {
   public List<SysPermission> findList(Map<String,Object> map);

   /**
    * 根据登录人   显示一级菜单
    * @return
    */
   public List<SysPermission> findYiji(Integer id);

   /**
    * 根据登录人显示  显示2级菜单
    * @param
    * @return
    */
   @Select("SELECT p5.* FROM sys_permission p5 WHERE p5.pid=#{pid}")
   public List<SysPermission> finderji(@Param("pid") int pid);
}
