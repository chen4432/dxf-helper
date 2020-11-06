package com.dxf.component;


import com.dxf.core.TP;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class 技能信息类 {

    public enum 技能状态枚举 {正常, 冷却中}
    public enum 技能类型枚举 {攻击, BUFF}

    private static final int    默认优先级_普通房间 = 100;
    private static final int    默认优先级_领主房间 = 100;
    private static final int    默认按键时间 = 0;
    private static final int    默认按键次数 = 1;
    private static final int    默认按键间隔时间 = 50;
    private static final String 默认辅助按键 = "";
    private static final int    默认辅助按键次数 = 0;
    private static final int    默认辅助按键间隔 = 50;


    private final String        名称;
    private final String        按键;
    private final int           冷却时间;
    private final 技能类型枚举    类型;
    private final int           按键时间;               // 毫秒
    private final int           按键次数;               // 按键次数
    private final int           按键间隔时间;            // 多次按键之间的间隔
    private final String        辅助按键;
    private final int           辅助按键次数;
    private final int           辅助按键间隔;
    private final int           技能优先级_普通房间;
    private final int           技能优先级_领主房间;

    private int                 tick;
    private 技能状态枚举          状态 = 技能状态枚举.正常;


    public void update() {
        if (tick > 0) {
            tick--;
        } else {
            状态 = 技能状态枚举.正常;
        }
    }

    public String 使用技能() {
        tick = 冷却时间;
        状态 = 技能状态枚举.冷却中;
        return 按键;
    }

    public boolean 释放技能() throws InterruptedException {
        if (状态 == 技能状态枚举.冷却中) return false;
        switch (类型) {
            case BUFF:
                List<String> 按键序列 = Arrays.stream(按键.split("\\|")).collect(Collectors.toList());
                log.info("BUFF技能名称：{}, 按键序列：{}", 名称, 按键序列);
                TP.keyPressCharList(按键序列, 20);
                break;
            case 攻击:
                if (按键时间 > 0) {
                    log.info("攻击技能名称: {}, 按键时间: {}", 名称, 按键时间);
                    TP.keyDownChar(按键);
                    基础功能类.延时(按键时间);
                    TP.keyUpChar(按键);
                } else {
                    log.info("攻击技能名称: {}, 按键时间: {}, 按键次数: {}", 名称, 按键时间, 按键次数);
                    TP.keyPressChar(按键);
                    if (按键次数 > 1) {
                        for (int i = 1; i < 按键次数; ++i) {
                            基础功能类.延时(按键间隔时间);
                            TP.keyUpChar(按键);
                        }
                    }
                }
                tick = 冷却时间;
                状态 = 技能状态枚举.冷却中;
                break;
        }
        return true;
    }

    public int get技能优先级_普通房间() {
        return 技能优先级_普通房间;
    }

    public int get技能优先级_领主房间() {
        return 技能优先级_领主房间;
    }

    public 技能状态枚举 取技能状态() {
        return 状态;
    }

    public String 取技能按键() {
        return 按键;
    }

    public 技能信息类(String 名称, String 按键, int 冷却时间, 技能类型枚举 类型) {
        this(名称, 按键, 冷却时间, 类型, 默认按键时间, 默认按键次数, 默认按键间隔时间, 默认辅助按键, 默认辅助按键次数, 默认辅助按键间隔, 默认优先级_普通房间, 默认优先级_领主房间);
    }

    public 技能信息类(String 名称, String 按键, int 冷却时间, 技能类型枚举 类型, int 技能优先级_普通房间, int 技能优先级_领主房间) {
        this(名称, 按键, 冷却时间, 类型, 默认按键时间, 默认按键次数, 默认按键间隔时间, 默认辅助按键, 默认辅助按键次数, 默认辅助按键间隔, 技能优先级_普通房间, 技能优先级_领主房间);
    }

    public 技能信息类(String 名称, String 按键, int 冷却时间, 技能类型枚举 类型, int 按键时间, int 技能优先级_普通房间, int 技能优先级_领主房间) {
        this(名称, 按键, 冷却时间, 类型, 按键时间, 默认按键次数, 默认按键间隔时间, 默认辅助按键, 默认辅助按键次数, 默认辅助按键间隔, 技能优先级_普通房间, 技能优先级_领主房间);
    }

    public 技能信息类(String 名称, String 按键, int 冷却时间, 技能类型枚举 类型, int 按键时间, int 按键次数, int 按键间隔时间, String 辅助按键, int 辅助按键次数, int 辅助按键间隔, int 技能优先级_普通房间, int 技能优先级_领主房间) {
        this.名称 = 名称;
        this.按键 = 按键;
        this.冷却时间 = 冷却时间;
        this.类型 = 类型;
        this.按键时间 = 按键时间;
        this.按键次数 = 按键次数;
        this.按键间隔时间 = 按键间隔时间;
        this.辅助按键 = 辅助按键;
        this.辅助按键次数 = 辅助按键次数;
        this.辅助按键间隔 = 辅助按键间隔;
        this.技能优先级_普通房间 = 技能优先级_普通房间;
        this.技能优先级_领主房间 = 技能优先级_领主房间;
    }

    public 技能类型枚举 取技能类型() {
        return 类型;
    }

    @Override
    public String toString() {
        return "技能信息类{" +
                "名称='" + 名称 + '\'' +
                ", 按键='" + 按键 + '\'' +
                ", 冷却时间=" + 冷却时间 +
                ", 类型=" + 类型 +
                ", 按键时间=" + 按键时间 +
                ", 按键次数=" + 按键次数 +
                ", 按键间隔时间=" + 按键间隔时间 +
                ", 辅助按键='" + 辅助按键 + '\'' +
                ", 辅助按键次数=" + 辅助按键次数 +
                ", 辅助按键间隔=" + 辅助按键间隔 +
                ", 技能优先级_普通房间=" + 技能优先级_普通房间 +
                ", 技能优先级_领主房间=" + 技能优先级_领主房间 +
                ", tick=" + tick +
                ", 状态=" + 状态 +
                '}';
    }
}
