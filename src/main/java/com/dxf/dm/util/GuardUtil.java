package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;


/**
 * 大漠防护盾工具集
 *
 * @author GuJun
 * @date 2020/9/21
 */
public class GuardUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    public static void guard(boolean enable, String type) throws DmOptException {
        int retCode = Dispatch.call(dm, "DmGuard", enable ? 1 : 0, type).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do DmGuard, retCode: " + retCode);
        }
    }

}
