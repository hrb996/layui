package com.hrb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hrb.entity.SysRoleUser;
import com.hrb.entity.SysUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr
 * @since 2020-01-01
 */
public interface SysRoleUserService extends IService<SysRoleUser> {
    /**
     * 根据登录的用户查询
     */
    public List<SysRoleUser> findByUser(SysUser sysUser);

    public List<SysRoleUser> findByUserId(int id);
}
