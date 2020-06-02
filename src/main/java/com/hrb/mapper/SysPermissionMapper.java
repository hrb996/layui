package com.hrb.mapper;

import com.hrb.entity.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    @Select("SELECT * FROM sys_permission p WHERE  p.id IN (SELECT rp.pid FROM sys_role_permission rp WHERE rid = #{rid})")
    public List<SysPermission> findbyrid(@Param("rid") Integer rid);


    /**
     * 根据登录人显示  显示一级菜单
     * @param open
     * @return
     */
    @Select("SELECT p5.* FROM sys_permission p5 WHERE  p5.type='menu'  AND   p5.id IN (SELECT rp3.pid FROM sys_role_permission rp3 WHERE rp3.rid IN ( SELECT  DISTINCT r2.id FROM  sys_role r2 INNER JOIN  sys_role_user ru2 ON ru2.rid=r2.id WHERE ru2.rid IN (SELECT r.id FROM sys_role r  INNER JOIN sys_role_user ru ON r.id=ru.rid WHERE ru.uid=(SELECT id FROM sys_user WHERE id=#{id}))))")
    public List<SysPermission> findYiji(Integer id);

    @Select("SELECT p5.* FROM sys_permission p5 WHERE    p5.id IN (SELECT rp3.pid FROM sys_role_permission rp3 WHERE rp3.rid IN ( SELECT  DISTINCT r2.id FROM  sys_role r2 INNER JOIN  sys_role_user ru2 ON ru2.rid=r2.id WHERE ru2.rid IN (SELECT r.id FROM sys_role r  INNER JOIN sys_role_user ru ON r.id=ru.rid WHERE ru.uid=(SELECT id FROM sys_user WHERE id=#{id}))))")
    public List<SysPermission> findYiji2(Integer id);
    /**
     * 根据登录人显示  显示2级菜单
     * @param
     * @return
     */
    @Select("SELECT p5.* FROM sys_permission p5 WHERE p5.pid=#{pid}")
    public List<SysPermission> finderji(@Param("pid") int pid);
}
