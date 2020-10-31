package com.dxf.util;

import com.dxf.core.GameMaster;
import com.dxf.model.Point2D;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DXF {

    private final int hwnd;

    public DXF(int hwnd) {
        this.hwnd = hwnd;
    }

    public DXF() {
        hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        if (hwnd == 0) {
            System.out.println("地下城与勇士游戏未启动！");
            System.exit(-1);
        }
    }

    public int getHwnd() {
        return hwnd;
    }

    public void setUp() {
        int ret = GameMaster.bindWindowEx(
                hwnd,
                "dx.graphic.2d",
                "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.cursor",
                "dx.keypad.input.lock.api|dx.keypad.state.api|dx.keypad.api",
                "dx.public.active.api|dx.public.active.message",
                0
                );
        System.out.println("BindWindowState:" + ret);
        try {
            Thread.sleep(1000);
            GameMaster.setWindowState(hwnd, 1);
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

    public int decrypt(long addr) {
        int a = GameMaster.readInt(hwnd, addr);
        long b = GameMaster.readLong(hwnd, ADDRESS.BASE.DECRYPT);
        long c = GameMaster.readLong(hwnd, b + 8 * (a >> 16) + 56);
        int d = GameMaster.readInt(hwnd,  c + 4 * (a & 65535) + 8476) & 65535;
        return GameMaster.readInt(hwnd, addr + 4) ^ (d | (d << 16));
    }

    public int 解密(long 内存地址) {
        int a = GameMaster.readInt(hwnd, 内存地址);
        long b = GameMaster.readLong(hwnd, ADDRESS.BASE.DECRYPT);
        long c = GameMaster.readLong(hwnd, b + 8 * (a >> 16) + 56);
        int d = GameMaster.readInt(hwnd,  c + 4 * (a & 65535) + 8476) & 65535;
        return GameMaster.readInt(hwnd, 内存地址 + 4) ^ (d | (d << 16));
    }
    /**
     * 解密
     * @param addr 内存地址
     * @return
     */
    public int decrypt2(long addr) {
        long rax = GameMaster.readInt(hwnd, addr);
        long rsi = GameMaster.readLong(hwnd, ADDRESS.BASE.DECRYPT);
        long rdx = rax;
        rdx >>= 0x10;
        rdx = GameMaster.readInt(hwnd, rsi + rdx * 8 + 56);
        rax = rax & 0xFFFF;
        rax = GameMaster.readInt(hwnd, rdx + rax * 4 + 8476);
        rdx = (short) rax;
        rsi = rdx;
        rsi <<= 0x10;
        rsi = rsi ^ rdx;
        rdx = GameMaster.readLong(hwnd, addr + 4);
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
        private final Integer                           width;
        private final Integer                           height;
        private final Point2D                           bossRoom;
        private Point2D                                 currRoom;
        private DXF                                     dxf;
        private int                                     hwnd;

        private List<Point2D>                           path;
        public void setPath(List<Point2D> path) {
            this.path = path;
        }

        public MapInfo(DXF dxf) {
            this.dxf = dxf;
            this.hwnd = dxf.getHwnd();
            long roomData = GameMaster.readLong(
                    hwnd,
                    ADDRESS.BASE.ROOM_INDEX,
                    ADDRESS.OFFSET.TIME,
                    ADDRESS.OFFSET.DOOR_TYPE
            );

            int bossRoomX = dxf.decrypt(roomData + ADDRESS.OFFSET.BOSS_ROOM_X);
            int bossRoomY = dxf.decrypt(roomData + ADDRESS.OFFSET.BOSS_ROOM_Y);
            bossRoom = new Point2D(bossRoomX, bossRoomY);
            System.out.println("BossRoom: " + bossRoom);

            int roomIndex = dxf.decrypt(roomData + ADDRESS.OFFSET.MAP_CODE);
            width  = GameMaster.readInt(hwnd, roomData + ADDRESS.OFFSET.WIDTH_HEIGHT,
                    roomIndex * 8);
            height = GameMaster.readInt(hwnd, roomData + ADDRESS.OFFSET.WIDTH_HEIGHT,
                    roomIndex * 8 + 4);
            System.out.println("width: " + width + ", height:" + height);

            long tmp = GameMaster.readLong(hwnd, roomData + ADDRESS.OFFSET.ARRAY, 40*roomIndex + 8);
            int channelNum = width * height;
            int[] channels = new int[channelNum];
            // channel & 1 != 0 has right door
            // channel & 2 != 0 has up door
            // channel & 4 != 0 has left door
            // channel & 8 != 0 has down door
            for (int i = 0; i < channelNum; ++i) {
                channels[i] = GameMaster.readInt(hwnd, tmp + i * 4);
            }

            for (int i = 0; i < channels.length; i++) {
                System.out.print(channels[i] + " ");
            }
            System.out.println();

            update();
        }

        public void update() {
            long roomData = GameMaster.readLong(
                    hwnd,
                    ADDRESS.BASE.ROOM_INDEX,
                    ADDRESS.OFFSET.TIME,
                    ADDRESS.OFFSET.DOOR_TYPE
            );
            long startRoomX = GameMaster.readLong(hwnd, roomData + ADDRESS.OFFSET.CURR_ROOM_X);
            long startRoomY = GameMaster.readLong(hwnd, roomData + ADDRESS.OFFSET.CURR_ROOM_Y);
            currRoom = new Point2D((int)startRoomX, (int)startRoomY);
            System.out.println("CurrRoom: " + currRoom);
        }

        //public int toChannelIndex(Point2D pos) {
        //    return pos.y * width + pos.x;
        //}

        //public Point2D toCoordinate(int channelIndex) {
        //    return new Point2D(channelIndex % width, channelIndex / width);
        //}
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

    @Data
    public static class RoomInfo {
        private Point                                   index;                          // 房间索引
        private List<Point2D>                           doors = new ArrayList<>();      // 门
        private List<Point2D>                           monsters = new ArrayList<>();   // 怪物
        private List<Point2D>                           materials = new ArrayList<>();  // 材料
        private Point                                   player;
        private Boolean                                 doorOpen;
        private DXF                                     dxf;
        private int                                     hwnd;

        public boolean idDoorOpen() {
            return false;
        }

        public Point2D nextDoorPos(Direction direction) {
            switch (direction) {
                case UP:
                    doors.sort((a, b) -> {
                        if (a.y == b.y) return 0;
                        return a.y > b.y ? 1 : -1;
                    });
                    break;
                case DN:
                    doors.sort((a, b) -> {
                        if (a.y == b.y) return 0;
                        return a.y < b.y ? 1 : -1;
                    });
                    break;
                case LL:
                    doors.sort((a, b) -> {
                        if (a.x == b.x) return 0;
                        return a.x > b.y ? 1 : -1;
                    });
                    break;
                case RR:
                    doors.sort((a, b) -> {
                        if (a.x == b.x) return 0;
                        return a.x < b.x ? 1 : -1;
                    });
                    break;
            }
            return doors.get(0);
        }

        public Optional<Point2D> nextMaterialPos(Point2D playerPos) {
            if (materials.isEmpty()) return Optional.empty();
            materials.sort((a, b) -> {
                if (Point2D.distance(playerPos, a) == Point2D.distance(playerPos, b)) return 0;
                return Point2D.distance(playerPos, a) > Point2D.distance(playerPos, a)? 1 : -1;
            });
            return Optional.of(materials.get(0));
        }

        public Optional<Point2D> nextMonsterPos(Point2D playerPos) {
            if (monsters.isEmpty()) return Optional.empty();
            monsters.sort((a, b) -> {
                if (Point2D.distance(playerPos, a) == Point2D.distance(playerPos, b)) return 0;
                return Point2D.distance(playerPos, a) > Point2D.distance(playerPos, b)? 1 : -1;
            });
            return Optional.of(monsters.get(0));
        }

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
            update();
        }

        void update() {
            long addr = GameMaster.readLong(hwnd, ADDRESS.BASE.PLAYER);
            long mapPtr = GameMaster.readLong(hwnd, addr + ADDRESS.OFFSET.MAP);
            long startAddr = GameMaster.readLong(hwnd, mapPtr + ADDRESS.OFFSET.MAP_START);
            long endAddr = GameMaster.readLong(hwnd, mapPtr + ADDRESS.OFFSET.MAP_END);
            doors.clear();
            monsters.clear();
            materials.clear();
            for (long objAddr = startAddr; objAddr < endAddr; objAddr += 8) {
                long objPtr = GameMaster.readLong(hwnd, objAddr);
                String name = GameMaster.readStringAddr(hwnd, GameMaster.readLong(hwnd, objPtr + ADDRESS.OFFSET.OBJ_NAME), 1, 50);
                int type1 = GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_TYPE);
                int type2 = GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_TYPE + 4);
                if (type1 == OBJ_TYPE_HUMAN || type2 == OBJ_TYPE_HUMAN) continue;
                int state = GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_STATE); // 0 或者 1 表示是可通过门
                Point pos = dxf.getMonsterPos(objPtr);
                if (type2 == OBJ_TYPE_DOOR_WALL && (state == 0 || state == 1)) {
                    doors.add(new Point2D(pos.x, pos.y));
                    System.out.println("door: " + new Point2D(pos.x, pos.y));
                }
                int hp = (type2 == OBJ_TYPE_MONSTER) ? GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_HP) : 0;
                if (type2 == OBJ_TYPE_MONSTER && hp != 0) {
                    monsters.add(new Point2D(pos.x, pos.y));
                }
                int zy = GameMaster.readInt(hwnd, objPtr + ADDRESS.OFFSET.OBJ_ZY);

                System.out.printf("name: %s\ttype1: %d\ttype2: %d\tstate:%d\tzy:%d\thp:%d\tpos: %s\n",
                        name,
                        type1,
                        type2,
                        state,
                        zy,
                        hp,
                        pos);
            }

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


    public void moveTo(Point2D target) throws Exception {
        Point curr = getPlayerPos();
        String dirLR = (target.x > curr.x) ? "right" : "left";
        String dirUD = (target.y > curr.y) ? "down" : "up";
        int xTime = 0, yTime = 0;
        int xDistance = Math.abs(curr.x - target.x);
        System.out.println(xDistance);
        if (xDistance != 0) {
            xTime = (int)(xDistance / xSpeed);
        }
        int yDistance = Math.abs(curr.y - target.y);
        System.out.println(yDistance);
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
