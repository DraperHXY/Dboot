package com.draper.dboot.common.util;

import com.draper.dboot.BaseTest;
import com.draper.dboot.common.utils.SpringContextUtils;
import org.junit.jupiter.api.Test;

/**
 * @author draper_hxy
 */
public class SpringContextUtilsTest extends BaseTest {

    @Test
    public void testGetAllBeans() {
        for (String bean : SpringContextUtils.getAllBeans()) {
            System.out.println(bean);
        }
    }
}
