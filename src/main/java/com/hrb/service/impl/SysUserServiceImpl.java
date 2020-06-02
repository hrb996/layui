package com.hrb.service.impl;

import com.hrb.entity.SysUser;
import com.hrb.mapper.SysUserMapper;
import com.hrb.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.map.HashedMap;
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
 * @since 2019-12-31
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
@Resource
  private SysUserMapper sysUserMapper;
    @Override
    public SysUser login(String userName) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("loginname",userName);
//        map.put("pwd",password);
        List<SysUser> list  =sysUserMapper.selectByMap(map);
        if(list != null){
            for(SysUser sysUser : list){
                System.out.println("loginname"+userName);
                return sysUser;
            }
        }
        return null;
    }

    @Override
    public int maxOrdernum() {
        return sysUserMapper.maxOrdernum();
    }
}
