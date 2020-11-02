package com.dxf;

import com.dxf.component.人物角色类;
import com.dxf.component.基础功能类;
import com.dxf.component.房间信息类;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class 人物角色测试 {

    private DXF dxf;
    private 人物角色类 player;

    @Before
    public void setUp() {
        dxf = new DXF();
        dxf.setUp();
        player = new 人物角色类(dxf.get窗口句柄());
        基础功能类.开启无视地图障碍(dxf.get窗口句柄());
    }

    @After
    public void cleanUp() {
        基础功能类.关闭无视地图障碍(dxf.get窗口句柄());
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
        房间信息类 room = new 房间信息类(dxf.get窗口句柄());
        player.房间清怪(room);
    }

    @Test
    public void 移动物品到脚下() throws Exception {
        player.移动物品到脚下();
    }

    @Test
    public void 自动刷图() throws Exception {
        player.自动刷图();
    }

}