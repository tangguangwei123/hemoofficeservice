package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.dto.UserDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {
    /**
     * 加载指定机构所有部门
     * @param orgId
     * @return
     */
    List<Dept> findDeptByOrgId(@Param("orgId") Integer orgId);

    /**
     * 新增部门
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 添加部门用户
     * @param userDeptList
     * @return
     */
    int addDeptUser(@Param("userDeptList") List<UserDept> userDeptList);

    /**
     * 删除部门用户
     * @param userDeptList
     * @return
     */
    int deleteDeptUser(@Param("userDeptList") List<UserDept> userDeptList);
}