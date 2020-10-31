package com.dxf;

import com.dxf.core.GameMaster;
import com.dxf.model.坐标;

import java.awt.*;


public class 基础功能 {

    private static int 地图穿透默认值;
    private static int 建筑穿透默认值;

    public static int 解密(int hwnd, long addr) {
        int  a = GameMaster.readInt(hwnd, addr);
        long b = GameMaster.readLong(hwnd, 基址.解密基址);
        long c = GameMaster.readLong(hwnd, b + 8 * (a >> 16) + 56);
        int  d = GameMaster.readInt(hwnd,  c + 4 * (a & 65535) + 8476) & 65535;
        return GameMaster.readInt(hwnd, addr + 4) ^ (d | (d << 16));
    }

    public static 游戏状态 取游戏状态(int hwnd) {
        return 游戏状态.到游戏状态(GameMaster.readInt(hwnd, 基址.游戏状态));
    }

    public static int 取当前消耗疲劳值(int hwnd) {
        return 解密(hwnd, 基址.当前疲劳);
    }

    public static void 开启无视地图障碍(int hwnd) {
        long addr = GameMaster.readLong(hwnd, 基址.人物基址);
        地图穿透默认值 = GameMaster.readInt(hwnd, addr + 偏移.地图穿透);
        建筑穿透默认值 = GameMaster.readInt(hwnd, addr + 偏移.建筑穿透);
        GameMaster.writeInt(hwnd, addr + 偏移.地图穿透, 0);
        GameMaster.writeInt(hwnd, addr + 偏移.建筑穿透, 0);
    }

    public static void 关闭无视地图障碍(int hwnd) {
        long addr = GameMaster.readLong(hwnd, 基址.人物基址);
        GameMaster.writeInt(hwnd, addr + addr + 偏移.地图穿透, 地图穿透默认值);
        GameMaster.writeInt(hwnd, addr + addr + 偏移.建筑穿透, 建筑穿透默认值);
    }

    public static 坐标 取物品坐标(int hwnd, long 物品数据) {
        long addr = GameMaster.readLong(hwnd, 物品数据 + 312);
        int x = (int) GameMaster.readFloat(hwnd, addr + 32);
        int y = (int) GameMaster.readFloat(hwnd, addr + 36);
        return new 坐标(x, y);
    }

    public static 坐标 取人物坐标(int hwnd, long 人物数据) {
        long addr = GameMaster.readLong(hwnd, 人物数据 + 848);
        int x = (int) GameMaster.readFloat(hwnd, addr);
        int y = (int) GameMaster.readFloat(hwnd, addr + 4);
        return new 坐标(x, y);
    }


}
