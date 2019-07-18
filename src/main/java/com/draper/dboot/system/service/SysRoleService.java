package com.draper.dboot.system.service;

import com.draper.dboot.system.entity.beans.SysRole;

import java.util.List;

/**
 * @author draper_hxy
 */
public interface SysRoleService {

    Integer add(SysRole sysRole);

    List<SysRole> listAll();

    SysRole one(int roleId);
}
