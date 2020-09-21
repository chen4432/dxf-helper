package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * @author GuJun
 * @date 2020/9/21
 */
public class BasicUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    /**
     * 设置全局路径,设置了此路径后,所有接口调用中,相关的文件都相对于此路径. 比如图片,字库等.
     * @param path 路径,可以是相对路径,也可以是绝对路径
     * @throws DmOptException
     */
    public static void setPath(String path) throws DmOptException {
        int retCode = Dispatch.call(dm, "SetPath", path).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do SetPath, retCode: " + retCode);
        }
    }

    /**
     * 获取全局路径.(可用于调试)
     * @return 以字符串的形式返回当前设置的全局路径
     */
    public static String getPath() {
        return Dispatch.call(dm, "GetPath").getString();
    }
}
