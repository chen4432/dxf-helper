package com.dxf;

import com.dxf.component.地图信息类;
import com.dxf.model.坐标类;
import org.junit.Before;
import org.junit.Test;

public class 地图信息测试 {

    private DXF dxf;

    @Before
    public void setUp() {
        dxf = new DXF();
    }

    @Test
    public void test() {
        地图信息类 地图信息 = new 地图信息类(dxf.get窗口句柄());
    }

}