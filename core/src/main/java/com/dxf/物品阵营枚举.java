package com.dxf;

public enum 物品阵营枚举 {
    未知(-1),
    友方(0),
    敌方(100),
    中立(200);

    private int code;

    物品阵营枚举(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static 物品阵营枚举 到物品阵营(int code) {
        物品阵营枚举[] 物品阵营数组 = 物品阵营枚举.values();
        for (物品阵营枚举 物品阵营 : 物品阵营数组) {
            if (物品阵营.getCode() == code) {
                return 物品阵营;
            }
        }
        return 未知;
    }

}
