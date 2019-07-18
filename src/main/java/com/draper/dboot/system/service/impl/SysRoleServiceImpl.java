package com.draper.dboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.draper.dboot.common.entity.BaseEntity;
import com.draper.dboot.common.utils.ShiroUtil;
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
    public Long add(SysRole sysRole) {
        sysRole.setCreatorId(ShiroUtil.id());
        sysRole.setCreateTime(LocalDateTime.now());
        sysRoleMapper.insert(sysRole);
        return sysRole.getId();
    }

    @Override
    public List<SysRole> listAll() {
        return sysRoleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public Page<SysRole> page(long pageNo, long pageSize) {
        Page<SysRole> sysRoleIPage = new Page<>(pageNo, pageSize);
        sysRoleMapper.selectPage(sysRoleIPage, new QueryWrapper<SysRole>().lambda().eq(BaseEntity::getIsDelete, 0));
        return sysRoleIPage;
    }

    @Override
    public SysRole one(long roleId) {
        return sysRoleMapper.selectById(roleId);
    }

    @Override
    public SysRole one(String name) {
        return sysRoleMapper.selectOne(new QueryWrapper<SysRole>()
                .lambda()
                .eq(SysRole::getName, name)
                .eq(BaseEntity::getIsDelete, 0));
    }

    @Override
    public long delete(long id) {
        // 1. 更新刷新时间
        update(id, new SysRole());

        // 2. 逻辑删除
        return sysRoleMapper.deleteById(id);
    }

    @Override
    public SysRole update(long id, SysRole sysRole) {
        // 1. 设置 id
        sysRole.setId(id);

        // 2. 设置更新者 id
        sysRole.setUpdaterId(ShiroUtil.id());

        // 3. 刷新更新时间
        sysRole.setUpdateTime(LocalDateTime.now());

        // 4. 更新
        sysRoleMapper.updateById(sysRole);
        return sysRoleMapper.selectById(id);
    }
}
