package com.draper.dboot.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.draper.dboot.system.entity.beans.SysRole;

import java.util.List;

/**
 * @author draper_hxy
 */
public interface SysRoleService {

    Long add(SysRole sysRole);

    List<SysRole> listAll();

    /**
     * 分页
     *
     * @param pageNo   页码
     * @param pageSize 页面大小
     */
    Page<SysRole> page(long pageNo, long pageSize);

    SysRole one(long roleId);

    SysRole one(String name);

    long delete(long id);

    SysRole update(long id, SysRole sysRole);
}
