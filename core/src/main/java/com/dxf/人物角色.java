package com.dxf;

import com.dxf.core.GameMaster;
import com.dxf.model.坐标类;
import com.dxf.util.DXF;
import com.dxf.util.刷图状态;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Slf4j
public class 人物角色 {

    private final DXF dxf;
    private final String 名称;
    private final int 等级;
    private final String 职业;

    private final CopyOnWriteArrayList<技能信息类> 技能栏 = new CopyOnWriteArrayList<技能信息类>();

    private final ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();

    private Double 移动速度X = 0.48;
    private Double 移动速度Y = 0.17;

    public 人物角色(DXF dxf) {
        this.dxf = dxf;
        名称 = GameMaster.readStringAddr(dxf.getHwnd(), GameMaster.readLong(dxf.getHwnd(), 基址.人物名称), 1, 50);
        等级 = GameMaster.readInt(dxf.getHwnd(), 基址.角色等级);
        职业 = GameMaster.readStringAddr(dxf.getHwnd(), GameMaster.readLong(dxf.getHwnd(), 基址.职业名称), 1, 50);
        读配置();
        //try {测试移动速度();} catch (Exception e) {e.printStackTrace();}
        pool.scheduleAtFixedRate(new Task(), 10, 1000, TimeUnit.MILLISECONDS);
    }

    public String 取名称() {
        return 名称;
    }

    public int 取等级() {
        return 等级;
    }

    public String 取职业() {
        return 职业;
    }

    public void 读配置() {
        if ("大暗黑天".equals(职业) || "天帝".equals(职业)) {
            技能栏.add(new 技能信息类("波动爆发", "A", 8, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("地裂波动剑", "S", 3, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("鬼斩","D", 6, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("邪光斩","F", 10, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("裂波斩","G", 8, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("鬼印珠","H", 6, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("冰刃波动剑","Q", 7, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("爆炎波动剑","W", 15, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("极冰裂波剑","E", 20, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("极炎裂波剑","R", 35, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("天雷降昏杵","T", 45, 技能信息类.技能类型.BUFF));
            技能栏.add(new 技能信息类("雷神降世","Y", 140, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("杀意波动","ctrl", 5, 技能信息类.技能类型.BUFF));
            技能栏.add(new 技能信息类("波动刻印", "alt", 20, 技能信息类.技能类型.BUFF));
            技能栏.add(new 技能信息类("远古记忆", "space", 20, 技能信息类.技能类型.BUFF));
            移动速度X = 0.48;
            移动速度Y = 0.17;
        }
        else if ("重霄·弹药专家".equals(职业)) {
            技能栏.add(new 技能信息类("G-18C冰冻手雷", "A", 4, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("G-35L感电手雷", "S", 3, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("镭射狙击","D", 41, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("G-16重力手雷","F", 18, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("凝固汽油弹","G", 18, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("G-14手雷","H", 2, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("重火力支援","Q", 40, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("轻火力速射","W", 27, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("皇鹰特战队","E", 54, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("黑玫瑰特种部队","R", 145, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("超新星核爆","T", 180, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("赤魂风暴狙击","Y", 290, 技能信息类.技能类型.攻击));
            //技能栏.add(new 技能信息("杀意波动","ctrl", 5, 技能信息.技能类型.BUFF));
            技能栏.add(new 技能信息类("爆裂弹", "ctrl", 5, 技能信息类.技能类型.BUFF));
            技能栏.add(new 技能信息类("远古记忆", "space", 20, 技能信息类.技能类型.BUFF));
            //技能栏.add(new 技能信息("普通攻击", "X", 0, 技能信息.技能类型.攻击));
            移动速度X = 0.6;
            移动速度Y = 0.28;
        } else {
            // 通用
            技能栏.add(new 技能信息类("A", "A", 10, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("S", "S", 10, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("D","D", 10, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("F","F", 20, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("G","G", 20, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("H","H", 20, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("Q","Q", 40, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("W","W", 40, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("E","E", 40, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("R","R", 40, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("T","T", 40, 技能信息类.技能类型.攻击));
            技能栏.add(new 技能信息类("Y","Y", 40, 技能信息类.技能类型.攻击));
            //技能栏.add(new 技能信息("杀意波动","ctrl", 5, 技能信息.技能类型.BUFF));
            //技能栏.add(new 技能信息("爆裂弹", "ctrl", 5, 技能信息.技能类型.BUFF));
            //技能栏.add(new 技能信息("远古记忆", "space", 20, 技能信息.技能类型.BUFF));
            //技能栏.add(new 技能信息("普通攻击", "X", 0, 技能信息.技能类型.攻击));
            移动速度X = 0.58;
            移动速度Y = 0.21;
        }
    }

    private static final int KEY_UP = 38;
    private static final int KEY_DN = 40;
    private static final int KEY_LL = 37;
    private static final int KEY_RR = 39;

    public 坐标类 取人物坐标() {
        long addr = GameMaster.readLong(dxf.getHwnd(), 基址.人物基址);
        return 基础功能类.取人物坐标(dxf.getHwnd(), addr);
    }

    public void 测试移动速度() throws Exception {
        移动到(new 坐标类(0,0));
        Thread.sleep(1000);
        调整方向(方向枚举.右);
        Thread.sleep(1000);
        坐标类 起点 = 取人物坐标();
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
        坐标类 终点 = 取人物坐标();
        int xDelta = 终点.getX() - 起点.getX();
        int yDelta = 终点.getY() - 起点.getY();
        移动速度X = xDelta * 1.0 / duration;
        移动速度Y = yDelta * 1.0 / duration;
        System.out.printf("xSpeed: %f\tySpeed: %f\n", 移动速度X, 移动速度Y);
    }

    public void 移动到(坐标类 target) throws Exception {
        坐标类 curr = 取人物坐标();
        String dirLR = (target.getX() > curr.getX()) ? "right" : "left";
        String dirUD = (target.getY() > curr.getY()) ? "down" : "up";
        int xTime = 0, yTime = 0;
        int xDistance = Math.abs(curr.getX() - target.getX());
        if (xDistance != 0) {
            xTime = (int)(xDistance / 移动速度X);
        }
        int yDistance = Math.abs(curr.getY() - target.getY());
        if (yDistance != 0) {
            yTime = (int)(yDistance / 移动速度Y);
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

    public void 移动物品到脚下() {
        GameMaster.keyPress(76);
    }

    public void 调整方向(方向枚举 dir) {
        try {
            if (dir == 方向枚举.右) GameMaster.keyPressChar("right");
            if (dir == 方向枚举.左) GameMaster.keyPressChar("left");
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void 释放技能(int cnt) throws Exception {
        GameMaster.keyDownChar("X");
        for (技能信息类 技能 : 技能栏) {
            System.out.println(技能);
            if (技能.取技能状态() == 技能信息类.技能状态.正常 && 技能.取技能类型() == 技能信息类.技能类型.攻击) {
                GameMaster.keyPressChar(技能.使用技能());
                System.out.println("释放技能： " + 技能.取技能按键());
                Thread.sleep(1000);
                if (--cnt == 0) break;
            }
        }
        GameMaster.keyUpChar("X");
    }

    class Task implements Runnable {
        @Override
        public void run() {
            for (技能信息类 技能 : 技能栏) {
                技能.update();
            }
        }
    }

    public void 房间清怪(房间信息类 room) throws Exception {
        while (true) {
            room.update();
            List<坐标类> 怪物列表 = room.取怪物列表();
            坐标类 pos = 取人物坐标();
            if (怪物列表.isEmpty()) {
                log.info("房间怪清理完毕！");
                break;
            } else {
                怪物列表.sort((a, b) -> {
                    if (坐标类.计算距离(pos, a) == 坐标类.计算距离(pos, b)) return 0;
                    return 坐标类.计算距离(pos, a) > 坐标类.计算距离(pos, b)? 1 : -1;
                });
            }
            坐标类 最近的怪物坐标 = 怪物列表.get(0);
            log.info("最近的怪物坐标： {}", 最近的怪物坐标);
            移动到(最近的怪物坐标);
            释放技能(1);
        }
        Thread.sleep(1000);
    }

    public void 加BUFF() throws Exception {
        log.info("加BUFF");
        /*
        for (技能信息 技能 : 技能栏) {
            if (技能.取技能状态() == 技能信息.技能状态.正常 && 技能.取技能类型() == 技能信息.技能类型.BUFF) {
                GameMaster.keyPressChar(技能.使用技能());
                Thread.sleep(1000);
            }
        }
         */
        GameMaster.keyPressChar("right");
        Thread.sleep(200);
        GameMaster.keyPressChar("right");
        Thread.sleep(200);
        GameMaster.keyPressChar("space");
        Thread.sleep(200);

        GameMaster.keyPressChar("up");
        Thread.sleep(200);
        GameMaster.keyPressChar("up");
        Thread.sleep(200);
        GameMaster.keyPressChar("space");
        Thread.sleep(200);

        GameMaster.keyPressChar("up");
        Thread.sleep(200);
        GameMaster.keyPressChar("down");
        Thread.sleep(200);
        GameMaster.keyPressChar("space");
        Thread.sleep(200);
    }

    public void 自动刷图() throws Exception {
        while (基础功能类.取当前消耗疲劳值(dxf.getHwnd()) < 184) {
            开始刷图();
        }
        开始刷图();
    }

    public void 开始刷图() throws Exception {
        if (基础功能类.取游戏状态(dxf.getHwnd()) != 游戏状态枚举.在副本中) return;
        地图信息类 map = new 地图信息类(dxf);
        加BUFF();
        while (true) {
            房间信息类 room = new 房间信息类(dxf);
            if (room.判断是否通关()) {
                log.info("通关成功.");
                //Thread.sleep(10000); // 等待
                //log.info("移动物品到脚下...");
                //移动物品到脚下();
                //Thread.sleep(500);
                //room.update();
                //log.info("正在捡物...物品数量： {}", room.取材料列表().size());
                //for (int i = 0; i < room.取材料列表().size(); ++i) {
                //    GameMaster.keyPressChar("X");
                //    Thread.sleep(300);
                //}
                //Thread.sleep(3000);
                if (基础功能类.取当前消耗疲劳值(dxf.getHwnd()) < 180) {
                    //GameMaster.keyPressChar("F10");
                    while (基础功能类.取游戏状态(dxf.getHwnd()) == 游戏状态枚举.在副本中 && room.取当前房间坐标().equals(map.取BOSS房间())) {
                        GameMaster.keyPressChar("ESC");
                        Thread.sleep(500);
                        System.out.println("等待清算结束...");
                        GameMaster.keyPressChar("F10");
                        Thread.sleep(500);
                    }
                    while (基础功能类.取游戏状态(dxf.getHwnd()) != 游戏状态枚举.在副本中) {
                        Thread.sleep(500);
                        System.out.println("正在进入新地图！");
                    }
                }
                break;
            }
            房间清怪(room);
            捡物(room);
            val dir = map.取过图方向(room.取当前房间坐标());
            if (dir == 方向枚举.未知) continue;
            val door = room.取过图门坐标(dir);
            精确过门(door, dir);
        }
    }

    public void 精确过门(坐标类 door, 方向枚举 dir) {
        boolean succeed = false;
        for (int i = 0; i < 3; ++i) {
            try {
                val curr = new 房间信息类(dxf).取当前房间坐标();
                移动到(door);
                Thread.sleep(800);
                val next = new 房间信息类(dxf).取当前房间坐标();
                if (!curr.equals(next)) {
                    succeed = true;
                    log.info("过门成功，门坐标：{}, 过门方向：{}", door, dir);
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!succeed) {
            log.info("遇到卡门情况，正在调整...");
            String key = "";
            switch (dir) {
                case 上:
                    key  = "down";
                    break;
                case 下:
                    key = "up";
                    break;
                case 左:
                    key = "right";
                    break;
                case 右:
                    key = "left";
                    break;
            }
            GameMaster.keyDownChar(key);
            try {Thread.sleep(1000);} catch (Exception e) {e.printStackTrace();}
            GameMaster.keyUpChar(key);
            精确过门(door, dir);
        }
    }

    public void 捡物(房间信息类 room) {
        /*
        room.update();
        for (坐标 pos : room.取材料列表()) {
            try {
                移动到(pos);
                Thread.sleep(500);
                GameMaster.keyPressChar("x");
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */
        try {
            Thread.sleep(500);
            GameMaster.keyPressChar("v");
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void 刷图控制(刷图状态 state) {

    }


}
