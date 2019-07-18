package com.draper.dboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
    public SysUser getUser(String username) {
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
