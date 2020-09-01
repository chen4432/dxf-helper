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

    private static ActiveXComponent dm;

     static {
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


    /**
     * 按住虚拟按键
     * @param vkCode
     * @return
     */
    public boolean keyDown(int vkCode) {
        return Dispatch.call(dm, "KeyDown", vkCode).getInt() == 1;
    }

    public boolean keyUp(int vkCode) {
        return Dispatch.call(dm, "KeyUp", vkCode).getInt() == 1;
    }

    public boolean keyDown(char c) {
        return Dispatch.call(dm, "KeyDownChar", String.valueOf(c)).getInt() == 1;
    }

    public boolean keyUp(char c) {
        return Dispatch.call(dm, "KeyUpChar", String.valueOf(c)).getInt() == 1;
    }

    public boolean keyPress(int vkCode) {
        return Dispatch.call(dm, "KeyPress", vkCode).getInt() == 1;
    }

    public boolean keyPress(char c) {
        return Dispatch.call(dm, "KeyPressChar", String.valueOf(c)).getInt() == 1;
    }

    public boolean leftClick() {
        return Dispatch.call(dm, "LeftClick").getInt() == 1;
    }

    public boolean leftRight() {
        return Dispatch.call(dm, "LeftRight").getInt() == 1;
    }

    public boolean leftDoubleClick() {
        return Dispatch.call(dm, "LeftDoubleClick").getInt() == 1;
    }

    public boolean leftDown() {
        return Dispatch.call(dm, "LeftDown").getInt() == 1;
    }

    public boolean rightDown() {
        return Dispatch.call(dm, "RightDown").getInt() == 1;
    }

    public boolean rightUp() {
        return Dispatch.call(dm, "RightUp").getInt() == 1;
    }

    public boolean moveTo(int x, int y) {
        return Dispatch.call(dm, "ModeTo", x, y).getInt() == 1;
    }


    public static class MemoryUtil {
        /**
         * 根据指定的窗口句柄，来获取对应窗口句柄进程下的指定模块的基址
         * @param hwnd 指定搜索的窗口句柄
         * @param module 模块名
         * @return 模块的基址
         */
        public int getModuleBaseAddr(int hwnd, String module) {
            return Dispatch.call(dm, "GetModuleBaseAddr", hwnd, module).getInt();
        }

        /**
         * 读取指定地址的二进制数据
         * @param hwnd 指定搜索的窗口句柄
         * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减,模块名必须用<>符号来圈起来
         *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
         *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
         *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
         *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
         *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
         * @param len len 二进制数据的长度
         * @return 读取到的数值,以16进制表示的字符串 每个字节以空格相隔 比如"12 34 56 78 ab cd ef"
         */
        public String readData(int hwnd, String addr, int len) {
            return Dispatch.call(dm, "ReadData", hwnd, addr, len).getString();
        }

        /**
         * 对指定地址写入二进制数据
         * @param hwnd 指定搜索的窗口句柄
         * @param addr addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减;模块名必须用<>符号来圈起来
         *             1. "4DA678" 最简单的方式，用绝对数值来表示地址
         *             2. "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
         *             3. "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
         *             4. "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
         *             5. "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
         * @param data 二进制数据，以字符串形式描述，比如"12 34 56 78 90 ab cd"
         * @return
         */
        public boolean writeData(int hwnd, String addr, String data) {
            return Dispatch.call(dm, "WriteData", hwnd, addr, data).getInt() == 1;
        }
    }

}
