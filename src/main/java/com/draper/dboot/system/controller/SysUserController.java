package com.draper.dboot.system.controller;

import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.common.utils.SimpleHashUtil;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.entity.dto.LogUpDto;
import com.draper.dboot.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author draper_hxy
 */
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @SysLog(operation = "用户注册")
    @PostMapping("/api/user")
    public R logUp(@RequestBody LogUpDto logUpDto) {
        String username = logUpDto.getUsername();
        String hashPassword = SimpleHashUtil.doHash(username, logUpDto.getPassword());

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setHashPassword(hashPassword);
        sysUserService.insertUser(sysUser);
        return R.ok();
    }
}
