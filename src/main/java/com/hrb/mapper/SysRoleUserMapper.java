package com.hrb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrb.entity.SysRoleUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr
 * @since 2020-01-01
 */
public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {
    /**
     * 根据登录人的id查询
     * @param id
     * @return
     */
    public List<SysRoleUser> findByUserId(@Param("id") int id);

    @Insert("INSERT INTO sys_role_user  VALUES (#{rid},#{uid})")
    public void addSysRoleUser (@Param("rid") Integer rid,@Param("uid")Integer uid);
}
