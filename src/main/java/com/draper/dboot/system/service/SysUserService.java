package com.draper.dboot.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.draper.dboot.system.entity.beans.SysUser;

/**
 * @author draper_hxy
 */
public interface SysUserService {

    Long insertUser(SysUser sysUser);

    SysUser one(String username);

    void refreshLastLogin(String username);

    Page<SysUser> page(long pageNo, long pageSize);

    SysUser one(Long id);

    long delete(Long id);
}
