package com.draper.dboot.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.draper.dboot.system.entity.beans.SysRole;
import com.draper.dboot.system.entity.beans.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.management.relation.Role;
import java.util.List;

/**
 * @author draper_hxy
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    List<SysRole> listByUserId(@Param("userId") Long userId);


    List<SysRole> listByUsername(@Param("username") String username);

}
