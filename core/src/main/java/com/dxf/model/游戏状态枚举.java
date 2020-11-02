package com.dxf.model;

public enum 游戏状态枚举 {
    未知(-1),
    选择角色(0),
    城镇(1),
    选择副本(2),
    在副本中(3),
    正在进入频道(5);

    private final int code;

    游戏状态枚举(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static 游戏状态枚举 到游戏状态(int code) {
        游戏状态枚举[] states = 游戏状态枚举.values();
        for (游戏状态枚举 state : states) {
            if (state.getCode() == code) {
                return state;
            }
        }
        return 未知;
    }

    public static void main(String[] args) {
        System.out.println(游戏状态枚举.在副本中);
        游戏状态枚举 状态 = 游戏状态枚举.到游戏状态(3);
        System.out.println(状态);
        System.out.println(游戏状态枚举.到游戏状态(4));
    }
}
