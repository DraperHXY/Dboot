package com.draper.dboot.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.utils.MapUtil;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.entity.dto.LoginDto;
import com.draper.dboot.system.service.SysUserRoleService;
import com.draper.dboot.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.rmi.Naming.list;

/**
 * 系统登录
 *
 * @author draper_hxy
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @SysLog(operation = "登录")
    @PostMapping(value = "/api/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public R login(@Valid @RequestBody LoginDto loginDto) {

        // 1.登录
        UsernamePasswordToken authToken = new UsernamePasswordToken();
        authToken.setUsername(loginDto.getUsername());
        authToken.setPassword(loginDto.getPassword().toCharArray());
        SecurityUtils.getSubject().login(authToken);

        // 2.刷新登录时间
        sysUserService.refreshLastLogin(loginDto.getUsername());

        // 3.封装结果
        List<SysRole> roleList = sysUserRoleService.list(loginDto.getUsername());
        List<String> roleStrList = new ArrayList<>();
        roleList.forEach(role -> roleStrList.add(role.getName()));

        HashMap map = new MapUtil()
                .put("roles", roleStrList)
                .put("username", loginDto.getUsername());

        // TODO: 2019-07-20 登录成功返回 username roles
        return R.ok(map);
    }

    @SysLog(operation = "注销")
    @GetMapping("/api/logout")
    public R logout() {
        SecurityUtils.getSubject().logout();
        return R.ok();
    }
}
