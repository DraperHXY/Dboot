package com.draper.dboot.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.config.Constant;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.common.utils.SimpleHashUtil;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.entity.dto.LogUpDto;
import com.draper.dboot.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author draper_hxy
 */
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @SysLog(operation = "[user] 注册")
    @PostMapping("/api/user/")
    public R logUp(@Valid @RequestBody LogUpDto logUpDto) {
        String username = logUpDto.getUsername();
        String hashPassword = SimpleHashUtil.doHash(username, logUpDto.getPassword());

        SysUser sysUser = new SysUser();
        sysUser.setUsername(username);
        sysUser.setHashPassword(hashPassword);
        sysUserService.insertUser(sysUser);
        return R.ok();
    }

    @SysLog(operation = "[user] 查看列表")
    @GetMapping("/api/user/")
    public R page(@RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_START) Long pageNo,
                  @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Long pageSize) {
        Page<SysUser> sysUserPage = sysUserService.page(pageNo, pageSize);
        return R.ok(sysUserPage);
    }

    @SysLog(operation = "[user] 获取")
    @GetMapping("/api/user/{id}")
    public R one(@PathVariable Long id) {
        SysUser sysUser = sysUserService.one(id);
        return R.ok(sysUser);
    }

    @SysLog(operation = "[user] 删除")
    @DeleteMapping("/api/user/{id}")
    public R delete(@PathVariable Long id) {
        return R.ok(sysUserService.delete(id));
    }
}
