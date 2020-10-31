package com.dxf;

public enum 物品类型 {
    未知(-1),
    营火(4),
    建筑(33),
    人形(273),
    地面物品(289),
    怪物(529),
    门(4129),
    技能(61400);

    private int code;

    物品类型(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static 物品类型 到物品类型(int code) {
        物品类型[] 物品类型数组 = 物品类型.values();
        for (物品类型 物品类型 : 物品类型数组) {
            if (物品类型.getCode() == code) {
                return 物品类型;
            }
        }
        return 未知;
    }
}
