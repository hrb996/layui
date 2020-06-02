package com.hrb.mapper;

import com.hrb.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrb.entity.SysRoleUser;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据Roleuser表的id查询
     * @param rUid
     * @return
     */

    public List<SysRoleUser> findSysRoleUserByUserId(@Param("rUid") int rUid);

    /**
     * 根据登录的用户id查询所拥有的权限
     * @param uid
     * @return
     */
   @Select("SELECT r.* FROM sys_role r WHERE r.id IN (SELECT ru.rid FROM sys_role_user ru WHERE ru.uid = (SELECT u.id FROM sys_user u WHERE u.id=#{uid}))")
    public List<SysRole> findByuid(@Param("uid") Integer uid);
}
