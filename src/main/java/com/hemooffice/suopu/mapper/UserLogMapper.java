package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.UserLog;
import java.util.List;

public interface UserLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(UserLog record);

    UserLog selectByPrimaryKey(Integer logId);

    List<UserLog> selectAll();

    int updateByPrimaryKey(UserLog record);
}