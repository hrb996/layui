package com.hrb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
public class SysUser implements Serializable {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String loginname;

    private String address;

    private Integer sex;

    private String remark;

    private String pwd;

    private Integer deptid;

    private LocalDateTime hiredate;

    private Integer mgr;

    private Integer available;

    private Integer ordernum;

    /**
     * 用户类型[0超级管理员1，管理员，2普通用户]
     */
    private Integer type;

    /**
     * 头像地址
     */
    private String imgpath;

    private String salt;

    /**
     * 分页 limit
     */
    @TableField(exist = false)
    private int currIndex;
    @TableField(exist = false)
    private int pageSize;

    @TableField(exist = false)
    private String deptTitle;

    public String getDeptTitle() {
        return deptTitle;
    }

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    @TableField(exist = false)
   private SysDept sysDept;

@TableField(exist = false)
private List<SysPermission> permissions;

    @TableField(exist = false)
    private List<SysPermission> permissions2;
    @TableField(exist = false)
    private SysPermission sysPermission;

    @TableField(exist = false)
    private List<SysRoleUser> sysRoleUsers;

    @TableField(exist = false)
    private List<SysRole> sysRoles;

    public int getCurrIndex() {
        return currIndex;
    }

    public void setCurrIndex(int currIndex) {
        this.currIndex = currIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<SysPermission> getPermissions2() {
        return permissions2;
    }

    public void setPermissions2(List<SysPermission> permissions2) {
        this.permissions2 = permissions2;
    }

    public SysPermission getSysPermission() {
        return sysPermission;
    }

    public void setSysPermission(SysPermission sysPermission) {
        this.sysPermission = sysPermission;
    }

    public List<SysRoleUser> getSysRoleUsers() {
        return sysRoleUsers;
    }

    public void setSysRoleUsers(List<SysRoleUser> sysRoleUsers) {
        this.sysRoleUsers = sysRoleUsers;
    }

    public List<SysRole> getSysRoles() {
        return sysRoles;
    }

    public void setSysRoles(List<SysRole> sysRoles) {
        this.sysRoles = sysRoles;
    }

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public List<SysPermission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysPermission> permissions) {
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }

    public LocalDateTime getHiredate() {
        return hiredate;
    }

    public void setHiredate(LocalDateTime hiredate) {
        this.hiredate = hiredate;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        "id=" + id +
        ", name=" + name +
        ", loginname=" + loginname +
        ", address=" + address +
        ", sex=" + sex +
        ", remark=" + remark +
        ", pwd=" + pwd +
        ", deptid=" + deptid +
        ", hiredate=" + hiredate +
        ", mgr=" + mgr +
        ", available=" + available +
        ", ordernum=" + ordernum +
        ", type=" + type +
        ", imgpath=" + imgpath +
        ", salt=" + salt +
        "}";
    }

    public SysUser(Integer id, String name, String loginname, String address, Integer sex, String remark, Integer deptid, Integer mgr, Integer ordernum) {
        this.id = id;
        this.name = name;
        this.loginname = loginname;
        this.address = address;
        this.sex = sex;
        this.remark = remark;
        this.deptid = deptid;
        this.mgr = mgr;
        this.ordernum = ordernum;
    }

    public SysUser(String name, String loginname, String address, Integer sex, String remark, Integer deptid, Integer mgr, Integer ordernum) {
        this.name = name;
        this.loginname = loginname;
        this.address = address;
        this.sex = sex;
        this.remark = remark;
        this.deptid = deptid;
        this.mgr = mgr;
        this.ordernum = ordernum;
    }

    public SysUser() {
    }
}
