package com.hrb.service;

import com.hrb.entity.SysRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
public interface SysRolePermissionService extends IService<SysRolePermission> {
    /**
     * 根据登录的id查询
     * @param uId
     * @return
     */
    public List<SysRolePermission> findByUserId(int uId);
}
