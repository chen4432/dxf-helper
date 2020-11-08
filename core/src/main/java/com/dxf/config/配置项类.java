package com.dxf.config;

import com.dxf.component.技能信息类;
import com.dxf.model.按键枚举;
import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class 配置项类 {

    private static final Map<String, List<技能信息类>> 技能栏配置列表;
    private static final Map<String, Pair<Double, Double>> 移动速度列表;

    static {
        技能栏配置列表 = new HashMap<>();
        移动速度列表 = new HashMap<>();

        技能栏配置列表.put("剑圣花弄影", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 1, 500, 0, 0, 3, 3),

                new 技能信息类("S", 按键枚举.S.getStrCode(), 1, 500,0, 500, 2, 2),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 12, 500,0, 0, 2, 2),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 40, 1500,0, 0, 3, 2),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 16, 500,0, 0, 2, 2),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 28, 2000,0, 0, 3, 2),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 28, 5000,0, 0, 3, 1),

                new 技能信息类("破极兵刃", "right|right|space", 20, 500,1, 0, 0, 0),
                new 技能信息类("自动格挡", "up|down|space", 20, 500,1, 0, 0, 0),
                new 技能信息类("流行狂", "a|space|a", 20, 500,1, 0, 0, 0)
        ));

        移动速度列表.put("剑圣花弄影", new Pair<>(0.80, 0.25));

        技能栏配置列表.put("南山、敬老院", Arrays.asList(
                //new 技能信息类("X", 按键枚举.X.getStrCode(), 2, 技能信息类.技能类型枚举.攻击, 1000, 10, 20),

                //new 技能信息类("A", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击, 0, 10, 20),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 1, 0,0, 1000, 11, 21),
                //new 技能信息类("D", 按键枚举.D.getStrCode(), 5, 技能信息类.技能类型枚举.攻击, 0, 10, 20),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 9, 0,0, 0, 10, 20),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 13, 0,0, 0, 10, 20),
                //new 技能信息类("H", 按键枚举.H.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 10, 20),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 21, 0,0, 0, 20, 6),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 17, 0,0, 0, 20, 5),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 37, 0,0, 0, 20, 4),
                //new 技能信息类("R", 按键枚举.R.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 20, 3),
                //new 技能信息类("T", 按键枚举.T.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 20, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 120, 0,0, 0, 100, 1),

                new 技能信息类("破极兵刃", "right|right|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("自动格挡", "up|down|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("流行狂", "a|space|a", 20, 0,1, 0, 0, 0)

        ));

        技能栏配置列表.put("修罗花弄影", Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 1, 500,0, 888, 3, 4),

                new 技能信息类("波动爆发", 按键枚举.A.getStrCode(), 8, 500,0, 0, 1, 3),
                new 技能信息类("地裂波动剑", 按键枚举.S.getStrCode(), 3, 1000,0, 0, 2, 3),
                new 技能信息类("邪光斩", 按键枚举.F.getStrCode(), 10, 1000,0, 500, 2, 3),

                new 技能信息类("冰刃波动剑", 按键枚举.Q.getStrCode(), 7, 1000,0, 0, 1, 2),
                new 技能信息类("爆炎波动剑", 按键枚举.W.getStrCode(), 15, 1000,0, 0, 3, 2),
                new 技能信息类("极冰列波剑", 按键枚举.E.getStrCode(), 14, 1000,0, 0, 3, 2),
                new 技能信息类("雷神降世", 按键枚举.Y.getStrCode(), 180, 5000,0, 0, 4, 1),

                new 技能信息类("杀意波动", "right|right|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("波动刻印", "up|up|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("远古记忆", "space", 20, 0,1, 0, 0, 0)
        ));

        技能栏配置列表.put("魔神花弄影", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 1, 500,0, 1000, 3, 3),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 20, 1000,0, 0, 2, 3),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 13, 1500,0, 0, 1, 3),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 4, 1300,0, 0, 1, 3),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 20, 1800,0, 0, 2, 2),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 36, 1800,0, 0, 2, 2),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 42, 1500,0, 0, 2, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 180, 3000,0, 0, 4, 1),

                new 技能信息类("暴走", "right|right|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("嗜血", "up|down|space", 20, 0,1, 0, 0, 0)

        ));

        技能栏配置列表.put("CodeTheWorld", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 3, 0,0, 1000, 1, 10),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 5, 0,0, 0, 1, 10),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 6, 0,0, 0, 1, 10),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 8, 0,0, 0, 1, 10),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 15, 0,0, 0, 1, 10),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 17, 0,0, 0, 1, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 19, 0,0, 0, 1, 10),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 42, 0,0, 0, 10, 6),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 42, 0,0, 0, 10, 5),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 48, 0,0, 0, 10, 4),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 27, 0,0, 0, 10, 3),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 190, 0,0, 0, 110, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 153, 0,0, 0, 110, 1),

                new 技能信息类("杀意波动", "right|right|space", 20, 0,1, 0, 0, 0)
                //new 技能信息类("波动刻印", "up|up|space", 5, 技能信息类.技能类型枚举.BUFF),
                //new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF),
                //new 技能信息类("天雷", "t", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        技能栏配置列表.put("jay俊", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 3, 0,0, 1000, 1, 10),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 3, 0,0, 0, 1, 10),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 7, 0,0, 0, 1, 10),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 4, 0,0, 0, 1, 10),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 12, 0,0, 0, 1, 10),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 30, 0,0, 0, 1, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 78, 0,0, 0, 1, 10),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 35, 0,0, 0, 10, 6),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 45, 0,0, 0, 10, 5),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 40, 0,0, 0, 10, 4),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 45, 0,0, 0, 10, 3),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 145, 0,0, 0, 110, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 180, 0,0, 0, 110, 1),

                new 技能信息类("杀意波动", "right|right|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("波动刻印", "up|down|space", 20, 0,1, 0, 0, 0)
                //new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF),
                //new 技能信息类("天雷", "t", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        技能栏配置列表.put("拳打成电", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 1, 0,0, 1000, 2, 3),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 2, 0,0, 0, 2, 3),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 7, 0,0, 0, 1, 3),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 7, 0,0, 0, 1, 3),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 14, 0,0, 0, 1, 3),
                //new 技能信息类("G", 按键枚举.G.getStrCode(), 30, 0, 0, 1, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 18, 0,0, 0, 1, 2),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 18, 0,0, 0, 2, 2),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 27, 0,0, 0, 2, 2),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 36, 0,0, 0, 4, 2),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 36, 0,0, 0, 4, 2),
                //new 技能信息类("T", 按键枚举.T.getStrCode(), 145, 0, 0, 110, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 261, 0,0, 0, 10, 1),

                new 技能信息类("强拳", "right|right|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("霸体", "up|up|space", 20, 0,1, 0, 0, 0)

        ));

        技能栏配置列表.put("ablevr", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 1, 0,0, 1000, 2, 3),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 3, 0,0, 0, 2, 3),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 6, 0,0, 0, 1, 3),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 6, 0,0, 0, 1, 3),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 15, 0,0, 3000, 1, 3),
                //new 技能信息类("G", 按键枚举.G.getStrCode(), 30, 0, 0, 1, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 20, 0,0, 0, 1, 2),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 24, 0,0, 0, 2, 2),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 30, 0,0, 0, 2, 2),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 45, 0,0, 5000, 4, 2),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 50, 0,0, 0, 4, 2),
                //new 技能信息类("T", 按键枚举.T.getStrCode(), 145, 0, 0, 110, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 290, 0,0, 0, 10, 1),
                new 技能信息类("涂毒", "right|right|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("挑衅", "down|down|space", 20, 0,1, 0, 0, 0)

        ));

        List<技能信息类> 修罗技能列表 = Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 0, 0,0, 1000, 3, 3),

                new 技能信息类("波动爆发", 按键枚举.A.getStrCode(), 8, 0,0, 0, 1, 3),
                new 技能信息类("地裂波动剑", 按键枚举.S.getStrCode(), 3, 0,0, 0, 3, 3),
                //new 技能信息类("鬼斩", 按键枚举.D.getStrCode(), 6, 0, 0, 100, 100),
                new 技能信息类("邪光斩", 按键枚举.F.getStrCode(), 10, 0,0, 888, 2, 3),
                new 技能信息类("列波斩", 按键枚举.G.getStrCode(), 8, 0,0, 0, 2, 3),
                new 技能信息类("邪光波动阵", 按键枚举.H.getStrCode(), 20, 0,0, 0, 1, 2),

                new 技能信息类("冰刃波动剑", 按键枚举.Q.getStrCode(), 7, 0,0, 0, 1, 2),
                new 技能信息类("爆炎波动剑", 按键枚举.W.getStrCode(), 15, 0,0, 0, 2, 2),
                new 技能信息类("极冰列波剑", 按键枚举.E.getStrCode(), 20, 0,0, 0, 4, 2),
                //new 技能信息类("极炎列波剑", 按键枚举.R.getStrCode(), 40, 0, 0, 110, 2),
                new 技能信息类("天雷降魔杵", 按键枚举.T.getStrCode(), 45, 0,0, 0, 0, 0),
                new 技能信息类("雷神降世", 按键枚举.Y.getStrCode(), 180, 0,0, 0, 10, 1),

                new 技能信息类("远古记忆", "space", 40, 0,1, 0, 0, 0),
                new 技能信息类("杀意波动", "right|right|space", 3600, 0,1, 0, 0, 0),
                new 技能信息类("波动刻印", "up|up|space", 3600, 0,1, 0, 0, 0)

        );

        技能栏配置列表.put("随风允诺1", 修罗技能列表);
        技能栏配置列表.put("随风允诺2", 修罗技能列表);
        技能栏配置列表.put("随风允诺3", 修罗技能列表);
        技能栏配置列表.put("随风允诺4", 修罗技能列表);
        技能栏配置列表.put("随风允诺5", 修罗技能列表);


        List<技能信息类> 鬼泣技能列表 = Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 3, 0,0, 1000, 10, 10),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 8, 0,0, 0, 1, 5),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 4, 0,0, 0, 10, 10),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 6, 0,0, 0, 100, 100),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 8, 0,0, 0, 10, 10),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 18, 0,0, 0, 10, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 20, 0,0, 0, 10, 10),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 15, 0,0, 0, 1, 5),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 20, 0,0, 0, 10, 4),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 40, 0,0, 0, 110, 3),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 40, 0,0, 0, 110, 2),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 180, 0,0, 0, 100, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 290, 0,0, 0, 110, 1),

                new 技能信息类("UDS", "up|down|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("远古记忆", "space", 20, 0,1, 0, 0, 0)
        );
        技能栏配置列表.put("大暗の黑天", 鬼泣技能列表);

        技能栏配置列表.put("通用", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 2, 0,0, 1000, 10, 20),
                new 技能信息类("A", 按键枚举.A.getStrCode(), 5, 0,0, 0, 10, 20),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 5, 0,0, 0, 10, 20),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 5, 0,0, 0, 10, 20),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 10, 0,0, 0, 10, 20),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 15, 0,0, 0, 10, 20),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 15, 0,0, 0, 10, 20),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 20, 0,0, 0, 20, 6),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 20, 0,0, 0, 20, 5),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 30, 0,0, 0, 20, 4),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 40, 0,0, 0, 20, 3),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 40, 0,0, 0, 20, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 180, 0,0, 0, 100, 1),

                new 技能信息类("远古记忆", "space", 20, 0,1, 0, 0, 0),
                new 技能信息类("RRSR", "right|right|space|right", 0,20, 1, 0, 0, 0),
                new 技能信息类("UUS", "up|up|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("UDS", "up|down|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("LDRS", "left|down|right|space", 20, 0,1, 0, 0, 0),
                new 技能信息类("URS", "up|right|space", 20, 0,1, 0, 0, 0)
        ));


        移动速度列表.put("修罗花弄影", new Pair<>(0.66, 0.24));
        移动速度列表.put("魔神花弄影", new Pair<>(0.75, 0.26));
        移动速度列表.put("随风允诺5", new Pair<>(0.44, 0.21));
        移动速度列表.put("随风允诺4", new Pair<>(0.44, 0.21));
        移动速度列表.put("随风允诺3", new Pair<>(0.44, 0.21));
        移动速度列表.put("随风允诺2", new Pair<>(0.44, 0.21));
        移动速度列表.put("随风允诺1", new Pair<>(0.44, 0.21));

        移动速度列表.put("大暗の黑天", new Pair<>(0.44, 0.23));
        移动速度列表.put("CodeTheWorld", new Pair<>(0.54, 0.18));
        移动速度列表.put("jay俊", new Pair<>(0.52, 0.18));
        移动速度列表.put("MoreEffect", new Pair<>(0.49, 0.18));
        移动速度列表.put("南山、敬老院", new Pair<>(0.46, 0.17));
        移动速度列表.put("拳打成电", new Pair<>(0.56, 0.25));
        移动速度列表.put("ablevr", new Pair<>(0.48, 0.20));

    }

    public static List<技能信息类> 读取技能栏(String 角色名称) {
        return 技能栏配置列表.getOrDefault(角色名称, 技能栏配置列表.get("通用"));
    }

    public static Pair<Double, Double> 读取移动速度(String 角色名称) {
        return 移动速度列表.getOrDefault(角色名称, new Pair<>(0.4, 0.17));
    }

}
