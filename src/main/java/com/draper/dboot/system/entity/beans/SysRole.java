package com.draper.dboot.system.entity.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.draper.dboot.common.entity.BaseEntity;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author draper_hxy
 */
@Data
public class SysRole extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    @NotBlank
    private String name;
}
