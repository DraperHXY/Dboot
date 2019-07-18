package com.draper.dboot.system.service;

import com.draper.dboot.system.entity.beans.SysUser;

/**
 * @author draper_hxy
 */
public interface SysUserService {

    Long insertUser(SysUser sysUser);

    SysUser getUser(String username);

    void refreshLastLogin(String username);
}
