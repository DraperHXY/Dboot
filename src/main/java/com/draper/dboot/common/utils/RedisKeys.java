package com.draper.dboot.common.utils;

/**
 * @author draper_hxy
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
