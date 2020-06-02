package com.hrb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
public class SysRolePermission implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    private Integer pid;

    @TableField(exist = false)
    private List<SysRole> roles;

    @TableField(exist = false)
    private SysPermission sysPermissions;

    @TableField(exist = false)
    private SysRole sysRole;


    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public SysPermission getSysPermissions() {
        return sysPermissions;
    }

    public void setSysPermissions(SysPermission sysPermissions) {
        this.sysPermissions = sysPermissions;
    }



    @TableField(exist = false)
    private SysPermission sysPermission;


    public SysPermission getSysPermission() {
        return sysPermission;
    }

    public void setSysPermission(SysPermission sysPermission) {
        this.sysPermission = sysPermission;
    }

    public SysRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SysRole sysRole) {
        this.sysRole = sysRole;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    @Override
    public String toString() {
        return "SysRolePermission{" +
        "rid=" + rid +
        ", pid=" + pid +
        "}";
    }
}
