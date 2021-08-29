package com.hemooffice.suopu.service.impl;

import com.hemooffice.suopu.dto.Role;
import com.hemooffice.suopu.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public List<Role> findRoleListByUserId(Integer userId) {
        return null;
    }
}
