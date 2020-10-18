package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.model.Coordinate2D;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BackgroundUtilTest {

    @Test
    public void bind_window() {
        DmCore.register();
        int hwnd =  WindowUtil.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println("窗口句柄： " + hwnd);
        WindowUtil.moveWindow(hwnd, new Coordinate2D(0, 0));
        WindowUtil.setWindowState(hwnd, 1);
        String display = "normal";
        String mouse = "normal";
        String keypad = "normal";
        int mode = 101;
        BackgroundUtil.bindWindow(hwnd, display, mouse, keypad, mode);
        System.out.println("绑定成功~");
        try {
            for (int i = 0; i < 10000000; ++i) {
                KeyMouseUtil.keyPressChar("space");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BackgroundUtil.unbindWindow();
        }
    }

}