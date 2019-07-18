package com.draper.dboot.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author draper_hxy
 */
public class SimpleHashUtil {

    public static String doHash(String username, String password) {
        SimpleHash simpleHash = new SimpleHash("md5", password, username, 2);
        return simpleHash.toString();
    }
}
