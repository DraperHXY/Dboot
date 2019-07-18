package com.draper.dboot.common.exception;

import com.draper.dboot.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 只针对自定义异常处理器
 *
 * @author draper_hxy
 */
@Slf4j
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(DrException.class)
    public R handleDrException(DrException e) {
        return new R(e.getCode(), e.getMsg());
    }
}
