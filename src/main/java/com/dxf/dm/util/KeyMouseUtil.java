package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.dxf.dm.model.Coordinate2D;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 大漠键鼠工具集
 *
 * @author GuJun
 * @date 2020/9/21
 */
public class KeyMouseUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    public static Coordinate2D getCursorPos() throws DmOptException {
        Variant x = new Variant(0, true);
        Variant y = new Variant(0, true);
        int retCode = Dispatch.call(dm, "GetCursorPos", x, y).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do GetCursorPos, retCode: " + retCode);
        }
        System.out.println(x);
        return new Coordinate2D(x.getInt(), y.getInt());
    }
}
