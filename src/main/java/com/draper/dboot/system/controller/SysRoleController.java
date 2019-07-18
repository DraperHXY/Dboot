package com.draper.dboot.system.controller;

import com.draper.dboot.common.utils.R;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author draper_hxy
 */
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 显示 role 列表
     */
    @RequiresRoles("超级管理员")
    @GetMapping("/api/role/")
    public R list() {
        List<SysRole> roleList = sysRoleService.listAll();
        return R.ok(roleList);
    }

    /**
     * 显示特定 role
     */
    @GetMapping("/api/role/{id}")
    public R one(@PathVariable Integer id) {
        SysRole sysRole = sysRoleService.one(id);
        return R.ok(sysRole);
    }
}
