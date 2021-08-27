package com.hemooffice.suopu.service;

import com.hemooffice.suopu.dto.UserLog;

public interface SysLogService {
    /**
     * 保存日志
     * @return
     */
    int save(UserLog userLog) throws Exception;
}
