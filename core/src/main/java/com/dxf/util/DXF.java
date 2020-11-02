package com.dxf.util;

import com.dxf.core.GameMaster;
import lombok.Data;

import java.awt.*;

public class DXF {

    private final int 窗口句柄;

    public DXF(int 窗口句柄) {
        this.窗口句柄 = 窗口句柄;
    }

    public DXF() {
        窗口句柄 = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        if (窗口句柄 == 0) {
            System.out.println("地下城与勇士游戏未启动！");
            System.exit(-1);
        }
    }

    public int get窗口句柄() {
        return 窗口句柄;
    }

    public void setUp() {
        int ret = GameMaster.bindWindowEx(
                窗口句柄,
                "dx.graphic.2d",
                "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.cursor",
                "dx.keypad.input.lock.api|dx.keypad.state.api|dx.keypad.api",
                "dx.public.active.api|dx.public.active.message",
                0
                );
        System.out.println("BindWindowState:" + ret);
        try {
            Thread.sleep(1000);
            GameMaster.setWindowState(窗口句柄, 1);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
            private static final long ROOM_INDEX        = 5546913896L; // 房间编号

        }
        // 偏移
        public static class OFFSET {
            public static final int MAP                = 336;  // 地图偏移
            public static final int MAP_START          = 376;  // 地图开始2
            public static final int MAP_END            = 384;  // 地图结束2
            public static final int OBJ_NAME           = 1976; // 名称偏移
            public static final int OBJ_TYPE           = 292;  // 类型偏移
            public static final int OBJ_ZY             = 3496; // 阵营偏移
            public static final int OBJ_HP             = 20392;// 血量偏移
            public static final int OBJ_STATE          = 3392;
            public static final int IGNORE_MAP         = 1984; // 地图穿透
            public static final int IGNORE_OBSTACLE    = 1988; // 建筑穿越
            public static final int TIME               = 2138192; // 时间基址
            public static final int DOOR_TYPE          = 328; // 门型偏移
            public static final int MAP_CODE           = 6756; // 地图编码
            public static final int WIDTH_HEIGHT       = 1488; // 宽高偏移
            public static final int ARRAY              = 1528; // 数组偏移
            public static final int START_X            = 336; // 起始坐标X
            public static final int START_Y            = 340; // 终止坐标Y
            public static final int MAP_NAME           = 984; // 地图名称
            public static final int CURR_ROOM_X        = 6448; // 当前房间X
            public static final int CURR_ROOM_Y        = 6452; // 当前房间Y
            public static final int BOSS_ROOM_X        = 6780; // BOSS房间Y
            public static final int BOSS_ROOM_Y        = 6788; // BOSS房间Y
            public static final int CAMP_FIRE          = 6976; // 篝火判断
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
        GameMaster.asmCall(窗口句柄, 5);
    }

    /**
     * 获取怪物坐标
     * @param baseAddr 怪物指针
     * @return
     */
    public Point getMonsterPos(long baseAddr) {
        long addr = GameMaster.readLong(窗口句柄, baseAddr + 312);
        int x = (int) GameMaster.readFloat(窗口句柄, addr + 32);
        int y = (int) GameMaster.readFloat(窗口句柄, addr + 36);
        return new Point(x, y);
    }

    /**
     * 获取人物坐标
     * @param baseAddr 人物指针
     * @return
     */
    public Point getHumanPos(long baseAddr) {
        long addr = GameMaster.readLong(窗口句柄, baseAddr + 848);
        int x = (int) GameMaster.readFloat(窗口句柄, addr);
        int y = (int) GameMaster.readFloat(窗口句柄, addr + 4);
        return new Point(x, y);
    }

    public int decrypt(long addr) {
        int a = GameMaster.readInt(窗口句柄, addr);
        long b = GameMaster.readLong(窗口句柄, ADDRESS.BASE.DECRYPT);
        long c = GameMaster.readLong(窗口句柄, b + 8 * (a >> 16) + 56);
        int d = GameMaster.readInt(窗口句柄,  c + 4 * (a & 65535) + 8476) & 65535;
        return GameMaster.readInt(窗口句柄, addr + 4) ^ (d | (d << 16));
    }

    public int 解密(long 内存地址) {
        int a = GameMaster.readInt(窗口句柄, 内存地址);
        long b = GameMaster.readLong(窗口句柄, ADDRESS.BASE.DECRYPT);
        long c = GameMaster.readLong(窗口句柄, b + 8 * (a >> 16) + 56);
        int d = GameMaster.readInt(窗口句柄,  c + 4 * (a & 65535) + 8476) & 65535;
        return GameMaster.readInt(窗口句柄, 内存地址 + 4) ^ (d | (d << 16));
    }
    /**
     * 解密
     * @param addr 内存地址
     * @return
     */
    public int decrypt2(long addr) {
        long rax = GameMaster.readInt(窗口句柄, addr);
        long rsi = GameMaster.readLong(窗口句柄, ADDRESS.BASE.DECRYPT);
        long rdx = rax;
        rdx >>= 0x10;
        rdx = GameMaster.readInt(窗口句柄, rsi + rdx * 8 + 56);
        rax = rax & 0xFFFF;
        rax = GameMaster.readInt(窗口句柄, rdx + rax * 4 + 8476);
        rdx = (short) rax;
        rsi = rdx;
        rsi <<= 0x10;
        rsi = rsi ^ rdx;
        rdx = GameMaster.readLong(窗口句柄, addr + 4);
        rax = rsi ^ rdx;
        return (int)rax;
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
        return GameMaster.readInt(窗口句柄, ADDRESS.BASE.GAME_STATE);
    }

    private int IGNORE_MAP_ORI_VAL;
    private int IGNORE_OBSTACLE_ORI_VAL;
    /**
     * 开启无视地形和障碍物
     */
    public void ignoreMapAndObstacle() {
        long addr = GameMaster.readLong(窗口句柄, ADDRESS.BASE.PLAYER);
        IGNORE_MAP_ORI_VAL = GameMaster.readInt(窗口句柄, addr + ADDRESS.OFFSET.IGNORE_MAP);
        IGNORE_OBSTACLE_ORI_VAL = GameMaster.readInt(窗口句柄, addr + ADDRESS.OFFSET.IGNORE_OBSTACLE);
        GameMaster.writeInt(窗口句柄, addr + ADDRESS.OFFSET.IGNORE_MAP, 0);
        GameMaster.writeInt(窗口句柄, addr + ADDRESS.OFFSET.IGNORE_OBSTACLE, 0);
    }
    /**
     * 关闭无视地形和障碍物
     */
    public void unIgnoreMapAndObstacle() {
        long addr = GameMaster.readLong(窗口句柄, ADDRESS.BASE.PLAYER);
        GameMaster.writeInt(窗口句柄, addr + ADDRESS.OFFSET.IGNORE_MAP, IGNORE_MAP_ORI_VAL);
        GameMaster.writeInt(窗口句柄, addr + ADDRESS.OFFSET.IGNORE_OBSTACLE, IGNORE_OBSTACLE_ORI_VAL);
    }

    public Point getPlayerPos() {
        long addr = GameMaster.readLong(窗口句柄, ADDRESS.BASE.PLAYER);
        return getHumanPos(addr);
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

    public enum Direction {
        UP(1),
        DN(2),
        LL(3),
        RR(4);

        private final int code;

        Direction(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    private float xSpeed = 0.494f;
    private float ySpeed = 0.172f;

    private static final int KEY_UP = 38;
    private static final int KEY_DN = 40;
    private static final int KEY_LL = 37;
    private static final int KEY_RR = 39;

    public void measureSpeed() {
        Point start = getPlayerPos();
        GameMaster.keyPress(KEY_RR);
        try {
            Thread.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameMaster.keyDown(KEY_RR);
        try {
            Thread.sleep(30);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameMaster.keyDown(KEY_DN);
        int duration = 500;
        try {
            Thread.sleep(duration);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameMaster.keyUp(KEY_RR);
        GameMaster.keyUp(KEY_DN);
        Point end = getPlayerPos();
        int xDelta = end.x - start.x;
        int yDelta = end.y - start.y;
        xSpeed = (float)xDelta / duration;
        ySpeed = (float)yDelta / duration;
        System.out.printf("xSpeed: %f\tySpeed: %f", xSpeed, ySpeed);
    }
}
