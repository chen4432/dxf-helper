package com.dxf;

import com.dxf.core.GameMaster;
import com.dxf.model.坐标类;


public class 基础功能类 {

    private static int 地图穿透默认值;
    private static int 建筑穿透默认值;

    public static int 解密(int hwnd, long addr) {
        int  a = GameMaster.readInt(hwnd, addr);
        long b = GameMaster.readLong(hwnd, 基址类.解密基址);
        long c = GameMaster.readLong(hwnd, b + 8 * (a >> 16) + 56);
        int  d = GameMaster.readInt(hwnd,  c + 4 * (a & 65535) + 8476) & 65535;
        return GameMaster.readInt(hwnd, addr + 4) ^ (d | (d << 16));
    }

    public static 游戏状态枚举 取游戏状态(int hwnd) {
        return 游戏状态枚举.到游戏状态(GameMaster.readInt(hwnd, 基址类.游戏状态));
    }

    public static int 取当前消耗疲劳值(int hwnd) {
        return 解密(hwnd, 基址类.当前疲劳);
    }

    public static void 开启无视地图障碍(int hwnd) {
        long addr = GameMaster.readLong(hwnd, 基址类.人物基址);
        地图穿透默认值 = GameMaster.readInt(hwnd, addr + 偏移类.地图穿透);
        建筑穿透默认值 = GameMaster.readInt(hwnd, addr + 偏移类.建筑穿透);
        GameMaster.writeInt(hwnd, addr + 偏移类.地图穿透, 0);
        GameMaster.writeInt(hwnd, addr + 偏移类.建筑穿透, 0);
    }

    public static void 关闭无视地图障碍(int hwnd) {
        long addr = GameMaster.readLong(hwnd, 基址类.人物基址);
        GameMaster.writeInt(hwnd, addr + addr + 偏移类.地图穿透, 地图穿透默认值);
        GameMaster.writeInt(hwnd, addr + addr + 偏移类.建筑穿透, 建筑穿透默认值);
    }

    public static 坐标类 取物品坐标(int hwnd, long 物品数据) {
        long addr = GameMaster.readLong(hwnd, 物品数据 + 312);
        int x = (int) GameMaster.readFloat(hwnd, addr + 32);
        int y = (int) GameMaster.readFloat(hwnd, addr + 36);
        return new 坐标类(x, y);
    }

    public static 坐标类 取人物坐标(int hwnd, long 人物数据) {
        long addr = GameMaster.readLong(hwnd, 人物数据 + 848);
        int x = (int) GameMaster.readFloat(hwnd, addr);
        int y = (int) GameMaster.readFloat(hwnd, addr + 4);
        return new 坐标类(x, y);
    }

    /**
     * /**
     *      * 缓冲CALL
     *      * sub rsp, 100
     *      * mov rax, [14ac80e30]; 发包基址
     *      * mov edx, 包头
     *      * mov rax, 14ac80e30; 缓冲CALL
     *      * call rax
     *      * add rsp, 100
     *
     *      sub rsp, 100
     *      * mov rax, [1]
     *      * mov rcx, rax
     *      * mov edx, 2
     *      * add [rax], al
     *      * add [rax], al
     *      * mov rax, 3
     *      * call rax
     *      * add rsp, 100
     *
     *      sub rsp, 100
     *      mov rax, [14ae6c4e0]
     *      mov rcx, rax
     *      mov edx, 2a
     *      add [rax], al
     *      add [rax], al
     *      mov rax, 145257240
     *      call rax
     *      add rsp, 100
     *
     * @param hwnd
     * @param 包头
     */
    public static void 缓冲CALL(int hwnd, int 包头) {
        GameMaster.asmClear();
        GameMaster.asmAdd("sub rsp, 100");
        GameMaster.asmAdd(String.format("mov rax, [%x]", 基址类.发包基址));
        GameMaster.asmAdd("mov rcx, rax");
        GameMaster.asmAdd(String.format("mov edx, %x", 包头));
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd(String.format("mov rax, %x", 基址类.缓冲CALL));
        GameMaster.asmAdd("call rax");
        GameMaster.asmAdd("add rsp, 100");
        System.out.println(GameMaster.assemble(0, 1));
        GameMaster.asmCall(hwnd, 1);
    }


    /**
     * sub rsp, 100
     * mov rax, [1]; 发包基址
     * mov rcx, rax
     * mov rdx, 2; 参数
     * mov rax, 3; CALL 地址
     * call rax
     * add rsp, 100
     * @param hwnd
     * @param 参数
     * @param 长度
     */
    public static void 加密CALL(int hwnd, long 参数, int 长度) {
        long CALL地址 = 0;
        if (长度 == 1) {
            CALL地址 = 基址类.加密包CALL;
        } else if (长度 == 2) {
            CALL地址 = 基址类.加密包CALL + 64;
        } else if (长度 == 4) {
            CALL地址 = 基址类.加密包CALL + 128;
        } else if (长度 == 8) {
            CALL地址 = 基址类.加密包CALL + 192;
        }
        GameMaster.asmClear();
        GameMaster.asmAdd("sub rsp, 100");
        GameMaster.asmAdd(String.format("mov rax, [%x]", 基址类.发包基址));
        GameMaster.asmAdd("mov rcx, rax");
        GameMaster.asmAdd(String.format("mov rdx, %x", 参数));
        GameMaster.asmAdd(String.format("mov rax, %x", CALL地址));
        GameMaster.asmAdd("call rax");
        GameMaster.asmAdd("add rsp, 100");
        System.out.println(GameMaster.assemble(0, 1));
        GameMaster.asmCall(hwnd, 1);
    }

    public static void 发包CALL(int hwnd) {
        GameMaster.asmClear();
        GameMaster.asmAdd("sub rsp, 100");
        GameMaster.asmAdd(String.format("mov rax, %x", 基址类.发包CALL));
        GameMaster.asmAdd("call rax");
        GameMaster.asmAdd("add rsp, 100");
        System.out.println(GameMaster.assemble(0, 1));
        GameMaster.asmCall(hwnd, 1);
    }


    public static void 组包反角(int hwnd) {
        缓冲CALL(hwnd,7);
        发包CALL(hwnd);
    }

    public static void 组包选角(int hwnd, int 位置) {
        if (位置 < 0) return;
        缓冲CALL(hwnd, 4);
        加密CALL(hwnd, 位置, 2);
        发包CALL(hwnd);
    }

    public static void 组包出图(int hwnd) {
        缓冲CALL(hwnd,42);
        发包CALL(hwnd);
    }


}
