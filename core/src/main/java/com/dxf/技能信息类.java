package com.dxf;



public class 技能信息类 {
    public enum 技能状态 {正常, 冷却中}
    public enum 技能类型 {攻击, BUFF}

    private final String 名称;
    private final String 按键;
    private final int CD;
    private int tick = 0;
    private final 技能类型 类型;
    private 技能状态 状态;

    public void update() {
        if (tick > 0) {
            tick--;
        } else {
            状态 = 技能状态.正常;
        }
    }

    public String 使用技能() {
        tick = CD;
        状态 = 技能状态.冷却中;
        return 按键;
    }

    public 技能状态 取技能状态() {
        return 状态;
    }

    public String 取技能按键() {
        return 按键;
    }

    public 技能信息类(String 名称, String 按键, int CD, 技能类型 类型) {
        this.名称 = 名称;
        this.按键 = 按键;
        this.CD = CD;
        this.状态 = 技能状态.正常;
        this.类型 = 类型;
    }

    public 技能类型 取技能类型() {
        return 类型;
    }

    @Override
    public String toString() {
        return "技能信息{" +
                "名称='" + 名称 + '\'' +
                ", 按键='" + 按键 + '\'' +
                ", CD=" + CD +
                ", tick=" + tick +
                ", 状态=" + 状态 +
                ", 类型=" + 类型 +
                '}';
    }

}
