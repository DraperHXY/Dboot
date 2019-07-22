package com.draper.dboot.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.draper.dboot.common.entity.BaseEntity;
import com.draper.dboot.common.exception.DrException;
import com.draper.dboot.common.utils.ShiroUtil;
import com.draper.dboot.system.dao.SysUserRoleMapper;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.entity.beans.SysUserRole;
import com.draper.dboot.system.entity.vo.UserRolesVo;
import com.draper.dboot.system.service.SysRoleService;
import com.draper.dboot.system.service.SysUserRoleService;
import com.draper.dboot.system.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author draper_hxy
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysRoleService roleService;

    @Override
    public int add(long userId, long roleId) {
        // 1. 检查已经是否存在
        SysUserRole userRole;
        try {
            userRole = sysUserRoleMapper.selectOne(new QueryWrapper<SysUserRole>()
                    .lambda()
                    .eq(SysUserRole::getUserId, userId)
                    .eq(SysUserRole::getRoleId, roleId)
            );
        } catch (Exception e) {
            throw new DrException("内部错误");
        }
        if (userRole != null) {
            throw new DrException("关联已存在");
        }

        // 2. 插入
        userRole = new SysUserRole();
        userRole.setUserId(userId);
        userRole.setRoleId(roleId);
        userRole.setCreatorId(ShiroUtil.id());
        userRole.setCreateTime(LocalDateTime.now());
        return sysUserRoleMapper.insert(userRole);
    }

    @Override
    public List<SysRole> list(long userId) {
        return sysUserRoleMapper.listByUserId(userId);
    }

    @Override
    public List<SysRole> list(String username) {
        return sysUserRoleMapper.listByUsername(username);
    }

    @Override
    public UserRolesVo one(Long userId, Long pageNo, Long pageSize) {
        UserRolesVo userRolesVo = new UserRolesVo();

        // 1. userId
        userRolesVo.setUserId(userId);

        // 2. username
        SysUser user = userService.one(userId);
        if (user == null) {
            return null;
        }
        String username = user.getUsername();
        userRolesVo.setUsername(username);

        // 3. roleNameList
        List<SysRole> roleList = sysUserRoleMapper.listByUserId(userId);
        List<String> roleNameList = new ArrayList<>();
        roleList.forEach(role -> roleNameList.add(role.getName()));
        userRolesVo.setRoleList(roleNameList);

        return userRolesVo;
    }

    @Override
    public int delete(String username, String roleName) {
        SysUser user = userService.one(username);
        if (user == null) {
            return 0;
        }

        SysRole role = roleService.one(roleName);
        if (role == null) {
            return 0;
        }

        SysUserRole userRole = sysUserRoleMapper.selectOne(new QueryWrapper<SysUserRole>()
                .lambda()
                .eq(SysUserRole::getUserId, user.getId())
                .eq(SysUserRole::getRoleId, role.getId())
                .eq(BaseEntity::getIsDelete, 0)
        );
        if (userRole == null) {
            return 0;
        }

        refreshUpdate(userRole.getId());
        return sysUserRoleMapper.deleteById(userRole.getId());
    }

    void refreshUpdate(Long userRoleId) {
        SysUserRole userRole = new SysUserRole();
        userRole.setId(userRoleId);
        userRole.setUpdaterId(ShiroUtil.id());
        userRole.setUpdateTime(LocalDateTime.now());
        sysUserRoleMapper.updateById(userRole);
    }
}
