package com.hemooffice.suopu.controller;

import com.hemooffice.suopu.dto.Msg;
import com.hemooffice.suopu.dto.OaActCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/act/")
public class ActivitiManageController {
    //日志
    private static final Logger logger = LoggerFactory.getLogger(ActivitiManageController.class);

    @PostMapping("/add-category")
    public Msg addActCategory(@Validated @RequestBody OaActCategory oaActCategory){

        return Msg.success(null);
    }
}
