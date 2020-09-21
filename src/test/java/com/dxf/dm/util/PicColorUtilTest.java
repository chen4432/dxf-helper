package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.model.Coordinate2D;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

public class PicColorUtilTest {

    @Test
    public void find_pic_e() throws Exception {
        DmCore.register();
        BasicUtil.setPath("C:\\Users\\jun\\IdeaProjects\\dxf-helper\\src\\main\\resources\\img_dict");
        Map<Integer, Coordinate2D> ret = PicColorUtil.findPicE(new Coordinate2D(0,0), new Coordinate2D(800, 600), Arrays.asList("QT_icon.bmp"), "202020", 0.9, 0);
        System.out.println(ret);
    }

}