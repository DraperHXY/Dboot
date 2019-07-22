package com.draper.dboot.common.utils;

import java.util.HashMap;

/**
 * @author draper_hxy
 */
public class MapUtil extends HashMap {

    @Override
    public MapUtil put(Object key, Object value) {
        super.put(key, value);
        return this;
    }
}
