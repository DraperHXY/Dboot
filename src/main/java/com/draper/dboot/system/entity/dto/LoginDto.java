package com.draper.dboot.system.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author draper_hxy
 */
@Data
public class LoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
