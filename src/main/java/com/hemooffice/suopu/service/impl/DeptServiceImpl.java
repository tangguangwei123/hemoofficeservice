package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Dept;
import com.hemooffice.suopu.dto.DeptParam;
import com.hemooffice.suopu.dto.DeptUserRelationship;
import com.hemooffice.suopu.dto.User;
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
     * 设置部门
     * @param deptParam
     * @return
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public int setDeptUser(DeptParam deptParam) {
        System.out.print("提交的数据:"+deptParam.toString());
        //如果deptId == null则新增部门 ，如果不为null 则编辑部门
        int result = 0;
        if(deptParam.getDeptId() == null){
            //新增部门
            result = deptMapper.addDept(deptParam);

        }else {
            //编辑部门
            deptMapper.updateDept(deptParam);
            //删除关联用户
            deptMapper.deleteDeptUser(deptParam.getDeptId());
        }

        if(deptParam.getDeptUserKeys().length == 0){
            return result;
        }
        //新增部门用户
        List<DeptUserRelationship> userDeptList = new ArrayList<>();
        for (int i = 0; i < deptParam.getDeptUserKeys().length; i++){
            DeptUserRelationship userDept = new DeptUserRelationship();
            userDept.setUserId(deptParam.getDeptUserKeys()[i]);
            userDept.setDeptId(deptParam.getDeptId());
            userDeptList.add(userDept);
        }

        deptMapper.addDeptUser(userDeptList);

        return result;
    }

    /**
     * 删除部门
     * @param deptId
     * @return
     */
    @Override
    @Transactional(rollbackFor={Exception.class})
    public int deleteDept(Integer deptId) {
        //先删除部门下面用户
        deptMapper.deleteDeptUser(deptId);
        //然后删除部门
        int result = deptMapper.deleteDept(deptId);

        return result;
    }

    /**
     * 加载当前机构所有部门和部门下用户
     * @return
     */
    public List<Dept> findDeptListAndUserList(Integer orgId){
        return deptMapper.findDeptListAndUserList(orgId);
    }

    /**
     * 查询指定部门下面用户
     * @param deptId
     * @return
     */
    @Override
    public List<User> findUsersByDeptId(Integer deptId) {
        return deptMapper.findUsersByDeptId(deptId);
    }
}
