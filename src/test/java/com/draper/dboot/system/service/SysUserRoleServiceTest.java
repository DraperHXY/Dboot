package com.draper.dboot.system.service;

import com.draper.dboot.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @author draper_hxy
 */
@Transactional
public class SysUserRoleServiceTest extends BaseTest {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Test
    void testAdd() {
        sysUserRoleService.add(5, 1);
    }

}