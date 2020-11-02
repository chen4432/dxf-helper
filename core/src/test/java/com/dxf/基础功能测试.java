package com.dxf;

import com.dxf.util.DXF;
import org.junit.Before;
import org.junit.Test;

public class 基础功能测试 {

    private DXF dxf;

    @Before
    public void setUp() {
        dxf = new DXF();
    }

    @Test
    public void 取游戏状态() throws Exception {
        for (int i = 0; i < 100; ++i) {
            Thread.sleep(1000);
            System.out.println("游戏状态：" + 基础功能类.取游戏状态(dxf.getHwnd()));
        }
    }

    @Test
    public void 取当前疲劳值() throws Exception {
        System.out.println(基础功能类.取当前消耗疲劳值(dxf.getHwnd()));
    }

    @Test
    public void 组包选角() {
        基础功能类.组包选角(dxf.getHwnd(), 1);
    }

    @Test
    public void 组包出图() {
        基础功能类.组包出图(dxf.getHwnd());
    }


    @Test
    public void 组包反角() {
        基础功能类.组包反角(dxf.getHwnd());
    }
}