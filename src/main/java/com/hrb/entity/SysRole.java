package com.hrb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 
 * </p>
 *
 * @author Mr
 * @since 2019-12-31
 */
public class SysRole  {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String remark;

    private Integer available;

    private LocalDateTime createtime;

    @TableField(exist = false)
   private List<SysRoleUser> sysRoleUsers;

    public List<SysRoleUser> getSysRoleUsers() {
        return sysRoleUsers;
    }

    public void setSysRoleUsers(List<SysRoleUser> sysRoleUsers) {
        this.sysRoleUsers = sysRoleUsers;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "SysRole{" +
        "id=" + id +
        ", name=" + name +
        ", remark=" + remark +
        ", available=" + available +
        ", createtime=" + createtime +
        "}";
    }
}
