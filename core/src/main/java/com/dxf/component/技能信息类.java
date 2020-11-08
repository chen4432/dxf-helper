package com.dxf.component;


import com.dxf.core.TP;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class 技能信息类 {

    public static final int 技能类型_攻击  = 0;
    public static final int 技能类型_状态  = 1;

    public static final int 技能状态_正常  = 0;
    public static final int 技能状态_冷却  = 1;

    private final String        名称;
    private final String        按键;
    private final int           冷却时间;
    private final int           持续时间;
    private final int           类型;
    private final int           按键时间;               // 毫秒
    private final int           技能优先级_普通房间;
    private final int           技能优先级_领主房间;

    private int                 tick;
    private int                 状态 = 技能状态_正常;


    public void update() {
        if (tick > 0) {
            tick--;
        } else {
            状态 = 技能状态_正常;
        }
        System.out.println("按键： " + 按键 + ", 状态： " + 状态);
    }

    public String 使用技能() {
        tick = 冷却时间;
        状态 = 技能状态_冷却;
        return 按键;
    }

    public boolean 释放技能() throws InterruptedException {
        if (状态 == 技能状态_冷却) return false;
        switch (类型) {
            case 技能类型_状态:
                List<String> 按键序列 = Arrays.stream(按键.split("\\|")).collect(Collectors.toList());
                log.info("BUFF技能名称：{}, 按键序列：{}", 名称, 按键序列);
                TP.keyPressCharList(按键序列, 30);
                if (持续时间 > 0) {
                    基础功能类.延时(持续时间);
                }
                break;
            case 技能类型_攻击:
                if (按键时间 > 0) {
                    log.info("攻击技能名称: {}, 按键时间: {}", 名称, 按键时间);
                    TP.keyDownChar(按键);
                    基础功能类.延时(按键时间);
                    TP.keyUpChar(按键);
                } else {
                    log.info("攻击技能名称: {}, 按键时间: {}", 名称, 按键时间);
                    TP.keyPressChar(按键);
                }
                if (持续时间 > 0) {
                    基础功能类.延时(持续时间);
                }
                tick = 冷却时间;
                状态 = 技能状态_冷却;
                break;
        }
        return true;
    }

    public int 取技能优先级_普通房间() {
        return 技能优先级_普通房间;
    }

    public int 取技能优先级_领主房间() {
        return 技能优先级_领主房间;
    }

    public String 取技能按键() {
        return 按键;
    }

    public boolean 是否冷却中() {
        return 状态 == 技能状态_冷却;
    }

    public 技能信息类(String 名称, String 按键, int 冷却时间, int 持续时间, int 类型, int 按键时间, int 技能优先级_普通房间, int 技能优先级_领主房间) {
        this.名称 = 名称;
        this.按键 = 按键;
        this.冷却时间 = 冷却时间;
        this.持续时间 = 持续时间;
        this.类型 = 类型;
        this.按键时间 = 按键时间;
        this.技能优先级_普通房间 = 技能优先级_普通房间;
        this.技能优先级_领主房间 = 技能优先级_领主房间;
    }

    public int 取技能类型() {
        return 类型;
    }

    public boolean 是否攻击类型() {
        return 类型 == 技能类型_攻击;
    }

    public boolean 是否状态技能() {
        return 类型 == 技能类型_状态;
    }

    @Override
    public String toString() {
        return "技能信息类{" +
                "名称='" + 名称 + '\'' +
                ", 按键='" + 按键 + '\'' +
                ", 冷却时间=" + 冷却时间 +
                ", 类型=" + 类型 +
                ", 按键时间=" + 按键时间 +
                ", 技能优先级_普通房间=" + 技能优先级_普通房间 +
                ", 技能优先级_领主房间=" + 技能优先级_领主房间 +
                ", tick=" + tick +
                ", 状态=" + 状态 +
                '}';
    }
}
