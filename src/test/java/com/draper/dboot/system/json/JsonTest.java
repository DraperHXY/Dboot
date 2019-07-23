package com.draper.dboot.system.json;

import com.alibaba.fastjson.JSONObject;
import com.draper.dboot.modules.oss.entity.SysCloudStorageConfig;
import org.junit.jupiter.api.Test;

/**
 * @author draper_hxy
 */
public class JsonTest {

    @Test
    void testConvertSysCloudStorageConfig() {
        String jsonStr = "{\"qiniuAccessKey\":\"U9tZuPjewm6OlfpyzVkEfDLWdvqWW1vN34c26JML\",\"qiniuBucketName\":\"Dboot\",\"qiniuDomain\":\"clouddn.com/qiniucdn.com/qiniudn.com/qnssl.com/qbox.me \",\"qiniuPrefix\":\"test\",\"qiniuSecretKey\":\"9lc-m-puvE1JbRZbq5ZFZYCd3F2FhOVGysiyNsk_\",\"type\":1}";
        SysCloudStorageConfig config = JSONObject.parseObject(jsonStr, SysCloudStorageConfig.class);
        System.out.println(config);
    }

}
