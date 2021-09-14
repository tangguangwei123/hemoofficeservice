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
        Set<Permission> set = new HashSet<>();

        Permission p1 = new Permission();
        Permission p2 = new Permission();
        Permission p3 = new Permission();
        Permission p4 = new Permission();

        p1.setId(1);
        p2.setId(1);
        p3.setId(1);
        p4.setId(1);

        set.add(p1);
        set.add(p2);
        set.add(p3);
        set.add(p4);

        Iterator<Permission> it = set.iterator();
        while (it.hasNext()) {
            Permission permission = it.next();
            System.out.println(permission.toString());
        }
    }
}
