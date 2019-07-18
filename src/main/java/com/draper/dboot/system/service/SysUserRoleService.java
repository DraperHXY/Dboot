package com.draper.dboot.system.service;

import com.draper.dboot.system.entity.beans.SysRole;

import java.util.List;

/**
 * @author draper_hxy
 */
public interface SysUserRoleService {

    int add(long userId, long roleId);

    List<SysRole> list(long userId);
}
