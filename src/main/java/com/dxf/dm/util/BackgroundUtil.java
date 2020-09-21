package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;


/**
 * 大漠后台工具集
 *
 * @author GuJun
 * @date 2020/9/21
 */
public class BackgroundUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    /**
     * 绑定指定的窗口,并指定这个窗口的屏幕颜色获取方式,鼠标仿真模式,键盘仿真模式,以及模式设定
     * @param hwnd
     * @param display
     * @param mouse
     * @param keypad
     * @param mode
     * @throws DmOptException
     */
    public static void bindWindow(int hwnd, String display, String mouse, String keypad, int mode) throws DmOptException {
        int retCode = Dispatch.call(dm, "BindWindow", hwnd, display, mouse, keypad, mode).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do BindWindow, retCode: " + retCode);
        }
    }

    /**
     * 绑定指定的窗口,并指定这个窗口的屏幕颜色获取方式,鼠标仿真模式,键盘仿真模式 高级用户使用.
     * @param hwnd
     * @param display
     * @param mouse
     * @param keypad
     * @param mode
     * @throws DmOptException
     */
    public static void bindWindowEx(int hwnd, String display, String mouse, String keypad, String common, int mode) throws DmOptException {
        int retCode = Dispatch.call(dm, "BindWindowEx", hwnd, display, mouse, keypad, common, mode).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do BindWindowEx, retCode: " + retCode);
        }
    }

    /**
     * 设置是否暂时关闭或者开启后台功能. 默认是开启.  一般用在前台切换，或者脚本暂停和恢复时，可以让用户操作窗口.
     * @param enable  0: 全部关闭(图色键鼠都关闭,也就是说图色,键鼠都是前台
     *               -1: 只关闭图色.(也就是说图色是normal前台. 键鼠不变)
     *                1: 开启(恢复原始状态)
     *                5: 同0，也是全部关闭
     */
    public static void enableBind(int enable) {
        int retCode = Dispatch.call(dm, "EnableBind", enable).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do EnableBind, retCode: " + retCode);
        }
    }

    /**
     * 获取当前对象已经绑定的窗口句柄. 无绑定返回0
     * @return 窗口句柄, 无绑定返回0
     */
    public static int getBindWindow() {
        return Dispatch.call(dm, "GetBindWindow").getInt();
    }

    /**
     * 解除绑定窗口,并释放系统资源.一般在OnScriptExit调用
     * @throws DmOptException
     */
    public static void unbindWindow() throws DmOptException {
        int retCode = Dispatch.call(dm, "UnbindWindow").getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do UnbindWindow, retCode: " + retCode);
        }
    }


}
