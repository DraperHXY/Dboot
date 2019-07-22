package com.draper.dboot.system.dao;

import com.draper.dboot.system.entity.beans.SysRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author draper_hxy
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class SysUserRoleMapperTest {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Test
    void testRoleList() {
        List<SysRole> sysRoleList = sysUserRoleMapper.listByUserId(1L);
        sysRoleList.forEach(System.out::println);
    }

}