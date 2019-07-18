package com.draper.dboot.system.controller;

import com.draper.dboot.common.utils.R;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author draper_hxy
 */
@RestController
public class TestController {

    @GetMapping("/api/getSubject")
    public R testGetSubject() {
        if (SecurityUtils.getSubject().getPrincipal() == null) {
            return R.error();
        } else {
            return R.ok(SecurityUtils.getSubject().getPrincipal().toString());
        }
    }

    @RequiresRoles("超级管理员")
    @GetMapping("/api/test/role")
    public R testRole() {
        if (SecurityUtils.getSubject().getPrincipal() == null) {
            return R.error();
        } else {
            return R.ok(SecurityUtils.getSubject().hasRole("超级管理员") + "");
        }
    }
}
