package com.draper.dboot.system.service;


import com.draper.dboot.system.entity.beans.SysLogEntity;

/**
 * @author draper_hxy
 */
public interface SysLogService {

    /**
     * 保存系统日志
     */
    void saveLog(SysLogEntity sysLogEntity);
}
