package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Dept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    /**
     * 加载指定机构所有部门
     * @param orgId
     * @return
     */
    List<Dept> findDeptByOrgId(@Param("orgId") Integer orgId);
}