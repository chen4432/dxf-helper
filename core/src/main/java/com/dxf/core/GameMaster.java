package com.dxf.core;

import com.dxf.common.util.ConfigUtil;
import com.google.common.base.Preconditions;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.typesafe.config.Config;


public class GameMaster {

    private static final Config CONF = ConfigUtil.getConfig();

    private static final ActiveXComponent DM;

    static {
        DM = new ActiveXComponent("dm.dmsoft");
        Preconditions.checkNotNull(DM);
    }

    public static String ver() {
        return Dispatch.call(DM, "Ver").getString();
    }

    public static int reg(String regCode, String verInfo) {
        return Dispatch.call(DM, "Reg", regCode, verInfo).getInt();
    }

    public static int getLastError() {
        return Dispatch.call(DM, "GetLastError").getInt();
    }

    /**
     *     "hm module unlink" : 防止当前进程中的指定模块被非法访问. module为模块名(为0表示EXE模块),比如dm.dll 。 unlink取0或者1，1表示是否把模块在进程模块链表中擦除,0表示不擦除.(此模式需要加载驱动)
     *     保护盾返回 ＝ 大漠保护盾 (1, “hm dm.dll 1”)
     *     "b2 [pid]" : 保护指定进程不被非法访问
     *     保护盾返回 ＝ 大漠保护盾 (1, “b2”)
     * @param type
     * @return
     */
    public static int dmGuard(int enable, String type) {
        return Dispatch.call(DM, "DmGuard", enable, type).getInt();
    }

    public static int setPath(String path) {
        return Dispatch.call(DM, "SetPath", path).getInt();
    }

    public static String getPath() {
        return Dispatch.call(DM, "GetPath").getString();
    }

    public static long readInt(int hwnd, String addr, int type) {
        return Dispatch.call(DM, "ReadInt", hwnd, addr, type).getLong();
    }

    public static String readString(int hwnd, String addr, int type, int len) {
        return Dispatch.call(DM, "ReadString", hwnd, addr, type, len).getString();
    }

    public static float readFloat(int hwnd, String addr) {
        return Dispatch.call(DM, "ReadFloat", hwnd, addr).getFloat();
    }

    public static double readDouble(int hwnd, String addr) {
        return Dispatch.call(DM, "ReadDouble", hwnd, addr).getDouble();
    }

    public static String readData(int hwnd, String addr, int len) {
        return Dispatch.call(DM, "ReadData", hwnd, addr, len).getString();
    }

    public static int writeData(int hwnd, String addr, String data) {
        return Dispatch.call(DM, "WriteData", hwnd, addr, data).getInt();
    }

    public static int writeInt(int hwnd, String addr, int type, long val) {
        return Dispatch.call(DM, "WriteInt", hwnd, addr, type, val).getInt();
    }

    public static int writeString(int hwnd, String addr, int type, String val) {
        return Dispatch.call(DM, "WriteString", hwnd, addr, type, val).getInt();
    }

    public static int writeFloat(int hwnd, String addr, int type, float val) {
        return Dispatch.call(DM, "WriteFloat", hwnd, addr, type, val).getInt();
    }

    public static int writeDouble(int hwnd, String addr, int type, double val) {
        return Dispatch.call(DM, "WriteDouble", hwnd, addr, type, val).getInt();
    }

    public static String intToData(long value, int type) {
        return Dispatch.call(DM, "IntToData", value, type).getString();
    }

    public static long getModuleBaseAddr(int hwnd, String module) {
        return Dispatch.call(DM, "GetModuleBaseAddr", hwnd, module).getLong();
    }

    //============================= 汇编 =============================
    public static int asmClear() {
        return Dispatch.call(DM, "AsmClear").getInt();
    }

    public static int asmAdd(String asmIns) {
        return Dispatch.call(DM, "AsmAdd", asmIns).getInt();
    }

    public static long asmCall(int hwnd, int mode) {
        return Dispatch.call(DM, "AsmCall", hwnd, mode).getInt();
    }

    public static String assemble(long baseAddr, int is64Bit) {
        return Dispatch.call(DM, "Assemble", baseAddr, is64Bit).getString();
    }

    public static String disAssemble(String asmCode, long baseAddr, int is64Bit) {
        return Dispatch.call(DM, "DisAssemble", asmCode, baseAddr, is64Bit).getString();
    }

    //============================= 窗口 ==============================
    public static int findWindow(String clazz, String title) {
        return Dispatch.call(DM, "FindWindow", clazz, title).getInt();
    }

    public static int moveWindow(int hwnd, int x, int y) {
        return Dispatch.call(DM, "MoveWindow", hwnd, x, y).getInt();
    }


    //==================================================== 后台 =========================================================
    public static int bindWindow(int hwnd, String display, String mouse, String keypad, int mode) {
        return Dispatch.call(DM, "BindWindow", hwnd, display, mouse, keypad, mode).getInt();
    }

    public static int unbindWindow() {
        return Dispatch.call(DM, "UnBindWindow").getInt();
    }

    //==================================================== 键鼠 =========================================================
    public static int keyPress(int vkCode) {
        return Dispatch.call(DM, "KeyPress", vkCode).getInt();
    }
    
}
