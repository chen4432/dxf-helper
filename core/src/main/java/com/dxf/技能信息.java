package com.dxf;



public class 技能信息 {

    public enum 技能状态 {正常, 冷却中}
    public enum 技能类型 {攻击, BUFF}
    private final String KEY;
    private final int CD;
    private int tick = 0;
    private 技能状态 state;
    private 技能类型 type;

    public void update() {
        if (tick > 0) {
            tick--;
        } else {
            state = 技能状态.正常;
        }
    }

    public String 使用技能() {
        tick = CD;
        state = 技能状态.冷却中;
        return KEY;
    }

    public 技能状态 取技能状态() {
        return state;
    }

    public String 取技能按键() {
        return KEY;
    }

    public 技能信息(String KEY, int CD, 技能类型 type) {
        this.KEY = KEY;
        this.CD = CD;
        this.state = 技能状态.正常;
        this.type = type;
    }

    public 技能类型 取技能类型() {
        return type;
    }

    @Override
    public String toString() {
        return "技能信息{" +
                "按键='" + KEY + '\'' +
                ", CD=" + CD +
                ", tick=" + tick +
                ", 状态=" + state +
                ", 类型=" + type +
                '}';
    }
}
