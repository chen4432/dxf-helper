package com.dxf.dm.util;

import com.dxf.dm.model.Coordinate2D;
import org.junit.Test;

import static org.junit.Assert.*;

public class WindowUtilTest {

    @Test
    public void find_window() throws Exception {
        int hwnd = WindowUtil.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
    }

    @Test
    public void move_window() throws Exception {
        int hwnd = WindowUtil.findWindow("地下城与勇士", "地下城与勇士");
        WindowUtil.moveWindow(hwnd, new Coordinate2D(0, 0));
    }

}