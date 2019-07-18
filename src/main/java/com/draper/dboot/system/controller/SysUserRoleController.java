package com.draper.dboot.system.controller;

import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.config.Constant;
import com.draper.dboot.common.exception.DrException;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.entity.dto.UserRoleDto;
import com.draper.dboot.system.entity.vo.UserRolesVo;
import com.draper.dboot.system.service.SysRoleService;
import com.draper.dboot.system.service.SysUserRoleService;
import com.draper.dboot.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author draper_hxy
 */
@RestController
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService userRoleService;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @SysLog(operation = "[user role] 关联角色")
    @PostMapping("/api/user/role/")
    public R correlate(@Valid @RequestBody UserRoleDto userRoleDto) {
        String username = userRoleDto.getUsername();
        String roleName = userRoleDto.getRoleName();

        SysUser user = userService.one(username);
        if (user == null) {
            throw new DrException("用户不存在");
        }
        long userId = user.getId();

        SysRole role = roleService.one(roleName);
        if (role == null) {
            throw new DrException("角色不存在");
        }
        long roleId = roleService.one(roleName).getId();

        int result = userRoleService.add(userId, roleId);
        return R.ok(result);
    }

    @SysLog(operation = "[user role] 查看角色")
    @GetMapping("/api/user/{userId}/role/")
    public R page(@PathVariable Long userId,
                  @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_START) Long pageNo,
                  @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE_SIZE) Long pageSize) {
        UserRolesVo userRolesVoPage = userRoleService.one(userId, pageNo, pageSize);
        return R.ok(userRolesVoPage);
    }

    @PutMapping("/api/user/role/")
    public R unCorrelate(@Valid @RequestBody UserRoleDto userRoleDto) {
        String username = userRoleDto.getUsername();
        String roleName = userRoleDto.getRoleName();
        int result = userRoleService.delete(username, roleName);
        return R.ok(result);
    }
}
