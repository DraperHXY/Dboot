package com.draper.dboot.system.service;

import com.draper.dboot.BaseTest;
import com.draper.dboot.system.entity.beans.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author draper_hxy
 */
@Transactional
public class SysRoleServiceTest extends BaseTest {

    @Autowired
    private SysRoleService roleService;

    @Test
    void testInsert() {
        SysRole sysRole = new SysRole();
        sysRole.setName("用户");
        roleService.add(sysRole);
    }
}