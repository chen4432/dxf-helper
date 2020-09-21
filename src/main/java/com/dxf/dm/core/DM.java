package com.dxf.dm.core;

import com.dxf.dm.model.Coordinate2D;
import com.dxf.util.ConfigUtil;
import com.google.common.base.Strings;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.typesafe.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuJun
 * @date 2020/9/1
 */
public class DM {

    private static final Config conf = ConfigUtil.getConfig();

    private static final ActiveXComponent dm;

    static {
        dm = new ActiveXComponent("dm.dmsoft");
    }

    synchronized public static void register() throws RuntimeException {
        int retCode = Dispatch.call(dm, "Reg", conf.getString("DM_CONF.CODE_1"), conf.getString("DM_CONF.CODE_2")).getInt();
        if (retCode != 1) {
            throw new RuntimeException("Failed to register dm.dll to system, retCode: " + retCode);
        }
    }

    synchronized public static String getVersion() {
        return Dispatch.call(dm, "Ver").getString();
    }


    /**
     * 窗口工具类
     */
    public static class WindowUtil {

        /**
         * 把窗口坐标转换为屏幕坐标
         * @param hwnd 制定的窗口句柄
         * @param pos 窗口坐标
         * @throws RuntimeException
         */
        synchronized public static void clientToScreen(int hwnd, Coordinate2D pos) throws RuntimeException {
            int retCode = Dispatch.call(dm, "ClientToScreen", pos.x, pos.y).getInt();
            if (retCode != 1) {
                throw new RuntimeException("Failed to do ClientToScreen, retCode: " + retCode);
            }
        }

        /**
         * 根据指定进程名,枚举系统中符合条件的进程PID,并且按照进程打开顺序排序.
         * @param name 进程名,比如qq.exe
         * @return 返回所有匹配的进程PID列表
         */
        synchronized public static List<Integer> enumProcess(String name) {
            List<Integer> pids = new ArrayList<>();
            String ret = Dispatch.call(dm, "EnumProcess", name).getString();
            if (!Strings.isNullOrEmpty(ret)) {
                for (String s : ret.split(",", -1)) {
                    pids.add(Integer.parseInt(s));
                }
            }
            return pids;
        }

        /**
         * 根据指定条件,枚举系统中符合条件的窗口,可以枚举到按键自带的无法枚举到的窗口
         * @param parent 获得的窗口句柄是该窗口的子窗口的窗口句柄,取0时为获得桌面句柄
         * @param title 窗口标题. 此参数是模糊匹配.
         * @param className 窗口类名. 此参数是模糊匹配
         * @param filter 取值定义如下
         *               1 : 匹配窗口标题,参数title有效
         *               2 : 匹配窗口类名,参数class_name有效.
         *               4 : 只匹配指定父窗口的第一层孩子窗口
         *               8 : 匹配父窗口为0的窗口,即顶级窗口
         *               16 : 匹配可见的窗口
         *               32 : 匹配出的窗口按照窗口打开顺序依次排列
         *               这些值可以相加,比如4+8+16就是类似于任务管理器中的窗口列表
         * @return 返回所有匹配的窗口句柄列表
         */
        synchronized public static List<Integer> enumWindow(int parent, String title, String className, int filter) {
            List<Integer> hwnds = new ArrayList<>();
            String ret = Dispatch.call(dm, "EnumWindow", parent, title, className, filter).getString();
            if (!Strings.isNullOrEmpty(ret)) {
                for (String s : ret.split(",", -1)) {
                    hwnds.add(Integer.parseInt(s));
                }
            }
            return hwnds;
        }

        /**
         * 查找符合类名或者标题名的顶层可见窗口
         * @param className 窗口类名
         * @param title 标题名
         * @return 窗口句柄，没有返回0
         */
        synchronized public static int findWindow(String className, String title) {
            return Dispatch.call(dm, "FindWindow", className, title).getInt();
        }

        /**
         * 获取指定窗口所在
         * @param hwnd 窗口句柄
         * @return 进程ID
         */
        synchronized public static int getWindowProcessId(int hwnd) {
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
        synchronized public static boolean getWindowRect(int hwnd, Variant x1, Variant y1, Variant x2, Variant y2) {
            return Dispatch.call(dm, "GetWindowRect", hwnd, x1, y1, x2, y2).getInt() == 1;
        }

        /**
         * 移动指定窗口到指定位置
         * @param hwnd
         * @param x
         * @param y
         * @return
         */
        synchronized public static boolean moveWindow(int hwnd, int x, int y) {
            return Dispatch.call(dm, "MoveWindow", hwnd, x, y).getInt() == 1;
        }

        /**
         * 向指定窗口发送文本数据
         * @param hwnd
         * @param message
         * @return
         */
        synchronized public static boolean sendString(int hwnd, String message) {
            return Dispatch.call(dm, "SendString", hwnd, message).getInt() == 1;
        }

        /**
         * 设置窗口大小
         * @param hwnd
         * @param width
         * @param height
         * @return
         */
        synchronized public static boolean setWindowSize(int hwnd, int width, int height) {
            return Dispatch.call(dm, "SetWindowSize", hwnd, width, height).getInt() == 1;
        }
    }

    /**
     * 防护盾
     */
    public static class GuardUtil {
        /**
         * 针对部分监测措施的保护盾
         * @param enable true： 打开保护盾； false： 关闭保护盾
         * @param type
         */
        synchronized public static void guard(boolean enable, String type) throws RuntimeException {
            int retCode = Dispatch.call(dm, "DmGuard", enable, type).getInt();
            if (retCode != 1) {
                throw new RuntimeException("Failed to do DmGuard, retCode: " + retCode);
            }
        }
    }


    public static class BackgroundUtil {
        /**
         * 绑定指定窗口
         * @param hwnd
         * @param display
         * @param mouse
         * @param keypad
         * @param mode
         * @return
         */
        public static boolean bindWindow(int hwnd, String display, String mouse, String keypad, int mode) {
            return Dispatch.call(dm, "BindWindow", hwnd, display, mouse, keypad, mode).getInt() == 1;
        }

        public static boolean bindWindowEx(int hwnd, String display, String mouse, String keypad, String pub, int mode) {
            return Dispatch.call(dm, "BindWindowEx", hwnd, display, mode, keypad, pub).getInt() == 1;
        }

        /**
         * 取消绑定窗口
         * @return
         */
        public static boolean unbindWindow() {
            return Dispatch.call(dm, "UnBindWindow").getInt() == 1;
        }
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

        public static String doubleToData(double value) {
            return Dispatch.call(dm, "DoubleToData", value).getString();
        }

        public static String floatToData(float value) {
            return Dispatch.call(dm, "FloatToData", value).getString();
        }

        public static String intToData(int value, int type) {
            return Dispatch.call(dm, "IntToData", value, type).getString();
        }

        public static String stringToData(String value, int type) {
            return Dispatch.call(dm, "StringToData", value, type).getString();
        }

        /**
         * 搜索指定的二进制数据,默认步长是1.如果要定制步长，请用FindDataEx
         * @param hwnd hwnd 指定搜索的窗口句柄
         * @param addrRange 指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描),如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索);"00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
         * @param data 要搜索的二进制数据 以字符串的形式描述比如"00 01 23 45 67 86 ab ce f1"等.
         * @return 返回搜索到的地址集合，地址格式如下:"addr1|addr2|addr3…|addrn" 比如"400050|423435|453430"
         */
        public static String findData(int hwnd, String addrRange, String data) {
            return Dispatch.call(dm, "FindData", hwnd, addrRange, data).getString();
        }

        public static String findDouble(int hwnd, String addrRange, double lowerBound, double upperBound) {
            return Dispatch.call(dm, "FindDouble", hwnd, addrRange, lowerBound, upperBound).getString();
        }

        public static String findFloat(int hwnd, String addrRange, float lowerBound, float upperBound) {
            return Dispatch.call(dm, "FindFloat", addrRange, lowerBound, upperBound).getString();
        }

        public static String findInt(int hwnd, String addrRange, int lowerBound, int upperBound) {
            return Dispatch.call(dm, "FindInt", hwnd, addrRange, lowerBound, upperBound).getString();
        }

        public static String findString(int hwnd, String addrRange, String value, int type) {
            return Dispatch.call(dm, "FindString", hwnd, addrRange, value, type).getString();
        }

        /**
         * 根据指定的窗口句柄，来获取对应窗口句柄进程下的指定模块的基址
         * @param hwnd 指定搜索的窗口句柄
         * @param module 模块名
         * @return 模块的基址
         */
        public static int getModuleBaseAddr(int hwnd, String module) {
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
        public static String readData(int hwnd, String addr, int len) {
            return Dispatch.call(dm, "ReadData", hwnd, addr, len).getString();
        }

        public static double readDouble(int hwnd, String addr) {
            return Dispatch.call(dm, "ReadDouble", hwnd, addr).getDouble();
        }

        public static float readFloat(int hwnd, String addr) {
            return Dispatch.call(dm, "ReadFloat", hwnd, addr).getFloat();
        }

        public static int readInt(int hwnd, String addr, int type) {
            return Dispatch.call(dm, "ReadInt", hwnd, addr, type).getInt();
        }

        public static String readString(int hwnd, String addr, int type, int len) {
            return Dispatch.call(dm, "ReadString", hwnd, addr, type, len).getString();
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
