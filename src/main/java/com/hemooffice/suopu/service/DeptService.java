package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.dto.DeptParam;

import java.util.List;

public interface DeptService {
    /**
     * 加载指定机构所有部门
     * @param orgId
     * @return
     */
    public List<Dept> findDeptByOrgId(Integer orgId);

    /**
     * 设置部门
     * @param deptParam
     * @return
     */
    public int setDeptUser(DeptParam deptParam);

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    public int deleteDept(Integer deptId);
}
