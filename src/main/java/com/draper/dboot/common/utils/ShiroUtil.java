package com.draper.dboot.common.utils;

import com.draper.dboot.system.entity.beans.SysUser;
import com.draper.dboot.system.service.SysUserService;
import org.apache.shiro.SecurityUtils;


/**
 * @author draper_hxy
 */
public class ShiroUtil {

    public static long id() {
        Object principal = SecurityUtils.getSubject().getPrincipal();

        if (principal == null) {
            return 0L;
        }

        if (principal instanceof SysUser) {
            return ((SysUser) principal).getId();
        }

        if (principal instanceof String) {
            return ((SysUserService) SpringContextUtils.getBean("sysUserServiceImpl")).getUser(principal.toString()).getId();

        }
        return 0L;
    }
}
