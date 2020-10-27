package com.dxf.util;

import com.dxf.core.GameMaster;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.awt.*;
import java.util.List;

public class DXF {

    private final int hwnd;

    public DXF(int hwnd) {
        this.hwnd = hwnd;
    }

    public void setUp() {
        GameMaster.bindWindowEx(
                hwnd,
                "dx.graphic.2d",
                "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.cursor",
                "dx.keypad.input.lock.api|dx.keypad.state.api|dx.keypad.api",
                "dx.public.active.api|dx.public.active.message",
                0
                );
    }

    public void cleanUp() {
        GameMaster.unbindWindow();
    }

    public static class ADDRESS {
        // 基址
        public static class BASE {
            private static final long PLAYER            = 5549592112L; // 人物基址
            private static final long GAME_STATE        = 5545477624L; // 游戏状态
            private static final long DECRYPT           = 5549974944L; // 解密基址
        }
        // 偏移
        public static class OFFSET {
            public static final long IGNORE_MAP         = 1984L; // 地图穿透
            public static final long IGNORE_OBSTACLE    = 1988L; // 建筑穿越
        }
    }

    public static class OBJECT_CODE {
        public static final int[] DEFAULT_BUFF = {
                2600021,    // 精神刺激 20冷却
                2600561,    // 顶级175力量药
                2600562,    // 顶级175智力药
        };
    }

    /**
     * 消耗品CLL
     * @param code 消耗品代码
     */
    public void consumeObject(int code) {
        GameMaster.asmClear();
        GameMaster.asmAdd("push rbx");
        GameMaster.asmAdd("sub rsp, 28");
        GameMaster.asmAdd("mov rax," + Long.toHexString(ADDRESS.BASE.PLAYER));
        GameMaster.asmAdd("mov rax, [rax]");
        GameMaster.asmAdd("mov r8, [rax]");
        GameMaster.asmAdd("mov edx," + Integer.toHexString(code));
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("mov rcx, rax");
        GameMaster.asmAdd("call qword [r8+1280]");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add rsp, 28");
        GameMaster.asmAdd("pop rbx");
        GameMaster.asmAdd("ret");
        GameMaster.asmCall(hwnd, 5);
    }

    /**
     * 获取怪物坐标
     * @param baseAddr 怪物指针
     * @return
     */
    public Point getMonsterPos(long baseAddr) {
        long addr = GameMaster.readLong(hwnd, baseAddr + 312);
        int x = (int) GameMaster.readFloat(hwnd, addr + 32);
        int y = (int) GameMaster.readFloat(hwnd, addr + 36);
        return new Point(x, y);
    }

    /**
     * 获取人物坐标
     * @param baseAddr 人物指针
     * @return
     */
    public Point getHumanPos(long baseAddr) {
        long addr = GameMaster.readLong(hwnd, baseAddr + 848);
        int x = (int) GameMaster.readFloat(hwnd, addr);
        int y = (int) GameMaster.readFloat(hwnd, addr + 4);
        return new Point(x, y);
    }

    /**
     * 解密
     * @param addr 内存地址
     * @return
     */
    public int decrypt(long addr) {
        int eax = GameMaster.readInt(hwnd, addr);
        int esi = GameMaster.readInt(hwnd, ADDRESS.BASE.DECRYPT);
        int edx = eax;
        edx >>= 0x10;
        edx = GameMaster.readInt(hwnd, esi + edx * 4 + 56);
        eax = eax & 0xFFFF;
        eax = GameMaster.readInt(hwnd, edx + eax * 4 + 8476);
        edx = (short) eax;
        esi = edx;
        esi <<= 0x10;
        esi = esi ^ edx;
        edx = GameMaster.readInt(hwnd, addr + 4);
        eax = esi ^ edx;
        return eax;
    }

    /**
     * 游戏状态定义
     */
    public static class GameState {
        public static final int CHOOSING_PLAYER     = 0; // 选择角色界面
        public static final int IN_CITY             = 1; // 城镇中
        public static final int CHOOSING_MAP        = 2; // 选择副本地图界面
        public static final int IN_MAP              = 3; // 副本地图中
        public static final int ENTERING_CHANNEL    = 5; // 进入频道中
    }
    public int getGameState() {
        return GameMaster.readInt(hwnd, ADDRESS.BASE.GAME_STATE);
    }

    private int IGNORE_MAP_ORI_VAL;
    private int IGNORE_OBSTACLE_ORI_VAL;
    /**
     * 开启无视地形和障碍物
     */
    public void ignoreMapAndObstacle() {
        long addr = GameMaster.readLong(hwnd, ADDRESS.BASE.PLAYER);
        IGNORE_MAP_ORI_VAL = GameMaster.readInt(hwnd, addr + ADDRESS.OFFSET.IGNORE_MAP);
        IGNORE_OBSTACLE_ORI_VAL = GameMaster.readInt(hwnd, addr + ADDRESS.OFFSET.IGNORE_OBSTACLE);
        GameMaster.writeInt(hwnd, addr + ADDRESS.OFFSET.IGNORE_MAP, 0);
        GameMaster.writeInt(hwnd, addr + ADDRESS.OFFSET.IGNORE_OBSTACLE, 0);
    }
    /**
     * 关闭无视地形和障碍物
     */
    public void unIgnoreMapAndObstacle() {
        long addr = GameMaster.readLong(hwnd, ADDRESS.BASE.PLAYER);
        GameMaster.writeInt(hwnd, addr + ADDRESS.OFFSET.IGNORE_MAP, IGNORE_MAP_ORI_VAL);
        GameMaster.writeInt(hwnd, addr + ADDRESS.OFFSET.IGNORE_OBSTACLE, IGNORE_OBSTACLE_ORI_VAL);
    }

    @Data
    public static class MapInfo {
        private final Integer width;
        private final Integer height;


    }

    @Data
    public static class SkillInfo {
        private String key;
        private Point range;
        private Integer time;

    }

    @Data
    public static class PlayerInfo {
        private final String name;
        private final int level;
        private int hp;
        private int mp;

        private Point pos;

        public PlayerInfo() {
            this.name = "";
            this.level = 0;
            update();
        }

        void update() {

        }
    }

    @Data
    public static class RoomInfo {
        private Point index;                // 房间索引
        private List<MonsterInfo> monsters;
        private List<Point> golds;
        private Point player;
        private Boolean doorOpen;

        @Data
        public static class MonsterInfo {
            private String name;
            private Long code;
            private Point pos;
            private Integer hp;
        }

        void update() {

        }


    }


}
