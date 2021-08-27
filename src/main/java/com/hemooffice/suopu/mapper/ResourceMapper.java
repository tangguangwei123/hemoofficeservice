package com.hemooffice.suopu.mapper;

import com.hemooffice.suopu.dto.Resource;
import java.util.List;

public interface ResourceMapper {
    /**
     * 根据资源ID加载资源
     * @param id
     * @return
     */
    Resource selectByPrimaryKey(Integer id);

    /**
     * 加载所有资源
     * @return
     */
    List<Resource> selectAll();

    /**
     * 根据资源ID更新资源
     * @param record
     * @return
     */
    int updateByPrimaryKey(Resource record);
}