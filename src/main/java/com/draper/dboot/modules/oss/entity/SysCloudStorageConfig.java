package com.draper.dboot.modules.oss.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author draper_hxy
 */
@Data
public class SysCloudStorageConfig {

    private Integer type;

    /**
     * 七牛绑定的域名
     */
    private String qiniuDomain;

    /**
     * 七牛路径的前缀
     */
    private String qiniuPrefix;

    /**
     * 七牛ACCESS_KEY
     */
    private String qiniuAccessKey;

    /**
     * 七牛SECRET_KEY
     */
    private String qiniuSecretKey;

    /**
     * 七牛存储空间名
     */
    private String qiniuBucketName;

}
