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
     * @param deptId
     * @return
     */
    int deleteDeptUser(@Param("deptId") Integer deptId);

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    int deleteDept(@Param("deptId") Integer deptId);

    /**
     * 查询指定部门下面用户
     * @param deptId
     * @return
     */
    List<Integer> findUserIdsByDeptId(@Param("deptId") Integer deptId);

    /**
     * 查找子部门通过部门id
     * @param deptId
     * @return
     */
    List<Dept> findChildrenDeptByDeptId(@Param("deptId") Integer deptId);
}