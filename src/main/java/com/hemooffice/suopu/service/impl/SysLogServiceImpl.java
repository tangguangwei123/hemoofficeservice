package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.UserLog;
import com.hemooffice.suopu.mapper.UserLogMapper;
import com.hemooffice.suopu.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private UserLogMapper userLogMapper;
    /**
     * 保存日志
     *
     * @param
     * @return
     */
    @Override
    public int save(UserLog userLog) throws Exception {
        return userLogMapper.insert(userLog);
    }
}
