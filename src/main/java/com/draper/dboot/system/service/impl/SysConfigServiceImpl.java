package com.draper.dboot.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.draper.dboot.common.entity.BaseEntity;
import com.draper.dboot.common.exception.DrException;
import com.draper.dboot.common.utils.ShiroUtil;
import com.draper.dboot.modules.oss.cloud.SysOssConstant;
import com.draper.dboot.system.entity.beans.SysConfigEntity;
import com.draper.dboot.system.dao.SysConfigMapper;
import com.draper.dboot.system.redis.SysConfigRedis;
import com.draper.dboot.system.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 获取云存储的配置信息
 *
 * @author draper_hxy
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigRedis redis;

    @Autowired
    private SysConfigMapper configMapper;


    @Override
    public void add(SysConfigEntity sysConfigEntity) {
        sysConfigEntity.setCreatorId(ShiroUtil.id());
        sysConfigEntity.setCreateTime(LocalDateTime.now());
        configMapper.insert(sysConfigEntity);
    }

    @Override
    public String getValue(String key) {
        SysConfigEntity ossConfig = redis.get(key);
        if (ossConfig == null) {
            LambdaQueryWrapper<SysConfigEntity> queryWrapper = new QueryWrapper<SysConfigEntity>()
                    .lambda()
                    .eq(SysConfigEntity::getKeyParam, key);
            ossConfig = configMapper.selectOne(queryWrapper);
            redis.saveOrUpdate(ossConfig);
        }
        return ossConfig == null ? null : ossConfig.getValue();
    }

    @Override
    public <T> T getSysConfigObject(String key, Class<T> clazz) {
        String value = getValue(key);
        if (StringUtils.isNotBlank(value)) {
            return JSONObject.parseObject(value, clazz);
        }
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new DrException("获取参数失败");
        }
    }

    @Override
    public void updateValueByKey(String key, String value) {
        LambdaUpdateWrapper<SysConfigEntity> updateWrapper = new UpdateWrapper<SysConfigEntity>()
                .lambda()
                .eq(SysConfigEntity::getKeyParam, key)
                .set(SysConfigEntity::getValue, value)
                .set(BaseEntity::getUpdaterId, ShiroUtil.id())
                .set(BaseEntity::getUpdateTime, LocalDateTime.now());
        configMapper.update(null, updateWrapper);
        redis.delete(SysOssConstant.CLOUD_STORAGE_CONFIG_KEY);
    }
}
