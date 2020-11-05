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
    private static Map<String, Pair<Double, Double>> 移动速度列表;

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
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 2, 技能信息类.技能类型枚举.攻击),

                new 技能信息类("波动爆发", 按键枚举.A.getStrCode(), 8, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("地裂波动剑", 按键枚举.S.getStrCode(), 3, 技能信息类.技能类型枚举.攻击, 0, 10, 10),
                new 技能信息类("三段斩", 按键枚举.D.getStrCode(), 6, 技能信息类.技能类型枚举.攻击, 0, 100, 100),
                new 技能信息类("邪光斩", 按键枚举.F.getStrCode(), 10, 技能信息类.技能类型枚举.攻击, 1000, 20, 20),
                new 技能信息类("无双波", 按键枚举.G.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 100, 100),
                new 技能信息类("天雷波动剑", 按键枚举.H.getStrCode(), 38, 技能信息类.技能类型枚举.攻击, 0, 100, 30),

                new 技能信息类("冰刃波动剑", 按键枚举.Q.getStrCode(), 7, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("爆炎波动剑", 按键枚举.W.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 5, 5),
                new 技能信息类("极冰列波剑", 按键枚举.E.getStrCode(), 14, 技能信息类.技能类型枚举.攻击, 0, 50, 4),
                new 技能信息类("极炎列波剑", 按键枚举.R.getStrCode(), 34, 技能信息类.技能类型枚举.攻击, 0, 50, 3),
                new 技能信息类("波动慧眼", 按键枚举.T.getStrCode(), 57, 技能信息类.技能类型枚举.攻击, 0, 100, 2),
                new 技能信息类("雷神降世", 按键枚举.Y.getStrCode(), 180, 技能信息类.技能类型枚举.攻击, 0, 101, 1),

                new 技能信息类("杀意波动", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("波动刻印", "up|up|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        技能栏配置列表.put("魔神花弄影", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 2, 技能信息类.技能类型枚举.攻击, 1000, 1, 10),

                new 技能信息类("波动爆发", 按键枚举.A.getStrCode(), 8, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("地裂波动剑", 按键枚举.S.getStrCode(), 3, 技能信息类.技能类型枚举.攻击, 0, 10, 10),
                new 技能信息类("三段斩", 按键枚举.D.getStrCode(), 6, 技能信息类.技能类型枚举.攻击, 0, 100, 100),
                new 技能信息类("邪光斩", 按键枚举.F.getStrCode(), 10, 技能信息类.技能类型枚举.攻击, 0, 20, 20),
                new 技能信息类("无双波", 按键枚举.G.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 100, 100),
                new 技能信息类("天雷波动剑", 按键枚举.H.getStrCode(), 38, 技能信息类.技能类型枚举.攻击, 0, 100, 30),

                new 技能信息类("冰刃波动剑", 按键枚举.Q.getStrCode(), 7, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("爆炎波动剑", 按键枚举.W.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 5, 5),
                new 技能信息类("极冰列波剑", 按键枚举.E.getStrCode(), 14, 技能信息类.技能类型枚举.攻击, 0, 50, 4),
                new 技能信息类("极炎列波剑", 按键枚举.R.getStrCode(), 34, 技能信息类.技能类型枚举.攻击, 0, 50, 3),
                new 技能信息类("波动慧眼", 按键枚举.T.getStrCode(), 57, 技能信息类.技能类型枚举.攻击, 0, 100, 2),
                new 技能信息类("雷神降世", 按键枚举.Y.getStrCode(), 180, 技能信息类.技能类型枚举.攻击, 0, 101, 1),

                new 技能信息类("杀意波动", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("波动刻印", "up|up|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        技能栏配置列表.put("CodeTheWorld", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 3, 技能信息类.技能类型枚举.攻击, 1000, 1, 10),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 6, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 8, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 17, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 19, 技能信息类.技能类型枚举.攻击, 0, 1, 10),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 42, 技能信息类.技能类型枚举.攻击, 0, 10, 6),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 42, 技能信息类.技能类型枚举.攻击, 0, 10, 5),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 48, 技能信息类.技能类型枚举.攻击, 0, 10, 4),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 27, 技能信息类.技能类型枚举.攻击, 0, 10, 3),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 190, 技能信息类.技能类型枚举.攻击, 0, 110, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 153, 技能信息类.技能类型枚举.攻击, 0, 110, 1),

                new 技能信息类("杀意波动", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF)
                //new 技能信息类("波动刻印", "up|up|space", 5, 技能信息类.技能类型枚举.BUFF),
                //new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF),
                //new 技能信息类("天雷", "t", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        技能栏配置列表.put("jay俊", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 3, 技能信息类.技能类型枚举.攻击, 1000, 1, 10),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 3, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 7, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 4, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 12, 技能信息类.技能类型枚举.攻击, 0, 1, 10),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 30, 技能信息类.技能类型枚举.BUFF, 0, 1, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 78, 技能信息类.技能类型枚举.BUFF, 0, 1, 10),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 35, 技能信息类.技能类型枚举.攻击, 0, 10, 6),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 45, 技能信息类.技能类型枚举.攻击, 0, 10, 5),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 10, 4),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 45, 技能信息类.技能类型枚举.攻击, 0, 10, 3),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 145, 技能信息类.技能类型枚举.攻击, 0, 110, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 180, 技能信息类.技能类型枚举.攻击, 0, 110, 1),

                new 技能信息类("杀意波动", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("波动刻印", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF)
                //new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF),
                //new 技能信息类("天雷", "t", 5, 技能信息类.技能类型枚举.BUFF)

        ));

        List<技能信息类> 修罗技能列表 = Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 3, 技能信息类.技能类型枚举.攻击, 1000, 10, 10),

                new 技能信息类("波动爆发", 按键枚举.A.getStrCode(), 8, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("地裂波动剑", 按键枚举.S.getStrCode(), 6, 技能信息类.技能类型枚举.攻击, 0, 10, 10),
                new 技能信息类("鬼斩", 按键枚举.D.getStrCode(), 6, 技能信息类.技能类型枚举.攻击, 0, 100, 100),
                new 技能信息类("邪光斩", 按键枚举.F.getStrCode(), 10, 技能信息类.技能类型枚举.攻击, 1000, 10, 10),
                new 技能信息类("列波斩", 按键枚举.G.getStrCode(), 8, 技能信息类.技能类型枚举.攻击, 0, 10, 10),
                new 技能信息类("鬼印珠", 按键枚举.H.getStrCode(), 6, 技能信息类.技能类型枚举.攻击, 0, 10, 10),

                new 技能信息类("冰刃波动剑", 按键枚举.Q.getStrCode(), 7, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("爆炎波动剑", 按键枚举.W.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 10, 4),
                new 技能信息类("极冰列波剑", 按键枚举.E.getStrCode(), 20, 技能信息类.技能类型枚举.攻击, 0, 110, 3),
                new 技能信息类("极炎列波剑", 按键枚举.R.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 110, 2),
                new 技能信息类("波动慧眼", 按键枚举.T.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 100, 2),
                new 技能信息类("雷神降世", 按键枚举.Y.getStrCode(), 180, 技能信息类.技能类型枚举.攻击, 0, 110, 1),

                new 技能信息类("杀意波动", "right|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("波动刻印", "up|up|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("天雷", "t", 5, 技能信息类.技能类型枚举.BUFF)
        );

        技能栏配置列表.put("随风允诺1", 修罗技能列表);
        技能栏配置列表.put("随风允诺2", 修罗技能列表);
        技能栏配置列表.put("随风允诺3", 修罗技能列表);
        技能栏配置列表.put("随风允诺4", 修罗技能列表);
        技能栏配置列表.put("随风允诺5", 修罗技能列表);


        List<技能信息类> 鬼泣技能列表 = Arrays.asList(
                new 技能信息类("普通攻击", 按键枚举.X.getStrCode(), 3, 技能信息类.技能类型枚举.攻击, 1000, 10, 10),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 8, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 4, 技能信息类.技能类型枚举.攻击, 0, 10, 10),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 6, 技能信息类.技能类型枚举.攻击, 0, 100, 100),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 8, 技能信息类.技能类型枚举.攻击, 0, 10, 10),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 18, 技能信息类.技能类型枚举.攻击, 0, 10, 10),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 20, 技能信息类.技能类型枚举.攻击, 0, 10, 10),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 1, 5),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 20, 技能信息类.技能类型枚举.攻击, 0, 10, 4),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 110, 3),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 110, 2),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 180, 技能信息类.技能类型枚举.攻击, 0, 100, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 290, 技能信息类.技能类型枚举.攻击, 0, 110, 1),

                new 技能信息类("UDS", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("远古记忆", "space", 5, 技能信息类.技能类型枚举.BUFF)
        );
        技能栏配置列表.put("大暗の黑天", 鬼泣技能列表);




        技能栏配置列表.put("通用", Arrays.asList(
                new 技能信息类("X", 按键枚举.X.getStrCode(), 2, 技能信息类.技能类型枚举.攻击, 1000, 10, 20),

                new 技能信息类("A", 按键枚举.A.getStrCode(), 5, 技能信息类.技能类型枚举.攻击, 0, 10, 20),
                new 技能信息类("S", 按键枚举.S.getStrCode(), 5, 技能信息类.技能类型枚举.攻击, 0, 10, 20),
                new 技能信息类("D", 按键枚举.D.getStrCode(), 5, 技能信息类.技能类型枚举.攻击, 0, 10, 20),
                new 技能信息类("F", 按键枚举.F.getStrCode(), 10, 技能信息类.技能类型枚举.攻击, 0, 10, 20),
                new 技能信息类("G", 按键枚举.G.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 10, 20),
                new 技能信息类("H", 按键枚举.H.getStrCode(), 15, 技能信息类.技能类型枚举.攻击, 0, 10, 20),

                new 技能信息类("Q", 按键枚举.Q.getStrCode(), 20, 技能信息类.技能类型枚举.攻击, 0, 20, 6),
                new 技能信息类("W", 按键枚举.W.getStrCode(), 20, 技能信息类.技能类型枚举.攻击, 0, 20, 5),
                new 技能信息类("E", 按键枚举.E.getStrCode(), 30, 技能信息类.技能类型枚举.攻击, 0, 20, 4),
                new 技能信息类("R", 按键枚举.R.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 20, 3),
                new 技能信息类("T", 按键枚举.T.getStrCode(), 40, 技能信息类.技能类型枚举.攻击, 0, 20, 2),
                new 技能信息类("Y", 按键枚举.Y.getStrCode(), 180, 技能信息类.技能类型枚举.攻击, 0, 100, 1),

                new 技能信息类("S", "space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("RRSR", "right|right|space|right", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("UUS", "up|up|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("UDS", "up|down|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("LDRS", "left|down|right|space", 5, 技能信息类.技能类型枚举.BUFF),
                new 技能信息类("URS", "up|right|space", 5, 技能信息类.技能类型枚举.BUFF)


        ));

        移动速度列表 = new HashMap<>();
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
    }

    public static List<技能信息类> 读取技能栏(String 角色名称) {
        return 技能栏配置列表.getOrDefault(角色名称, 技能栏配置列表.get("通用"));
    }

    public static Pair<Double, Double> 读取移动速度(String 角色名称) {
        return 移动速度列表.getOrDefault(角色名称, new Pair<>(0.4, 0.17));
    }

}
