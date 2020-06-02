package com.hrb.mapper;

import com.hrb.entity.SysUser;
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
public interface SysUserMapper extends BaseMapper<SysUser> {
  /**
   * 自定义分页的方法
   * @param sysUser
   * @return
   */
  @Select("SELECT u.*,d.title as deptTitle  from sys_user  u  inner join sys_dept  d on u.deptid = d.id where 1=1    limit #{currIndex} , #{pageSize}")
  public List<SysUser> lists(@Param("name") String name, @Param("address")String address,@Param("currIndex") int currIndex,@Param("pageSize") int pageSize);

  @Select(" SELECT count(1) from sys_user u   inner join sys_dept  d on u.deptid = d.id where 1=1")
  public int count(@Param("name") String name, @Param("address")String address);

  @Select("SELECT MAX(ordernum) FROM sys_user ")
  public int maxOrdernum();
}
