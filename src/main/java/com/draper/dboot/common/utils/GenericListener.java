package com.draper.dboot.common.utils;

/**
 * 泛型回调函数
 *
 * @author draper_hxy
 */
public interface GenericListener<T, R> {

    /**
     * 带参数和返回值的回调函数
     *
     * @param t
     * @return
     */
    R callback(T t);
}
