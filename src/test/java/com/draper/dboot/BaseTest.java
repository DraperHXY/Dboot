package com.draper.dboot;

import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.config.shiro.DbootRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * @author draper_hxy
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public abstract class BaseTest {

    @Autowired
    private DbootRealm dbootRealm;

    @Test
    @BeforeEach
    @SysLog(operation = "登录")
    void testLogin() {
        log.info("测试用例，登录");
        SecurityManager securityManager = new DefaultSecurityManager();
        ((DefaultSecurityManager) securityManager).setRealm(dbootRealm);

        ThreadContext.bind(securityManager);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.setUsername("dev");
        usernamePasswordToken.setPassword("dev".toCharArray());
        SecurityUtils.getSubject().login(usernamePasswordToken);
    }
}
