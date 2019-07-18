package com.draper.dboot.system.entity.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author draper_hxy
 */
@Data
@TableName(value = "sys_log")
public class SysLogEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 操作
     */
    private String operation;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 消耗时间
     */
    private Long consumeTime;

    /**
     * ip 地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
