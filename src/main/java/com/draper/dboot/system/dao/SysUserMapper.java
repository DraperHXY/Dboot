package com.draper.dboot.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.draper.dboot.system.entity.beans.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author draper_hxy
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
}
