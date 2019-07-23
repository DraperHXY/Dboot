package com.draper.dboot.modules.oss.controller;

import com.alibaba.fastjson.JSONObject;
import com.draper.dboot.common.annotation.SysLog;
import com.draper.dboot.common.exception.DrException;
import com.draper.dboot.common.utils.R;
import com.draper.dboot.modules.oss.cloud.SysOssConstant;
import com.draper.dboot.modules.oss.entity.SysCloudStorageConfig;
import com.draper.dboot.modules.oss.cloud.SysOssFactory;
import com.draper.dboot.modules.oss.entity.SysOssEntity;
import com.draper.dboot.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author draper_hxy
 */
@RestController
public class SysOssController {
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = SysOssConstant.CLOUD_STORAGE_CONFIG_KEY;

    @SysLog(operation = "[sys][oss] 更新存储")
    @PostMapping("/api/sys/oss/")
    public R saveConfig(@RequestBody SysCloudStorageConfig config) {
        sysConfigService.updateValueByKey(KEY, JSONObject.toJSONString(config));

        return R.ok();
    }

    @SysLog(operation = "[sys][oss] 上传文件")
    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new DrException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = SysOssFactory.build().uploadSuffix(file.getBytes(), suffix);

        //保存文件信息
        SysOssEntity ossEntity = new SysOssEntity();
        ossEntity.setUrl(url);
        ossEntity.setCreateTime(LocalDateTime.now());

        return R.ok().put("url", url);
    }
}
