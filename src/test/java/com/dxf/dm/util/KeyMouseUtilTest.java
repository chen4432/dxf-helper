package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.dxf.dm.model.Coordinate2D;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author GuJun
 * @date 2020/9/21
 */
public class KeyMouseUtilTest {

    @Test
    public void get_cursor_pos() throws DmOptException {
        DmCore.register();
        Coordinate2D pos = KeyMouseUtil.getCursorPos();
        System.out.println(pos);
    }

    @Test
    public void get_cursor_pos_continually() throws Exception {
        for (int i = 0; i < 1000000; ++i) {
            System.out.println(KeyMouseUtil.getCursorPos());
            Thread.sleep(100);
        }
    }

}