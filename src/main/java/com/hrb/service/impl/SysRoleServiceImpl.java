package com.hrb.service.impl;

import com.hrb.entity.SysRole;
import com.hrb.entity.SysRoleUser;
import com.hrb.mapper.SysRoleMapper;
import com.hrb.service.SysRoleService;
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
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
@Resource
  private SysRoleMapper sysRoleMapper;
    /**
     * 根据Roleuser表的id查询(登录的id)
     *
     * @param rUid
     * @return
     */
    @Override
    public List<SysRoleUser> findSysRoleUserByUserId(int rUid) {
        return sysRoleMapper.findSysRoleUserByUserId(rUid);
    }
}
