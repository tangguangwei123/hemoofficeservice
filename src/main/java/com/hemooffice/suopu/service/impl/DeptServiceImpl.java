package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.dto.DeptParam;
import com.hemooffice.suopu.dto.UserDept;
import com.hemooffice.suopu.mapper.DeptMapper;
import com.hemooffice.suopu.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    /**
     * 设置部门用户
     * @param deptParam
     * @return
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public int setDeptUser(DeptParam deptParam) {
        //新增部门
        int result = deptMapper.addDept(deptParam);

        if(deptParam.getDeptUserKeys().length == 0){
            return result;
        }
        //新增部门用户
        List<UserDept> userDeptList = new ArrayList<>();
        for (int i = 0; i < deptParam.getDeptUserKeys().length; i++){
            UserDept userDept = new UserDept();
            userDept.setUserId(deptParam.getDeptUserKeys()[i]);
            userDept.setDeptId(deptParam.getDeptId());
            userDeptList.add(userDept);
        }

        deptMapper.addDeptUser(userDeptList);

        return result;
    }
}
