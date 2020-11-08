package com.dxf.component;

import com.dxf.config.配置项类;
import com.dxf.constant.基址类;
import com.dxf.core.TP;
import com.dxf.model.坐标类;
import com.dxf.model.按键枚举;
import com.dxf.model.方向枚举;
import com.dxf.model.游戏状态枚举;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


@Slf4j
public class 人物角色类 {

    private final int 窗口句柄;
    private final String 名称;
    private final int 等级;
    private final String 职业;

    private CopyOnWriteArrayList<技能信息类> 技能栏;

    private Double 移动速度X = 0.44;
    private Double 移动速度Y = 0.18;

    public 人物角色类(int 窗口句柄) {
        this.窗口句柄 = 窗口句柄;
        名称 = TP.readStringAddr(窗口句柄, TP.readLong(窗口句柄, 基址类.人物名称), 1, 50);
        等级 = TP.readInt(窗口句柄, 基址类.角色等级);
        职业 = TP.readStringAddr(窗口句柄, TP.readLong(窗口句柄, 基址类.职业名称), 1, 50);
        设置技能配置();
        设置移动速度();
        ScheduledExecutorService 线程池 = Executors.newSingleThreadScheduledExecutor();
        线程池.scheduleAtFixedRate(new Task(), 1, 1000, TimeUnit.MILLISECONDS);
    }

    @Override
    public String toString() {
        return "人物角色类{" +
                "名称='" + 名称 + '\'' +
                ", 等级=" + 等级 +
                ", 职业='" + 职业 + '\'' +
                ", 移动速度X=" + 移动速度X +
                ", 移动速度Y=" + 移动速度Y +
                '}';
    }

    public void 设置技能配置() {
        技能栏 = new CopyOnWriteArrayList(配置项类.读取技能栏(名称));
    }

    public void 设置移动速度() {
        val 移动速度 = 配置项类.读取移动速度(名称);
        移动速度X = 移动速度.getKey();
        移动速度Y = 移动速度.getValue();
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

    private static final int KEY_UP = 38;
    private static final int KEY_DN = 40;
    private static final int KEY_LL = 37;
    private static final int KEY_RR = 39;

    public 坐标类 取人物坐标() {
        long 人物数据 = TP.readLong(窗口句柄, 基址类.人物基址);
        return 基础功能类.取人物坐标(窗口句柄, 人物数据);
    }

    public void 测试移动速度() throws InterruptedException {
        移动到(new 坐标类(0,0));
        基础功能类.延时(1000);
        调整方向(方向枚举.右);
        基础功能类.延时(1000);
        坐标类 起点 = 取人物坐标();
        long 计时开始 = System.currentTimeMillis();
        TP.keyPress(KEY_RR);
        基础功能类.延时(30);
        TP.keyDown(KEY_RR);
        基础功能类.延时(30);
        TP.keyDown(KEY_DN);
        基础功能类.延时(888);
        TP.keyUp(KEY_RR);
        TP.keyUp(KEY_DN);
        long 计时结束 = System.currentTimeMillis();
        坐标类 终点 = 取人物坐标();
        int xDelta = 终点.X() - 起点.X();
        int yDelta = 终点.Y() - 起点.Y();
        移动速度X = xDelta * 1.0 / (计时结束-计时开始);
        移动速度Y = yDelta * 1.0 / (计时结束-计时开始);
        System.out.printf("xSpeed: %f\tySpeed: %f\n", 移动速度X, 移动速度Y);
    }

    public void 移动到(坐标类 target) throws InterruptedException {
        坐标类 curr = 取人物坐标();
        long 计时开始 = System.currentTimeMillis();
        String dirLR = (target.X() > curr.X()) ? "right" : "left";
        String dirUD = (target.Y() > curr.Y()) ? "down" : "up";
        int xTime = 0, yTime = 0;
        int xDistance = Math.abs(curr.X() - target.X());
        if (xDistance != 0) {
            xTime = (int)(xDistance / 移动速度X);
        }
        int yDistance = Math.abs(curr.Y() - target.Y());
        if (yDistance != 0) {
            yTime = (int)(yDistance / 移动速度Y);
        }
        System.out.printf("xTime: %d\tyTime: %d\n", xTime, yTime);
        if (xDistance == 0) {
            TP.keyDownChar(dirUD);
            基础功能类.延时(yTime);
            TP.keyUpChar(dirUD);
            return;
        }
        if (yDistance == 0) {
            TP.keyPressChar(dirLR);
            基础功能类.延时(30);
            TP.keyDownChar(dirLR);
            基础功能类.延时(xTime);
            TP.keyUpChar(dirLR);
            return;
        }
        TP.keyPressChar(dirLR);
        基础功能类.延时(30);
        TP.keyDownChar(dirLR);
        基础功能类.延时(30);
        TP.keyDownChar(dirUD);
        if (xTime > yTime) {
            基础功能类.延时(yTime);
            TP.keyUpChar(dirUD);
            基础功能类.延时(xTime - yTime);
            TP.keyUpChar(dirLR);
        } else {
            基础功能类.延时(xTime);
            TP.keyUpChar(dirLR);
            基础功能类.延时(yTime - xTime);
            TP.keyUpChar(dirUD);
        }
        long 计时结束 = System.currentTimeMillis();
        int xDelta = Math.abs(target.X() - curr.X());
        int yDelta = Math.abs(target.Y() - curr.Y());
        double x = xDelta * 1.0 / (计时结束-计时开始);
        double y = yDelta * 1.0 / (计时结束-计时开始);
        System.out.printf("xSpeed: %f\tySpeed: %f\n", x, y);

    }

    public void 移动物品到脚下() {
        TP.keyPress(76);
    }

    public void 调整方向(方向枚举 dir) {
        try {
            if (dir == 方向枚举.右) TP.keyPressChar("right");
            if (dir == 方向枚举.左) TP.keyPressChar("left");
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void 普通房间释放技能() throws InterruptedException {
        技能栏.sort(Comparator.comparingInt(技能信息类::取技能优先级_普通房间));
        for (技能信息类 技能 : 技能栏) {
            if (!技能.是否冷却中() && 技能.是否攻击类型()) {
                if (技能.释放技能()) {
                    break;
                }
            }
        }
    }

    public void 领主房间释放技能() throws InterruptedException {
        技能栏.sort(Comparator.comparingInt(技能信息类::取技能优先级_领主房间));
        for (技能信息类 技能 : 技能栏) {
            if (!技能.是否冷却中() && 技能.是否攻击类型()) {
                if (技能.释放技能()) {
                    break;
                }
            }
        }
    }

    public void 释放状态技能() throws InterruptedException {
        log.info("开始释放BUFF技能...");
        for (技能信息类 技能 : 技能栏) {
            if (!技能.是否冷却中() && 技能.是否状态技能()) {
                技能.释放技能();
                基础功能类.延时(1000);
            }
        }
        log.info("释放BUFF技能完成!");
    }

    class Task implements Runnable {
        @Override
        public void run() {
            for (技能信息类 技能 : 技能栏) {
                技能.update();
            }
        }
    }

    public void BOSS房间清怪(房间信息类 room) throws InterruptedException {
        int cnt = 0;
        while (true) {
            log.info("BOSS房间清怪中...次数：{}", ++cnt);
            room.update();
            List<坐标类> 怪物列表 = room.取怪物列表();
            坐标类 pos = 取人物坐标();
            if (怪物列表.isEmpty()) {
                log.info("BOSS房间怪清理完毕！");
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
            领主房间释放技能();
        }
        //基础功能类.延时(1000);
    }

    public void 普通房间清怪(房间信息类 room) throws InterruptedException {
        int cnt = 0;
        while (true) {
            log.info("BOSS房间清怪中...次数：{}", ++cnt);
            room.update();
            List<坐标类> 怪物列表 = room.取怪物列表();
            坐标类 pos = 取人物坐标();
            if (怪物列表.isEmpty()) {
                log.info("普通房间怪清理完毕！");
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
            普通房间释放技能();
        }
        //基础功能类.延时(1000);
    }

    public void 执行刷图任务() throws InterruptedException {
        if (基础功能类.取游戏状态(窗口句柄) != 游戏状态枚举.在副本中) {
            log.info("未在副本中，退出。");
            return;
        }
        地图信息类 map = new 地图信息类(窗口句柄);
        log.info("地图信息： " + map);
        基础功能类.延时(1500);
        释放状态技能();
        坐标类 BOSS房间坐标 = map.取BOSS房间();
        while (!Thread.currentThread().isInterrupted()) {
            坐标类 当前房间坐标 = map.取当前房间坐标();
            房间信息类 room = new 房间信息类(窗口句柄);
            if (当前房间坐标.equals(BOSS房间坐标)) {
                BOSS房间清怪(room);
                if (room.判断是否通关()) {
                    log.info("通关完成...");
                    捡物(room);
                    基础功能类.等待直到符合条件(() -> {
                        TP.keyPressChar("ESC");
                        基础功能类.延时(500);
                        System.out.println("等待清算结束，返回城镇中...");
                        TP.keyPressChar("F12");
                        基础功能类.延时(500);
                        return 基础功能类.取游戏状态(窗口句柄) == 游戏状态枚举.城镇;
                    }, 10, 1000);
                    break;
                } else {
                    BOSS房间清怪(room);
                }
            } else {
                普通房间清怪(room);
                捡物(room);
                val dir = map.取过图方向(room.取当前房间坐标());
                if (dir == 方向枚举.未知) continue;
                val door = room.取过图门坐标(dir);
                精确过门(door, dir);
            }
        }
    }

    public void 精确过门(坐标类 door, 方向枚举 dir) throws InterruptedException {
        boolean succeed = false;
        for (int i = 0; i < 3; ++i) {
            val curr = new 房间信息类(窗口句柄).取当前房间坐标();
            移动到(door);
            基础功能类.延时(800);
            val next = new 房间信息类(窗口句柄).取当前房间坐标();
            if (!curr.equals(next)) {
                succeed = true;
                log.info("过门成功，门坐标：{}, 过门方向：{}", door, dir);
                break;
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
            TP.keyDownChar(key);
            基础功能类.延时(1000);
            TP.keyUpChar(key);
            //精确过门(door, dir);
        }
    }

    public void 捡物(房间信息类 room) throws InterruptedException {
        //Thread.sleep(500);
        TP.keyPressChar("v");
        //Thread.sleep(500);
    }

    public int get剩余疲劳值() {
        int 最大疲劳值 = 基础功能类.解密读取(窗口句柄, 基址类.最大疲劳);
        int 消耗疲劳值 = 基础功能类.解密读取(窗口句柄, 基址类.当前疲劳);
        return 最大疲劳值 - 消耗疲劳值;
    }

    public void 进图_根特皇宫() throws InterruptedException {
        if (基础功能类.取游戏状态(窗口句柄) != 游戏状态枚举.城镇) return;
        基础功能类.等待直到符合条件(() -> {
            TP.keyDownChar(按键枚举.方向左.getStrCode());
            return 基础功能类.取游戏状态(窗口句柄) == 游戏状态枚举.选择副本;
        }, 30, 1000);
        TP.keyUpChar(按键枚举.方向左.getStrCode());
        基础功能类.等待直到符合条件(() -> {
            TP.keyPressChar("right");
            基础功能类.延时(500);
            TP.keyPressChar("space");
            return 基础功能类.取游戏状态(窗口句柄) == 游戏状态枚举.在副本中;
        }, 30, 1000);
    }

}
