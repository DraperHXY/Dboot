package com.draper.dboot.system.entity.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.draper.dboot.common.entity.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author draper_hxy
 */
@Data
@Accessors(chain = true)
public class SysUser extends BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String hashPassword;

    private String salt;

    /**
     * 0 禁用
     * 1 启用
     */
    private Integer status;

    private LocalDateTime lastLoginTime;
}
