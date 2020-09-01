package com.dxf.dm;

import com.dxf.util.ConfigUtil;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.typesafe.config.Config;

/**
 * @author GuJun
 * @date 2020/9/1
 */
public class DM {

    private Config conf = ConfigUtil.getConfig();

    private ActiveXComponent dm;

    public DM() {
        dm = new ActiveXComponent("dm.dmsoft");
    }

    public String getVersion() {
        return Dispatch.call(dm, "Ver").getString();
    }

    public boolean register() {
        return Dispatch.call(dm, "Reg", conf.getString("DM_CONF.CODE_1"), conf.getString("DM_CONF.CODE_2")).getInt() == 1;
    }

    /**
     * 查找符合类名或者标题名的顶层可见窗口
     * @param className 窗口类名
     * @param title 标题名
     * @return 窗口句柄，没有返回0
     */
    public int findWindow(String className, String title) {
        return Dispatch.call(dm, "FindWindow", className, title).getInt();
    }

    /**
     * 获取指定窗口所在
     * @param hwnd 窗口句柄
     * @return 进程ID
     */
    public int getWindowProcessId(int hwnd) {
        return Dispatch.call(dm, "GetWindowProcessId", hwnd).getInt();
    }

    /**
     *
     * @param hwnd
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean getWindowRect(int hwnd, Variant x1, Variant y1, Variant x2, Variant y2) {
        return Dispatch.call(dm, "GetWindowRect", hwnd, x1, y1, x2, y2).getInt() == 1;
    }

    /**
     * 移动指定窗口到指定位置
     * @param hwnd
     * @param x
     * @param y
     * @return
     */
    public boolean moveWindow(int hwnd, int x, int y) {
        return Dispatch.call(dm, "MoveWindow", hwnd, x, y).getInt() == 1;
    }

    /**
     * 向指定窗口发送文本数据
     * @param hwnd
     * @param message
     * @return
     */
    public boolean sendString(int hwnd, String message) {
        return Dispatch.call(dm, "SendString", hwnd, message).getInt() == 1;
    }

    /**
     * 设置窗口大小
     * @param hwnd
     * @param width
     * @param height
     * @return
     */
    public boolean setWindowSize(int hwnd, int width, int height) {
        return Dispatch.call(dm, "SetWindowSize", hwnd, width, height).getInt() == 1;
    }

    /**
     * 绑定指定窗口
     * @param hwnd
     * @param display
     * @param mouse
     * @param keypad
     * @param mode
     * @return
     */
    public boolean bindWindow(int hwnd, String display, String mouse, String keypad, int mode) {
        return Dispatch.call(dm, "BindWindow", hwnd, display, mouse, keypad, mode).getInt() == 1;
    }

    public boolean bindWindowEx(int hwnd, String display, String mouse, String keypad, String pub, int mode) {
        return Dispatch.call(dm, "BindWindowEx", hwnd, display, mode, keypad, pub).getInt() == 1;
    }

    /**
     * 取消绑定窗口
     * @return
     */
    public boolean unbindWindow() {
        return Dispatch.call(dm, "UnBindWindow").getInt() == 1;
    }

    /**
     * 设置全局路径，设置了此路径，所有接口调用中，路径均相对于此路径，如图片，字库等
     * @return
     */
    public boolean setPath() {
        return Dispatch.call(dm, "SetPath").getInt() == 1;
    }

    /**
     * 获取鼠标位置
     * @param x
     * @param y
     * @return
     */
    public boolean getCursorPos(Variant x, Variant y) {
        return Dispatch.call(dm, "GetCursorPos", x, y).getInt() == 1;
    }


    public boolean keyDown(int vkCode) {
        return Dispatch.call(dm, "KeyDown", vkCode).getInt() == 1;
    }

}
