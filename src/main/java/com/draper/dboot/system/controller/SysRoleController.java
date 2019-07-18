package com.draper.dboot.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.config.Constant;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author draper_hxy
 */
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 显示 role 分页
     */
    @SysLog(operation = "[role] 查看列表")
    @RequiresRoles("超级管理员")
    @GetMapping("/api/role/")
    public R page(
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_START) Long pageNo,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Long pageSize) {
        Page<SysRole> rolePage = sysRoleService.page(pageNo, pageSize);
        return R.ok(rolePage);
    }

    /**
     * 添加 role
     */
    @SysLog(operation = "[role] 添加 role")
    @PostMapping("/api/role/")
    public R add(@RequestBody SysRole sysRole) {
        Long result = sysRoleService.add(sysRole);
        return R.ok(result);
    }

    /**
     * 显示特定 role
     */
    @SysLog(operation = "[role] 获取 role")
    @GetMapping("/api/role/{id}")
    public R one(@PathVariable Long id) {
        SysRole sysRole = sysRoleService.one(id);
        return R.ok(sysRole);
    }

    /**
     * 修改 role
     */
    @SysLog(operation = "[role] 修改 role")
    @PutMapping("/api/role/{id}")
    public R update(@PathVariable Long id, @RequestBody SysRole sysRole) {
        sysRole = sysRoleService.update(id, sysRole);
        return R.ok(sysRole);
    }

    /**
     * 删除 role
     */
    @SysLog(operation = "[role] 删除 role")
    @DeleteMapping("/api/role/{id}")
    public R delete(@PathVariable Long id) {
        long result = sysRoleService.delete(id);
        return R.ok(result);
    }
}
