package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.mapper.DeptMapper;
import com.hemooffice.suopu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    /**
     * 加载指定机构所有部门
     * @param orgId
     * @return
     */
    @Override
    public List<Dept> findDeptByOrgId(Integer orgId) {

        return deptMapper.findDeptByOrgId(orgId);
    }
}
