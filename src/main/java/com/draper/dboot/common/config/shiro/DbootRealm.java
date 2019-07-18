package com.draper.dboot.common.config.shiro;

import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.service.SysUserRoleService;
import com.draper.dboot.system.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author draper_hxy
 */
@Slf4j
public class DbootRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            log.error("principals must not null");
            return null;
        } else {
            log.info(principals.getPrimaryPrincipal().toString());
        }

        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        sysUserService.one(sysUser.getUsername());

        // 1. 获取角色
        List<SysRole> sysRoleList = sysUserRoleService.list(sysUser.getId());
        Set<String> roleSets = new HashSet<>();
        sysRoleList.forEach(sysRole -> roleSets.add(sysRole.getName()));

        // 2. 添加角色
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleSets);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1. 查询用户是否存在
        String username = token.getPrincipal().toString();
        SysUser sysUser = sysUserService.one(username);
        if (sysUser == null) {
            throw new UnknownAccountException("账号不存在");
        }

        // 2. 交给 Shiro 进行校验
        return new SimpleAuthenticationInfo(
                // 把整个 user 传入
                sysUser,
                // 此为数据库查出的密码
                sysUser.getHashPassword(),
                // salt
                ByteSource.Util.bytes(username),
                getName()
        );
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
}
