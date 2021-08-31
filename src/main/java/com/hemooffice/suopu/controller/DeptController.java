package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeptController {

    private static final Logger logger = LoggerFactory.getLogger(DeptController.class);

    /**
     * 获取登录机构部门列表
     * @return
     */
    public Msg findDeptListByLoginOrgId(){

        return Msg.success(null);
    }
}
