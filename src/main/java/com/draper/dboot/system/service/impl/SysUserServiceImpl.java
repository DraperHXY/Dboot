package com.draper.dboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.draper.dboot.common.entity.BaseEntity;
import com.draper.dboot.system.dao.SysUserMapper;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author draper_hxy
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public Long insertUser(SysUser sysUser) {
        init(sysUser);
        return sysUser.getId();
    }

    @Override
    public SysUser one(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().lambda();
        return sysUserMapper.selectOne(queryWrapper.eq(SysUser::getUsername, username));
    }

    @Override
    public void refreshLastLogin(String username) {
        LambdaUpdateWrapper<SysUser> lambdaUpdateWrapper = new UpdateWrapper<SysUser>().lambda();
        lambdaUpdateWrapper.eq(SysUser::getUsername, username)
                .set(SysUser::getLastLoginTime, LocalDateTime.now());
        sysUserMapper.update(null, lambdaUpdateWrapper);
    }


    @Override
    public Page<SysUser> page(long pageNo, long pageSize) {
        Page<SysUser> page = new Page<>(pageNo, pageSize);
        sysUserMapper.selectPage(page,
                new QueryWrapper<SysUser>()
                        .lambda()
                        .eq(BaseEntity::getIsDelete, 0));
        return page;
    }

    @Override
    public SysUser one(Long id) {
        return sysUserMapper.selectOne(
                new QueryWrapper<SysUser>()
                        .lambda()
                        .eq(SysUser::getId, id)
                        .eq(BaseEntity::getIsDelete, 0
                        ));
    }

    @Override
    public long delete(Long id) {
        refreshUpdate(id);
        return sysUserMapper.deleteById(id);
    }

    private void refreshUpdate(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setId(userId);
        sysUser.setUpdateTime(LocalDateTime.now());
        sysUserMapper.updateById(sysUser);
    }

    private void init(SysUser sysUser) {
        if (sysUser.getStatus() == null) {
            sysUser.setStatus(1);
        }
        if (sysUser.getCreatorId() == null) {
            sysUser.setCreatorId(1L);
        }
        if (sysUser.getSalt() == null) {
            sysUser.setSalt(sysUser.getUsername());
        }
        sysUser.setLastLoginTime(null);
        sysUser.setCreateTime(LocalDateTime.now());
    }
}
