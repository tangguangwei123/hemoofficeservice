package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.service.DeptService;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    /**
     * 加载指定机构所有部门
     * @param orgId
     * @return
     */
    @Override
    public Dept findDeptByOrgId(Integer orgId) {
        return null;
    }
}
