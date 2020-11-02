package com.dxf;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class 房间信息测试 {

    private DXF dxf;

    @Before
    public void setUp() {
        dxf = new DXF();
        dxf.setUp();
    }

    @After
    public void cleanUp() {
        dxf.cleanUp();
    }

    @Test
    public void test() {
        房间信息类 info = new 房间信息类(dxf);
        System.out.println(info.取怪物列表());
    }

    @Test
    public void 是否通关() {
        房间信息类 info = new 房间信息类(dxf);
        System.out.println(info.判断是否通关());
    }

}