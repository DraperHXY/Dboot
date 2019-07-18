package com.draper.dboot.common.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author draper_hxy
 */
@Data
public class BaseEntity {

    /**
     * 创建管理员 id
     */
    private Long creatorId;

    /**
     * 更新管理员 id
     */
    private Long updaterId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 逻辑删除
     * 0 未删除
     * 1 删除
     */
    @TableLogic
    private Integer isDelete;
}
