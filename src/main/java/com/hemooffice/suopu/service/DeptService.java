package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.Dept;

public interface DeptService {
    /**
     * 加载指定机构所有部门
     * @param orgId
     * @return
     */
    public Dept findDeptByOrgId(Integer orgId);
}
