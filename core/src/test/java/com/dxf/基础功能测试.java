package com.dxf;

import com.dxf.component.基础功能类;
import com.dxf.core.TP;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class 基础功能测试 {

    private DXF dxf;

    @Before
    public void setUp() {
        dxf = new DXF();
        dxf.setUp();
    }

    @After
    public void tearDown() {
        dxf.cleanUp();
    }


    @Test
    public void 取游戏状态() throws Exception {
        for (int i = 0; i < 100; ++i) {
            Thread.sleep(1000);
            System.out.println("游戏状态：" + 基础功能类.取游戏状态(dxf.get窗口句柄()));
        }
    }

    @Test
    public void 取当前疲劳值() throws Exception {
        System.out.println(基础功能类.取当前消耗疲劳值(dxf.get窗口句柄()));
    }

    @Test
    public void 组包选角() {
        基础功能类.组包选角(dxf.get窗口句柄(), 2);
    }

    @Test
    public void 组包出图() {
        基础功能类.组包出图(dxf.get窗口句柄());
    }


    @Test
    public void 组包返回角色列表() {
        基础功能类.组包返回角色列表(dxf.get窗口句柄());
    }

    @Test
    public void 下一个角色() throws Exception {
        基础功能类.进入选择角界面(dxf.get窗口句柄());
    }

    @Test
    public void 进入根特皇宫门口测试() throws Exception {

        基础功能类.进入根特皇宫门口();
    }

    @Test
    public void 下一个角色测试() throws Exception {
        基础功能类.下一个角色();
    }
}