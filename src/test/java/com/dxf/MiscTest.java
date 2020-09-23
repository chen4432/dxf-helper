package com.dxf;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.util.GuardUtil;
import com.dxf.dm.util.MemUtil;
import com.dxf.dm.util.WindowUtil;
import org.junit.Before;
import org.junit.Test;

public class MiscTest {

    private int hwnd;

    @Before
    public void setUp() throws Exception {
        DmCore.register();
        GuardUtil.guard(true, "memory");
        hwnd = WindowUtil.findWindow("", "地下城与勇士");
    }

    @Test
    public void game_state() throws Exception {
        long GAME_STATE_ADDR = 5545481924L;
        for (int i = 0; i < 100000; ++i) {
            long val = MemUtil.readInt(hwnd, GAME_STATE_ADDR, 0);
            System.out.println(val);
            Thread.sleep(1000);
        }
    }


}
