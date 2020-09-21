package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.model.Coordinate2D;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 大漠图色工具集
 *
 * @author GuJun
 * @date 2020/9/21
 */
public class PicColorUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    public static int findPic(Coordinate2D upperLeft, Coordinate2D downRight, List<String> images, String deltaColor, double sim, int dir, Coordinate2D pos) {
        return 0;
    }

    public static Map<Integer, Coordinate2D> findPicE(
            Coordinate2D upperLeft,
            Coordinate2D downRight,
            List<String> images,
            String deltaColor,
            double sim,
            int dir) {
        Map<Integer, Coordinate2D> map = new HashMap<>();
        int x1 = upperLeft.x, y1 = upperLeft.y, x2 = downRight.x, y2 = downRight.y;
        String picName = String.join("|", images);
        String ret = Dispatch.call(dm, "FindPicE", x1, y1, x2, y2, picName, deltaColor, sim, dir).getString();
        System.out.println(ret);
        String[] fields = ret.split("\\|", -1);
        map.put(Integer.parseInt(fields[0]), new Coordinate2D(Integer.parseInt(fields[1]), Integer.parseInt(fields[2])));
        return map;
    }

}
