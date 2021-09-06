package com.hemooffice.suopu;

import com.hemooffice.suopu.dto.UserDept;
import com.hemooffice.suopu.mapper.DeptMapper;
import com.hemooffice.suopu.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HemoOfficeServiceApplicationTests {

    @Autowired
    private DeptMapper deptMapper;
    @Test
    void contextLoads() throws UnsupportedEncodingException {
        List<UserDept> userDeptList = new ArrayList<>();
        UserDept userDept1 = new UserDept();
        userDept1.setDeptId(21);
        userDept1.setUserId(1);
        UserDept userDept2 = new UserDept();
        userDept2.setDeptId(21);
        userDept2.setUserId(2);
        userDeptList.add(userDept1);
        userDeptList.add(userDept2);
        int result = deptMapper.addDeptUser(userDeptList);
    }
}
