package com.draper.dboot.modules.oss.cloud;

/**
 * @author draper_hxy
 */
public class SysOssConstant {

    public final static String CLOUD_STORAGE_CONFIG_KEY = "CLOUD_STORAGE_CONFIG_KEY";

    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
