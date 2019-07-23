package com.draper.dboot.system.service;

import com.draper.dboot.BaseTest;
import com.draper.dboot.common.utils.ShiroUtil;
import com.draper.dboot.modules.oss.cloud.SysOssConstant;
import com.draper.dboot.system.entity.beans.SysConfigEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author draper_hxy
 */
public class SysConfigServiceTest extends BaseTest {

    @Autowired
    private SysConfigService sysConfigService;

    @Test
    public void add() {
        SysConfigEntity configEntity = new SysConfigEntity();
        configEntity.setKeyParam(SysOssConstant.CLOUD_STORAGE_CONFIG_KEY);
        configEntity.setValue("{}");
        configEntity.setCreatorId(ShiroUtil.id());
        configEntity.setCreateTime(LocalDateTime.now());
        sysConfigService.add(configEntity);
    }
}