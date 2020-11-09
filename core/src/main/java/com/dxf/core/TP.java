package com.dxf.core;

import com.dxf.component.基础功能类;
import com.google.common.base.Preconditions;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.sun.jna.Library;
import com.sun.jna.Native;

import java.util.List;


public class TP {

    private static ActiveXComponent DM;

    private interface Dll extends Library {
        Dll INSTANCE = Native.load("DmReg", Dll.class);
        void SetDllPathA(String path, int mode);
        void SetDllPathW(String path, int mode);
    }

    static {
        try {
            //Dll.INSTANCE.SetDllPathW(new String("src/main/resources/dm.dll".getBytes(), StandardCharsets.UTF_16), 0);
            //Dll.INSTANCE.SetDllPathA("src/main/resources/dm.dll", 0);
            DM = new ActiveXComponent("dm.dmsoft");
            Preconditions.checkNotNull(DM);
            TP.reg("suifengyunnuo5fe95058910f5da8bb59e01fb48b93d2", "83Y4N");
            TP.dmGuard(1, "memory2");       // 破读写
            TP.dmGuard(1, "hm dm.dll 1");   // 隐藏模块
            //GameMaster.dmGuard(1, "b2");            // 保护指定进程不被非法访问
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static String ver() {
        return Dispatch.call(DM, "Ver").getString();
    }

    public static String 取版本号() {
        return Dispatch.call(DM, "Ver").getString();
    }

    public static int reg(String regCode, String verInfo) {
        return Dispatch.call(DM, "Reg", regCode, verInfo).getInt();
    }

    public static int 注册(String 注册码, String 附加信息) {
        return Dispatch.call(DM, "Reg", 注册码, 附加信息).getInt();
    }

    public static int getLastError() {
        return Dispatch.call(DM, "GetLastError").getInt();
    }

    public static int 取错误信息() {
        return Dispatch.call(DM, "GetLastError").getInt();
    }

    /**
     *     "hm module unlink" : 防止当前进程中的指定模块被非法访问
     *                          module 为模块名(为0表示EXE模块),比如 dm.dll
     *                          unlink 取 0 或者 1，1 表示是否把模块在进程模块链表中擦除, 0 表示不擦除.(此模式需要加载驱动)
     *     保护盾返回 ＝ 大漠保护盾 (1, “hm dm.dll 1”)
     *     "b2 [pid]" : 保护指定进程不被非法访问
     *     保护盾返回 ＝ 大漠保护盾 (1, “b2”)
     * @param type
     * @return
     */
    public static int dmGuard(int enable, String type) {
        return Dispatch.call(DM, "DmGuard", enable, type).getInt();
    }

    public static int 设置保护盾(int 开关, String 类别) {
        return Dispatch.call(DM, "DmGuard", 开关, 类别).getInt();
    }

    public static int setPath(String path) {
        return Dispatch.call(DM, "SetPath", path).getInt();
    }

    public static int 设置路径(String 路径) {
        return Dispatch.call(DM, "SetPath", 路径).getInt();
    }

    public static String getPath() {
        return Dispatch.call(DM, "GetPath").getString();
    }

    public static String 读取路径() {
        return Dispatch.call(DM, "GetPath").getString();
    }

    //==================================================== 内存 =========================================================
    public static int 写内存字节集(int 窗口句柄, long 内存地址, String 字节集) {
        return Dispatch.call(DM, "WriteDataAddr", 窗口句柄, 内存地址, 字节集).getInt();
    }

    public static String 读内存字节集(int 窗口句柄, long 内存地址, int 长度) {
        return Dispatch.call(DM, "ReadDataAddr", 窗口句柄, 内存地址, 长度).getString();
    }

    public static long readInt(int hwnd, String addr, int type) {
        return Dispatch.call(DM, "ReadInt", hwnd, addr, type).getLong();
    }

    public static long 读内存长整型(int 窗口句柄, String 内存地址) {
        return Dispatch.call(DM, "ReadInt", 窗口句柄, 内存地址, 3).getLong();
    }

    public static int readInt(int hwnd, String addr) {
        return (int) Dispatch.call(DM, "ReadInt", hwnd, addr, 0).getLong();
    }

    public static int 读内存整数型(int 窗口句柄, String 内存地址) {
        return (int) Dispatch.call(DM, "ReadInt", 窗口句柄, 内存地址, 0).getLong();
    }

    public static int readInt(int hwnd, long addr) {
        return (int) Dispatch.call(DM, "ReadIntAddr", hwnd, addr, 0).getLong();
    }

    public static int 读内存整数型(int 窗口句柄, long 内存地址) {
        return (int) Dispatch.call(DM, "ReadIntAddr", 窗口句柄, 内存地址, 0).getLong();
    }

    public static int readInt(int hwnd, long baseAddr, int offsetAddr) {
        String addr = String.format("[%x]+%x", baseAddr, offsetAddr);
        return readInt(hwnd, addr);
    }

    public static int 读内存整数型(int 窗口句柄, long 基址, int 偏移) {
        String 内存地址 = String.format("[%x]+%x", 基址, 偏移);
        return 读内存整数型(窗口句柄, 内存地址);
    }

    public static int readInt(int hwnd, long baseAddr, int offsetAddr1, int offsetAddr2) {
        String addr = String.format("[[%x]+%x]+%x", baseAddr, offsetAddr1, offsetAddr2);
        return readInt(hwnd, addr);
    }

    public static int 读内存整数型(int 窗口句柄, long 基址, int 一级偏移, int 二级偏移) {
        String 内存地址 = String.format("[[%x]+%x]+%x", 基址, 一级偏移, 二级偏移);
        return 读内存整数型(窗口句柄, 内存地址);
    }

    public static int readInt(int hwnd, long baseAddr, int offsetAddr1, int offsetAddr2, int offsetAddr3) {
        String addr = String.format("[[[%x]+%x]+%x]+%x", baseAddr, offsetAddr1, offsetAddr2, offsetAddr3);
        return readInt(hwnd, addr);
    }

    public static long readIntAddr(int hwnd, long addr, int type) {
        return Dispatch.call(DM, "ReadIntAddr", hwnd, addr, type).getLong();
    }

    public static long readLong(int hwnd, String addr) {
        return Dispatch.call(DM, "ReadInt", hwnd, addr, 3).getLong();
    }

    public static long readLong(int hwnd, long addr) {
        return Dispatch.call(DM, "ReadIntAddr", hwnd, addr, 3).getLong();
    }

    public static long readLong(int hwnd, long baseAddr, int offset) {
        String addr = String.format("[%x]+%x", baseAddr, offset);
        return readLong(hwnd, addr);
    }

    public static long readLong(int hwnd, long baseAddr, int offset1, int offset2) {
        String addr = String.format("[[%x]+%x]+%x", baseAddr, offset1, offset2);
        return readLong(hwnd, addr);
    }

    public static long readLong(int hwnd, long baseAddr, int offset1, int offset2, int offset3) {
        String addr = String.format("[[[%x]+%x]+%x]+%x", baseAddr, offset1, offset2, offset3);
        return readLong(hwnd, addr);
    }

    public static String readString(int hwnd, String addr, int type, int len) {
        return Dispatch.call(DM, "ReadString", hwnd, addr, type, len).getString();
    }

    public static String readStringAddr(int hwnd, long addr, int type, int len) {
        return Dispatch.call(DM, "ReadStringAddr", hwnd, addr, type, len).getString();
    }

    public static float readFloat(int hwnd, String addr) {
        return Dispatch.call(DM, "ReadFloat", hwnd, addr).getFloat();
    }

    public static float readFloat(int hwnd, long addr) {
        return Dispatch.call(DM, "ReadFloatAddr", hwnd, addr).getFloat();
    }

    public static double readDouble(int hwnd, String addr) {
        return Dispatch.call(DM, "ReadDouble", hwnd, addr).getDouble();
    }

    public static double readDoubleAddr(int hwnd, long addr) {
        return Dispatch.call(DM, "ReadDoubleAddr", hwnd, addr).getDouble();
    }

    public static String readData(int hwnd, String addr, int len) {
        return Dispatch.call(DM, "ReadData", hwnd, addr, len).getString();
    }

    public static String readDataAddr(int hwnd, long addr, int len) {
        return Dispatch.call(DM, "ReadDataAddr", hwnd, addr, len).getString();
    }

    public static int writeData(int hwnd, String addr, String data) {
        return Dispatch.call(DM, "WriteData", hwnd, addr, data).getInt();
    }

    public static int writeDataAddr(int hwnd, long addr, String data) {
        return Dispatch.call(DM, "WriteDataAddr", hwnd, addr, data).getInt();
    }

    public static int writeInt(int hwnd, String addr, int type, long val) {
        return Dispatch.call(DM, "WriteInt", hwnd, addr, type, val).getInt();
    }

    public static int writeIntAddr(int hwnd, long addr, int type, long val) {
        return Dispatch.call(DM, "WriteIntAddr", hwnd, addr, type, val).getInt();
    }

    public static void writeInt(int hwnd, String addr, int val) {
        Dispatch.call(DM, "WriteInt", hwnd, addr, 0, (long)val);
    }

    public static void writeInt(int hwnd, long addr, int val) {
        Dispatch.call(DM, "WriteIntAddr", hwnd, addr, 0, (long)val);
    }

    public static void writeLong(int hwnd, String addr, long val) {
        Dispatch.call(DM, "WriteInt", hwnd, addr, 3, val);
    }

    public static void writeLong(int hwnd, long addr, long val) {
        Dispatch.call(DM, "WriteIntAddr", hwnd, addr, 3, val);
    }

    public static int writeString(int hwnd, String addr, int type, String val) {
        return Dispatch.call(DM, "WriteString", hwnd, addr, type, val).getInt();
    }

    public static int writeStringAddr(int hwnd, long addr, int type, String val) {
        return Dispatch.call(DM, "WriteStringAddr", hwnd, addr, type, val).getInt();
    }

    public static int writeFloat(int hwnd, String addr, int type, float val) {
        return Dispatch.call(DM, "WriteFloat", hwnd, addr, type, val).getInt();
    }

    public static int writeFloatAddr(int hwnd, long addr, int type, float val) {
        return Dispatch.call(DM, hwnd, addr, type, val).getInt();
    }

    public static int writeDouble(int hwnd, String addr, int type, double val) {
        return Dispatch.call(DM, "WriteDouble", hwnd, addr, type, val).getInt();
    }

    public static int writeDoubleAddr(int hwnd, long addr, int type, double val) {
        return Dispatch.call(DM, "WriteDoubleAddr", hwnd, addr, type, val).getInt();
    }

    public static String intToData(long value, int type) {
        return Dispatch.call(DM, "IntToData", value, type).getString();
    }

    public static long getModuleBaseAddr(int hwnd, String module) {
        return Dispatch.call(DM, "GetModuleBaseAddr", hwnd, module).getLong();
    }

    //==================================================== 汇编 =========================================================
    public static int asmClear() {
        return Dispatch.call(DM, "AsmClear").getInt();
    }

    public static int asmAdd(String asmIns) {
        return Dispatch.call(DM, "AsmAdd", asmIns).getInt();
    }

    public static long asmCall(int hwnd, int mode) {
        return Dispatch.call(DM, "AsmCall", hwnd, mode).getLong();
    }

    public static String assemble(long baseAddr, int is64Bit) {
        return Dispatch.call(DM, "Assemble", baseAddr, is64Bit).getString();
    }

    public static String disAssemble(String asmCode, long baseAddr, int is64Bit) {
        return Dispatch.call(DM, "DisAssemble", asmCode, baseAddr, is64Bit).getString();
    }

    //==================================================== 窗口 =========================================================
    public static int findWindow(String clazz, String title) {
        return Dispatch.call(DM, "FindWindow", clazz, title).getInt();
    }

    public static int moveWindow(int hwnd, int x, int y) {
        return Dispatch.call(DM, "MoveWindow", hwnd, x, y).getInt();
    }

    public static int getWindowState(int hwnd, int flag) {
        return Dispatch.call(DM, "GetWindowState", hwnd, flag).getInt();
    }

    public static int setWindowState(int hwnd, int flag) {
        return Dispatch.call(DM, "SetWindowState", hwnd, flag).getInt();
    }

    //==================================================== 后台 =========================================================
    public static int bindWindow(int hwnd, String display, String mouse, String keypad, int mode) {
        return Dispatch.call(DM, "BindWindow", hwnd, display, mouse, keypad, mode).getInt();
    }

    public static int 绑定窗口(int 窗口句柄, String 显示, String 鼠标, String 键盘, int 模式) {
        return Dispatch.call(DM, "BindWindow", 窗口句柄, 显示, 鼠标, 键盘, 模式).getInt();
    }

    public static int bindWindowEx(int hwnd, String display, String mouse, String keypad, String pub, int mode) {
        return Dispatch.call(DM, "BindWindowEx", hwnd, display, mouse, keypad, pub, mode).getInt();
    }

    public static int 绑定窗口高级(int 窗口句柄, String 显示, String 鼠标, String 键盘, String 公共, int 模式) {
        return Dispatch.call(DM, "BindWindowEx", 窗口句柄, 显示, 鼠标, 键盘, 公共, 模式).getInt();
    }

    public static int unbindWindow() {
        return Dispatch.call(DM, "UnBindWindow").getInt();
    }

    public static int 解除绑定窗口() {
        return Dispatch.call(DM, "UnBindWindow").getInt();
    }

    //==================================================== 键鼠 =========================================================
    public static int keyPress(int vkCode) {
        return Dispatch.call(DM, "KeyPress", vkCode).getInt();
    }

    public static int 按下按键(int 窗口句柄, int vkCode) {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        return Dispatch.call(DM, "KeyPress", vkCode).getInt();
    }

    public static int keyPressChar(String keyStr) {
        return Dispatch.call(DM, "KeyPressChar", keyStr).getInt();
    }

    public static int 按下按键(int 窗口句柄, String 按键) {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        return Dispatch.call(DM, "KeyPressChar", 按键).getInt();
    }

    /**
     * 按照序列依次Press按键
     * @param keyStrList 按键序列
     * @param delay 延迟时间
     * @return
     */
    public static void keyPressCharList(List<String> keyStrList, int delay) throws InterruptedException {
        if (keyStrList.isEmpty()) return;
        keyPressChar(keyStrList.get(0));
        for (int i = 1; i < keyStrList.size(); ++i) {
            基础功能类.延时(delay);
            keyPressChar(keyStrList.get(i));
        }
    }

    public static void 按下按键序列(int 窗口句柄, List<String> 按键序列, int 延时时间) throws InterruptedException {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        if (按键序列.isEmpty()) return;
        按下按键(窗口句柄, 按键序列.get(0));
        for (int i = 1; i < 按键序列.size(); ++i) {
            基础功能类.延时(延时时间);
            按下按键(窗口句柄, 按键序列.get(i));
        }
    }

    public static int keyPressStr(String keyStr, int delay) {
        return Dispatch.call(DM, "KeyPressStr", keyStr, delay).getInt();
    }

    public static int keyDown(int vkCode) {
        return Dispatch.call(DM, "KeyDown", vkCode).getInt();
    }

    public static int 按住按键(int 窗口句柄, int vkCode) {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        return Dispatch.call(DM, "KeyDown", vkCode).getInt();
    }

    public static int keyUp(int vkCode) {
        return Dispatch.call(DM, "KeyUp", vkCode).getInt();
    }

    public static int 松开按键(int 窗口句柄, int vkCode) {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        return Dispatch.call(DM, "KeyUp", vkCode).getInt();
    }

    public static int keyDownChar(String keyStr) {
        return Dispatch.call(DM, "KeyDownChar", keyStr).getInt();
    }

    public static int 按住按键(int 窗口句柄, String 按键) {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        return Dispatch.call(DM, "KeyDownChar", 按键).getInt();
    }

    public static int keyUpChar(String keyStr) {
        return Dispatch.call(DM, "KeyUpChar", keyStr).getInt();
    }

    public static int 松开按键(int 窗口句柄, String 按键) {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        return Dispatch.call(DM, "KeyUpChar", 按键).getInt();
    }

    public static int leftClick() {
        return Dispatch.call(DM, "LeftClick").getInt();
    }

    public static int 鼠标左击() {
        return Dispatch.call(DM, "LeftClick").getInt();
    }

    public static int 鼠标左击(int 窗口句柄) {
        while (getWindowState(窗口句柄, 1) != 1) {
            setWindowState(窗口句柄, 1);
        }
        return leftClick();
    }


    public static int moveTo(int x, int y) {
        return Dispatch.call(DM, "MoveTo", x, y).getInt();
    }

    public static int setKeypadDelay(String type, int delay) {
        return Dispatch.call(DM, "SetKeypadDelay", type, delay).getInt();
    }

    //==================================================== 图色 =========================================================
    public static String findColorE(int x1, int y1, int x2, int y2, String color, double sim, int dir) {
        return Dispatch.call(DM, "FindColorE", x1, y1, x2, y2, color, sim, dir).getString();
    }

    public static String findPicE(int x1, int y1, int x2, int y2, String picName, String deltaColor, double sim, int dir) {
        return Dispatch.call(DM, "FindPicE", x1, y1, x2, y2, picName, deltaColor, sim, dir).getString();
    }
}
