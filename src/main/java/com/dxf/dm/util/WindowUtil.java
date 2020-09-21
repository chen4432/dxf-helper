package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.dxf.dm.model.Coordinate2D;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * @author GuJun
 * @date 2020/9/21
 */
public class WindowUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    /**
     * 查找符合类名或者标题名的顶层可见窗口
     * @param className 查找符合类名或者标题名的顶层可见窗口
     * @param title 查找符合类名或者标题名的顶层可见窗口
     * @return 查找符合类名或者标题名的顶层可见窗口
     * @throws DmOptException
     */
    public static int findWindow(String className, String title) throws DmOptException {
        int hwnd = Dispatch.call(dm, "FindWindow", className, title).getInt();
        if (hwnd <= 0) {
            throw new DmOptException("Failed to do FindWindow, hwnd: " + hwnd);
        }
        return hwnd;
    }

    /**
     * 设置窗口的大小
     * @param hwnd 指定的窗口句柄
     * @param width 宽度
     * @param height 高度
     * @throws DmOptException
     */
    public static void setWindowSize(int hwnd, int width, int height) throws DmOptException {
        int retCode = Dispatch.call(dm, "SetWindowSize", hwnd, width, height).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do SetWindowSize, retCode: " + retCode);
        }
    }

    /**
     * 设置窗口的状态
     * @param hwnd 指定的窗口句柄
     * @param flag 取值定义如下
     *              0 : 关闭指定窗口
     *              1 : 激活指定窗口
     *              2 : 最小化指定窗口,但不激活
     *              3 : 最小化指定窗口,并释放内存,但同时也会激活窗口.(释放内存可以考虑用FreeProcessMemory函数)
     *              4 : 最大化指定窗口,同时激活窗口.
     *              5 : 恢复指定窗口 ,但不激活
     *              6 : 隐藏指定窗口
     *              7 : 显示指定窗口
     *              8 : 置顶指定窗口
     *              9 : 取消置顶指定窗口
     *              10 : 禁止指定窗口
     *              11 : 取消禁止指定窗口
     *              12 : 恢复并激活指定窗口
     *              13 : 强制结束窗口所在进程.
     *              14 : 闪烁指定的窗口
     *              15 : 使指定的窗口获取输入焦点
     * @throws DmOptException
     */
    public static void setWindowState(int hwnd, int flag) throws DmOptException {
        int retCode = Dispatch.call(dm, "SetWindowState", hwnd, flag).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to SetWindowState, retCode: " + retCode);
        }
    }

    /**
     * 移动指定窗口到指定位置
     * @param hwnd 移动指定窗口到指定位置
     * @param pos 指定坐标
     * @throws DmOptException
     */
    public static void moveWindow(int hwnd, Coordinate2D pos) throws DmOptException {
        int retCode = Dispatch.call(dm, "MoveWindow", hwnd, pos.x, pos.y).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to MoveWindow, retCode: " + retCode);
        }
    }
}
