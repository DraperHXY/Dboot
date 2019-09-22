package com.draper.dboot.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author draper_hxy
 */
public class NacosUserTest {

    @Test
    public void testCryptPassword(){
        System.out.println(new BCryptPasswordEncoder().encode("nacos"));
    }

}
