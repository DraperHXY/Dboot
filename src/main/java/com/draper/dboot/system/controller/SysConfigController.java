package com.draper.dboot.system.controller;

import com.draper.dboot.common.utils.R;
import com.draper.dboot.system.entity.beans.SysConfigEntity;
import com.draper.dboot.system.entity.dto.SysConfigDto;
import com.draper.dboot.system.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author draper_hxy
 */
@RestController
public class SysConfigController {

    @Autowired
    private SysConfigService sysConfigService;

    @PostMapping("/api/sys/")
    public R add(@RequestBody SysConfigDto configDto) {
        SysConfigEntity configEntity = new SysConfigEntity();
        configEntity.setKeyParam(configDto.getKey());
        configEntity.setValue(configDto.getValue());
        sysConfigService.add(configEntity);
        return R.ok();
    }
}
