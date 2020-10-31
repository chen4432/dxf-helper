package com.dxf;

import com.dxf.util.DXF;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class 人物角色测试 {

    private DXF dxf;
    private 人物角色 player;

    @Before
    public void setUp() {
        dxf = new DXF();
        dxf.setUp();
        player = new 人物角色(dxf);
        基础功能.开启无视地图障碍(dxf.getHwnd());
    }

    @After
    public void cleanUp() {
        基础功能.关闭无视地图障碍(dxf.getHwnd());
        dxf.cleanUp();
    }

    @Test
    public void 取人物信息() {
        System.out.println("名称：" + player.取名称());
        System.out.println("等级：" + player.取等级());
        System.out.println("职业：" + player.取职业());
    }

    @Test
    public void 测试移动速度() throws Exception {
        player.测试移动速度();
    }

    @Test
    public void 取人物坐标() {
        System.out.println(player.取人物坐标());
    }


    @Test
    public void 清理房间怪() throws Exception {
        房间信息 room = new 房间信息(dxf);
        player.房间清怪(room);
    }

    @Test
    public void 自动刷图() throws Exception {
        player.开始刷图();
    }

}