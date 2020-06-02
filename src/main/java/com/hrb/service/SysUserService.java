package com.hrb.service;

import com.hrb.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
public interface SysUserService extends IService<SysUser> {
    public SysUser login(String userName);

    public int maxOrdernum();
}
