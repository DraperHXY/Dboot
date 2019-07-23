package com.draper.dboot.system.service;

import com.draper.dboot.system.entity.beans.SysConfigEntity;

/**
 * @author draper_hxy
 */
public interface SysConfigService {

    void add(SysConfigEntity sysConfigEntity);

    String getValue(String key);

    /**
     * 通过不同的 keyParam 来获取不同的配置
     */
    <T> T getSysConfigObject(String key, Class<T> clazz);

    void updateValueByKey(String key, String value);
}
