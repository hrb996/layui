package com.hrb.service.impl;

import com.hrb.entity.SysRolePermission;
import com.hrb.mapper.SysRolePermissionMapper;
import com.hrb.service.SysRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
@Service
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermission> implements SysRolePermissionService {
   @Resource
   private SysRolePermissionMapper sysRolePermissionMapper;
    /**
     * 根据登录的id查询
     *
     * @param uId
     * @return
     */
    @Override
    public List<SysRolePermission> findByUserId(int uId) {
        return sysRolePermissionMapper.findByUserId(uId);
    }
}
