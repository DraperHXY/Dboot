package com.draper.dboot.system.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author draper_hxy
 */
@Data
public class UserRolesVo {

    private Long userId;

    private String username;

    private List<String> roleList;
}
