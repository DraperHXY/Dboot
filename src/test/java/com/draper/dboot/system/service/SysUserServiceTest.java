package com.draper.dboot.system.service;

import com.draper.dboot.BaseTest;
import com.draper.dboot.system.entity.beans.SysUser;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


/**
 * @author draper_hxy
 */
//@Transactional
public class SysUserServiceTest extends BaseTest {

    @Autowired
    private SysUserService userService;

    @Test
    void testInsert() {
        SysUser sysUser = new SysUser();
        sysUser.setUsername("admin");
        sysUser.setSalt("admin");
        sysUser.setStatus(1);
        SimpleHash hash = new SimpleHash("md5", "admin", sysUser.getSalt(), 2);
        sysUser.setHashPassword(hash.toString());
        sysUser.setLastLoginTime(LocalDateTime.now());
        userService.insertUser(sysUser);
    }

    @Test
    void testGet() {
        SysUser sysUser = userService.getUser("admin");
        System.out.println(sysUser.getLastLoginTime());
    }
}