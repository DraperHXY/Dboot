package com.draper.dboot;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


/**
 * @author draper_hxy
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest
public abstract class BaseTest {

    @Test
    @BeforeEach
    void testLogin() {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken();
        usernamePasswordToken.setUsername("dev");
        usernamePasswordToken.setPassword("dev".toCharArray());
        SecurityUtils.getSubject().login(usernamePasswordToken);
    }
}
