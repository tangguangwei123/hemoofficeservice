package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.dto.DeptParam;
import com.hemooffice.suopu.dto.User;
import com.hemooffice.suopu.exception.CusAuthException;

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
     * @param orgId
     * @param deptId
     * @return
     */
    public int deleteDept(Integer orgId, Integer deptId) throws CusAuthException;

    /**
     * 加载当前机构所有部门和部门下用户
     * @param orgId
     * @return
     */
    public List<Dept> findDeptListAndUserList(Integer orgId);

    /**
     *
     * @param deptId
     * @return
     */
    public List<User> findUsersByDeptId(Integer deptId);
}
