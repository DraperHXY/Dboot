package com.draper.dboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.draper.dboot.system.dao.SysRoleMapper;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author draper_hxy
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public Integer add(SysRole sysRole) {
        init(sysRole);

        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public List<SysRole> listAll() {
        return sysRoleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public SysRole one(int roleId) {
        return sysRoleMapper.selectById(roleId);
    }

    private void init(SysRole sysRole) {
        if (sysRole.getCreatorId() == null) {
            sysRole.setCreatorId(1L);
        }
        sysRole.setCreateTime(LocalDateTime.now());
    }
}
