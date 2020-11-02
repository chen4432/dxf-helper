package com.dxf.config;

import com.dxf.component.技能信息类;
import com.dxf.model.按键枚举;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class 配置项 {

    private static Map<String, List<技能信息类>> 技能栏配置列表;

    static {
        技能栏配置列表 = new HashMap<>();

        技能栏配置列表.put("剑圣花弄影", Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("流星", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("里鬼剑术", 按键枚举.S.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("三段斩", 按键枚举.D.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.F.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.G.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.H.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("普通攻击", 按键枚举.Q.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.W.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.E.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.R.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.T.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.Y.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("破极兵刃", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("自动格挡", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("流行狂", "a|space|a", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        技能栏配置列表.put("修罗花弄影", Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("流星", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("里鬼剑术", 按键枚举.S.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("三段斩", 按键枚举.D.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.F.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.G.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.H.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("普通攻击", 按键枚举.Q.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.W.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.E.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.R.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.T.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.Y.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("破极兵刃", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("自动格挡", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("流行狂", "a|space|a", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        技能栏配置列表.put("魔神花弄影", Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("流星", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("里鬼剑术", 按键枚举.S.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("三段斩", 按键枚举.D.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.F.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.G.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.H.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("普通攻击", 按键枚举.Q.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.W.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.E.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.R.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.T.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.Y.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("破极兵刃", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("自动格挡", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("流行狂", "a|space|a", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        List<技能信息类> 技能列表 = Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("流星", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("里鬼剑术", 按键枚举.S.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("三段斩", 按键枚举.D.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.F.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.G.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.H.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("普通攻击", 按键枚举.Q.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.W.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.E.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.R.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.T.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("普通攻击", 按键枚举.Y.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("破极兵刃", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("自动格挡", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("流行狂", "a|space|a", 5, 技能信息类.技能类型枚举.BUFF)
        );

        技能栏配置列表.put("随风允诺1", 技能列表);
        技能栏配置列表.put("随风允诺2", 技能列表);
        技能栏配置列表.put("随风允诺3", 技能列表);
        技能栏配置列表.put("随风允诺4", 技能列表);
        技能栏配置列表.put("随风允诺5", 技能列表);


        技能栏配置列表.put("通用", Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 5, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("RRS", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("UDS", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("DUS", "down|up|space", 5, 技能信息类.技能类型枚举.BUFF)
        ));
    }

    public static List<技能信息类> 读取技能栏(String 角色名称) {
        return 技能栏配置列表.getOrDefault(角色名称, 技能栏配置列表.get("通用"));
    }

}
