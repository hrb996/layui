package com.hrb.service;

import com.hrb.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hrb.entity.SysRoleUser;
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
public interface SysRoleService extends IService<SysRole> {
    /**
     * 根据Roleuser表的id查询
     * @param rUid
     * @return
     */
    public List<SysRoleUser> findSysRoleUserByUserId(int rUid);
}
