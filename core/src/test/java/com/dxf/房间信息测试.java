package com.dxf;

import com.dxf.util.DXF;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        房间信息 info = new 房间信息(dxf);
        System.out.println(info.取怪物列表());
    }

    @Test
    public void 是否通关() {
        房间信息 info = new 房间信息(dxf);
        System.out.println(info.判断是否通关());
    }

}