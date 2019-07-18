package com.draper.dboot.system.entity.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.draper.dboot.common.entity.BaseEntity;
import lombok.Data;

/**
 * @author draper_hxy
 */
@Data
public class SysUserRole extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long roleId;
}
