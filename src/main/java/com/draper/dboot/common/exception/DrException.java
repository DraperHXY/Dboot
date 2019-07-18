package com.draper.dboot.common.exception;

import lombok.Data;

/**
 * 自定义异常
 *
 * @author draper_hxy
 */
@Data
public class DrException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public DrException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public DrException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public DrException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public DrException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }
}
