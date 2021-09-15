package com.hemooffice.suopu;

import com.hemooffice.suopu.dto.Permission;
import com.hemooffice.suopu.mapper.DeptMapper;
import com.hemooffice.suopu.mapper.RoleMapper;
import com.hemooffice.suopu.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.*;

@SpringBootTest
class HemoOfficeServiceApplicationTests {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Test
    void contextLoads() throws UnsupportedEncodingException {
        System.out.print(roleMapper.findRoleByUserId(1,1));
    }
}
