package com.dxf;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.util.GuardUtil;
import com.dxf.dm.util.MemUtil;
import com.dxf.dm.util.WindowUtil;
import com.dxf.util.ConfigUtil;
import com.typesafe.config.Config;
import org.junit.Before;
import org.junit.Test;

public class MiscTest {

    private Config config;

    private int hwnd;

    @Before
    public void setUp() throws Exception {
        DmCore.register();
        GuardUtil.guard(true, "memory");
        hwnd = WindowUtil.findWindow("", "地下城与勇士");
        config = ConfigUtil.getConfig("constant.conf");
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

    @Test
    public void role_level() throws Exception {
        long ROLE_LEVEL_ADDR = config.getLong("角色等级");
        System.out.println(MemUtil.readInt(hwnd, ROLE_LEVEL_ADDR, 0));
    }

    @Test
    public void role_name() throws Exception {
        long ROLE_NAME_ADDR = config.getLong("人物名称");
        System.out.println(ROLE_NAME_ADDR);
        System.out.println(MemUtil.readString(hwnd, ROLE_NAME_ADDR, 1, 30));
    }

}
