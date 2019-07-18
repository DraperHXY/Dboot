package com.draper.dboot.system.service;

import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.entity.vo.UserRolesVo;

import java.util.List;

/**
 * @author draper_hxy
 */
public interface SysUserRoleService {

    int add(long userId, long roleId);

    List<SysRole> list(long userId);

    UserRolesVo one(Long userId, Long pageNo, Long pageSize);

    int delete(String username, String roleName);
}
