package com.hrb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hrb.entity.SysDept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr
 * @since 2020-01-01
 */

public interface SysDeptMapper extends BaseMapper<SysDept> {

    @Select("SELECT *FROM sys_dept WHERE pid  IN (#{list)")
    public List<SysDept> erjiTree (@Param("id") List<Integer> list);
}
