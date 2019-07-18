package com.draper.dboot.system.controller;

import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.entity.dto.LoginDto;
import com.draper.dboot.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 系统登录，注册的 controller
 *
 * @author draper_hxy
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @SysLog(operation = "登录")
    @PostMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public R login(@Valid @RequestBody LoginDto loginDto) {

        // 1.登录
        UsernamePasswordToken authToken = new UsernamePasswordToken();
        authToken.setUsername(loginDto.getUsername());
        authToken.setPassword(loginDto.getPassword().toCharArray());

        // 2.刷新登录时间
        SecurityUtils.getSubject().login(authToken);
        sysUserService.refreshLastLogin(loginDto.getUsername());
        return R.ok();
    }


}
