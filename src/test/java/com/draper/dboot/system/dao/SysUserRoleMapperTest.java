package com.draper.dboot.system.dao;

import com.draper.dboot.BaseTest;
import com.draper.dboot.system.entity.beans.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author draper_hxy
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class SysUserRoleMapperTest  {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Test
    void testRoleList() {
        List<SysRole> sysRoleList = sysUserRoleMapper.list(1L);
        sysRoleList.forEach(System.out::println);
    }

}