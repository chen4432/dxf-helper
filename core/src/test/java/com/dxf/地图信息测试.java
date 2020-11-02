package com.dxf;

import com.dxf.model.坐标类;
import com.dxf.util.DXF;
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

    @Test
    public void 门测试() {
        地图信息类 地图信息 = new 地图信息类(dxf.get窗口句柄());
        for (int i = 0; i < 地图信息.取地图高度(); ++i) {
            for (int j = 0; j < 地图信息.取地图宽度(); ++j) {
                System.out.printf("坐标：%d,%d => %s\n", j, i, 地图信息.取当前可通行邻居房间坐标(new 坐标类(j, i)));
            }
        }
    }

}