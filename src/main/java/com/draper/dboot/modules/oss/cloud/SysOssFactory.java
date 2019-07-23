package com.draper.dboot.modules.oss.cloud;

import com.draper.dboot.common.utils.SpringContextUtils;
import com.draper.dboot.modules.oss.entity.SysCloudStorageConfig;
import com.draper.dboot.system.service.SysConfigService;

/**
 * @author draper_hxy
 */
public class SysOssFactory {

    private static SysConfigService sysConfigService;

    static {
        SysOssFactory.sysConfigService = (SysConfigService) SpringContextUtils.getBean("sysConfigServiceImpl");
    }

    public static SysCloudStorageService build() {
        SysCloudStorageConfig config = sysConfigService.getSysConfigObject(SysOssConstant.CLOUD_STORAGE_CONFIG_KEY, SysCloudStorageConfig.class);

        if (config.getType() == SysOssConstant.CloudService.QINIU.getValue()) {
            return new QiNiuCloudStorageService(config);
        }
        return null;
    }
}
