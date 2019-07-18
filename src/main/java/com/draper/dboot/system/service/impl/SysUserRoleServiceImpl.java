package com.draper.dboot.system.service.impl;

import com.draper.dboot.system.dao.SysUserRoleMapper;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.entity.beans.SysUserRole;
import com.draper.dboot.system.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author draper_hxy
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int add(long userId, long roleId) {
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setCreateTime(LocalDateTime.now());
        return sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public List<SysRole> list(long userId) {
        return sysUserRoleMapper.list(userId);
    }
}
