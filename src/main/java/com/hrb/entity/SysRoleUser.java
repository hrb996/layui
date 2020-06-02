package com.hrb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mr
 * @since 2020-01-01
 */
public class SysRoleUser implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;

    private Integer uid;



    @TableField(exist = false)
   private List<SysRole> roles;

    @TableField(exist = false)
    private List<SysUser> sysUsers;

    @TableField(exist = false)
    private SysRole role;

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysUser> getSysUsers() {
        return sysUsers;
    }

    public void setSysUsers(List<SysUser> sysUsers) {
        this.sysUsers = sysUsers;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }



    @Override
    public String toString() {
        return "SysRoleUser{" +
        "rid=" + rid +
        ", uid=" + uid +
        "}";
    }
}
