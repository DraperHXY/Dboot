package com.draper.dboot.system.service.impl;

import com.draper.dboot.system.dao.SysLogMapper;
import com.draper.dboot.system.entity.beans.SysLogEntity;
import com.draper.dboot.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author draper_hxy
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void saveLog(SysLogEntity sysLogEntity) {
        sysLogMapper.insert(sysLogEntity);
    }
}
