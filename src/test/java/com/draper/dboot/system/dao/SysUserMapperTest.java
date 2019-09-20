package com.draper.dboot.system.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.draper.dboot.BaseTest;
import com.draper.dboot.system.entity.beans.SysUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


/**
 * @author draper_hxy
 */
@Transactional
public class SysUserMapperTest extends BaseTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    void testInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setSalt("admin");
        sysUser.setStatus(1);
        SimpleHash hash = new SimpleHash("md5", "admin", sysUser.getSalt(), 2);
        sysUser.setHashPassword(hash.toString());
        sysUser.setLastLoginTime(LocalDateTime.now());
        Assert.assertEquals(1, sysUserMapper.insert(sysUser));
    }

    @Test
    void testPage() {
        Page<SysUser> page = new Page<>(1, 5);
        IPage<SysUser> sysUserIPage = sysUserMapper.selectPage(page, null);
        sysUserIPage.getRecords().forEach(System.out::println);
    }
}