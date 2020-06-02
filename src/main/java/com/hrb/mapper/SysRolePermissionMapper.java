package com.hrb.mapper;

import com.hrb.entity.SysRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
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
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    /**
     * 根据登录的id查询
     * @param uId
     * @return
     */
   public List<SysRolePermission> findByUserId(@Param("uId") int uId);
@Insert("INSERT INTO sys_role_permission(rid,pid) VALUES(#{rid},#{pid})")
   public int addsp(@Param("rid") int rid,@Param("pid") int pid);



}
