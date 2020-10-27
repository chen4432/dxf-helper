package com.dxf.util;

import com.dxf.core.GameMaster;
import lombok.Data;

import javax.naming.SizeLimitExceededException;
import java.awt.*;
import java.util.List;

public class DXF {

    private final int hwnd;

    public DXF(int hwnd) {
        this.hwnd = hwnd;
    }

    public DXF() {
        hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
    }

    public int getHwnd() {
        return hwnd;
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
            public static final long MAP                = 336L;  // 地图偏移
            public static final long MAP_START          = 376L;  // 地图开始2
            public static final long MAP_END            = 384L;  // 地图结束2
            public static final long OBJ_NAME           = 1976L; // 名称偏移
            public static final long OBJ_TYPE           = 292L;  // 类型偏移
            public static final long OBJ_ZY             = 3496L; // 阵营偏移
            public static final long OBJ_HP             = 20392L;// 血量偏移
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

    public Point getPlayerPos() {
        long addr = GameMaster.readLong(hwnd, ADDRESS.BASE.PLAYER);
        return getHumanPos(addr);
    }

    @Data
    public static class MapInfo {
        private final Integer width;
        private final Integer height;

        public MapInfo() {
            this.width = 0;
            this.height = 0;
        }


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
        private DXF dxf;
        private int hwnd;

        private static final int OBJ_TYPE_CAMP_FIRE         =    4;       // 营火
        private static final int OBJ_TYPE_BUILDING          =   33;       // 建筑
        private static final int OBJ_TYPE_HUMAN             =   273;      // 人形
        private static final int OBJ_TYPE_GOLD_OBJ          =   289;      // 金币或材料
        private static final int OBJ_TYPE_MONSTER           =   529;      // 怪物
        private static final int OBJ_TYPE_DOOR_WALL         =  4129;      // 门或墙
        private static final int OBJ_ATTACK                 = 61400;      // 技能

        private static final int ZY_0                       =     0;      // 己方
        private static final int ZY_100                     =   100;      // 敌人
        private static final int ZY_200                     =   200;      // 建筑

        public RoomInfo(DXF dxf) {
            this.dxf = dxf;
            this.hwnd = dxf.getHwnd();
            long addr = GameMaster.readLong(hwnd, ADDRESS.BASE.PLAYER);
            long mapPtr = GameMaster.readLong(hwnd, addr + ADDRESS.OFFSET.MAP);
            long startAddr = GameMaster.readLong(hwnd, mapPtr + ADDRESS.OFFSET.MAP_START);
            long endAddr = GameMaster.readLong(hwnd, mapPtr + ADDRESS.OFFSET.MAP_END);
            for (long objAddr = startAddr; objAddr < endAddr; objAddr += 8) {
                long objPtr = GameMaster.readLong(hwnd, objAddr);
                String name = GameMaster.readStringAddr(hwnd, GameMaster.readLong(hwnd, objPtr + ADDRESS.OFFSET.OBJ_NAME), 1, 50);
                int type1 = GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_TYPE);
                int type2 = GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_TYPE + 4);
                if (type1 == OBJ_TYPE_HUMAN || type2 == OBJ_TYPE_HUMAN) continue;
                Point pos = dxf.getMonsterPos(objPtr);
                int zy = GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_ZY);
                int hp = (type2 == OBJ_TYPE_MONSTER) ? GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_HP) : 0;
                System.out.printf("name: %s\ttype1: %d\ttype2: %d\tzy:%d\thp:%d\tpos: %s\n",
                        name,
                        type1,
                        type2,
                        zy,
                        hp,
                        pos);
            }
        }

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

    private float xSpeed = 0.546f;
    private float ySpeed = 0.188f;

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


    public void moveTo(Point target) throws Exception {
        Point curr = getPlayerPos();
        String dirLR = (target.x > curr.x) ? "right" : "left";
        String dirUD = (target.y > curr.y) ? "down" : "up";
        int xTime = 0, yTime = 0;
        int xDistance = Math.abs(curr.x - target.x);
        if (xDistance != 0) {
            xTime = (int)(xDistance / xSpeed);
        }
        int yDistance = Math.abs(curr.y - target.y);
        if (yDistance != 0) {
            yTime = (int)(yDistance / ySpeed);
        }
        System.out.printf("xTime: %d\tyTime: %d\n", xTime, yTime);
        if (xDistance == 0) {
            GameMaster.keyDownChar(dirUD);
            Thread.sleep(yTime);
            GameMaster.keyUpChar(dirUD);
            return;
        }
        if (yDistance == 0) {
            GameMaster.keyPressChar(dirLR);
            Thread.sleep(30);
            GameMaster.keyDownChar(dirLR);
            Thread.sleep(xTime);
            GameMaster.keyUpChar(dirLR);
            return;
        }
        GameMaster.keyPressChar(dirLR);
        Thread.sleep(30);
        GameMaster.keyDownChar(dirLR);
        Thread.sleep(30);
        GameMaster.keyDownChar(dirUD);
        if (xTime > yTime) {
            Thread.sleep(yTime);
            GameMaster.keyUpChar(dirUD);
            Thread.sleep(xTime - yTime);
            GameMaster.keyUpChar(dirLR);
        } else {
            Thread.sleep(xTime);
            GameMaster.keyUpChar(dirLR);
            Thread.sleep(yTime - xTime);
            GameMaster.keyUpChar(dirUD);
        }
        Thread.sleep(100);
    }


}
