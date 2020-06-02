package com.hrb.service.impl;

import com.hrb.entity.SysPermission;
import com.hrb.mapper.SysPermissionMapper;
import com.hrb.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {
  @Resource
   private SysPermissionMapper sysPermissionMapper;
    @Override
    public List<SysPermission> findList(Map<String, Object> map) {
        map.put("pid",1);
        return sysPermissionMapper.selectByMap(map);
    }

    /**
     * 是否是系统管理员   显示一级菜单
     *
     * @return
     */
    @Override
    public List<SysPermission> findYiji(Integer id) {
        return sysPermissionMapper.findYiji(id);
    }

    /**
     * 根据登录人显示  显示2级菜单
     *
     * @param pid@return
     */
    @Override
    public List<SysPermission> finderji(int pid) {
        return sysPermissionMapper.finderji(pid);
    }
}
