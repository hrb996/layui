package com.hrb.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hrb.entity.SysRoleUser;
import com.hrb.entity.SysUser;
import com.hrb.mapper.SysRoleUserMapper;
import com.hrb.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr
 * @since 2020-01-01
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {

    @Resource
    private SysRoleUserMapper sysRoleUserMapper;
    /**
     * 根据登录的用户查询
     *
     * @param sysUser
     */
    @Override
    public List<SysRoleUser> findByUser(SysUser sysUser) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("sysUsers",sysUser);
        return sysRoleUserMapper.selectByMap(map);
    }

    @Override
    public List<SysRoleUser> findByUserId(int id) {
        return sysRoleUserMapper.findByUserId(id);
    }
}
