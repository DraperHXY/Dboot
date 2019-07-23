/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.draper.dboot.modules.oss.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * 文件上传
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class SysOssEntity {

    private Long id;

    private String url;

    private LocalDateTime createTime;
}
