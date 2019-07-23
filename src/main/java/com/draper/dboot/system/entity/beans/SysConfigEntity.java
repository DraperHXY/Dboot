package com.draper.dboot.system.entity.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.draper.dboot.common.entity.BaseEntity;
import lombok.Data;

/**
 * @author draper_hxy
 */
@Data
@TableName("sys_config")
public class SysConfigEntity extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String keyParam;

    private String value;
}
