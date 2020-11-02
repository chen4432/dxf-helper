package com.dxf;

import com.dxf.core.GameMaster;
import com.dxf.model.坐标类;
import com.dxf.model.方向枚举;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class 地图信息类 {

    private final int 窗口句柄;
    private final long 地图数据;
    private final int 地图宽度;
    private final int 地图高度;
    private final 坐标类 BOSS房间;
    private final Map<坐标类, 坐标类> 下一个房间;

    private final int[][] 地图通道;

    public 地图信息类(int 窗口句柄) {
        this.窗口句柄 = 窗口句柄;
        地图数据 = GameMaster.readLong(窗口句柄, 基址类.房间编号, 偏移类.时间基址, 偏移类.门型偏移);
        log.info("地图数据：{}", 地图数据);
        int BOSS房间X = 基础功能类.解密读取(窗口句柄, 地图数据 + 偏移类.BOSS房间X);
        int BOSS房间Y = 基础功能类.解密读取(窗口句柄, 地图数据 + 偏移类.BOSS房间Y);
        BOSS房间 = new 坐标类(BOSS房间X, BOSS房间Y);
        int 地图编号 = 基础功能类.解密读取(窗口句柄, 地图数据 + 偏移类.地图编码);
        log.info("地图编号：{}", 地图编号); // 0\1\2\3
        地图宽度 = GameMaster.readInt(窗口句柄, 地图数据 + 偏移类.宽高偏移, 地图编号 * 8);
        地图高度 = GameMaster.readInt(窗口句柄, 地图数据 + 偏移类.宽高偏移, 地图编号 * 8 + 4);
        地图通道 = new int[地图高度][地图宽度];
        long 通道数据 = GameMaster.readLong(窗口句柄, 地图数据 + 偏移类.数组偏移, 地图编号 * 40 + 8);
        int n = 0;
        for (int i = 0; i < 地图高度; ++i) {
            for (int j = 0; j < 地图宽度; ++j) {
                地图通道[i][j] = GameMaster.readInt(窗口句柄, 通道数据 + n * 4);
                ++n;
            }
        }
        /*
        System.out.println("BOSS房间： " + BOSS房间);
        System.out.printf("地图宽度：%d，地图高度：%d\n", 地图宽度, 地图高度);
        System.out.println("地图通道：");
        for (int i = 0; i < 地图通道.length; ++i) {
            for (int j = 0; j < 地图通道[0].length; ++j) {
                System.out.printf("%02d\t", 地图通道[i][j]);
            }
            System.out.println();
        }
        */
        int 当前房间X = GameMaster.readInt(窗口句柄, 地图数据 + 偏移类.当前房间X);
        int 当前房间Y = GameMaster.readInt(窗口句柄, 地图数据 + 偏移类.当前房间Y);
        坐标类 当前房间 = new 坐标类(当前房间X, 当前房间Y);

        下一个房间 = new HashMap<>();
        List<坐标类> 路径 = 生成通关路径(当前房间);
        if (路径.size() > 1) {
            for (int i = 1; i < 路径.size(); ++i) {
                下一个房间.put(路径.get(i-1), 路径.get(i));
            }
        }
    }

    public 坐标类 取下一个房间坐标(坐标类 当前房间) {
        return 下一个房间.get(当前房间);
    }

    public List<坐标类> 取当前可通行邻居房间坐标(坐标类 curr) {
        List<坐标类> ret = new ArrayList<>();
        if ((地图通道[curr.Y()][curr.X()] & 8) != 0) {
            // 下方有门
            ret.add(new 坐标类(curr.X(), curr.Y()+1));
        }
        if ((地图通道[curr.Y()][curr.X()] & 4) != 0) {
            // 左边有门
            ret.add(new 坐标类(curr.X()-1, curr.Y()));
        }
        if ((地图通道[curr.Y()][curr.X()] & 2) != 0) {
            // 上面有门
            ret.add(new 坐标类(curr.X(), curr.Y()-1));
        }
        if ((地图通道[curr.Y()][curr.X()] & 1) != 0) {
            // 右边有门
            ret.add(new 坐标类(curr.X()+1, curr.Y()));
        }
        return ret;
    }

    public List<坐标类> 生成通关路径(坐标类 当前房间) {
        LinkedList<坐标类> queue = new LinkedList<>();
        boolean[][] visited = new boolean[地图高度][地图宽度];
        Map<坐标类, 坐标类> pred = new HashMap<>();
        Map<坐标类, Integer> dist = new HashMap<>();
        for (int i = 0; i < visited.length; ++i) {
            for (int j = 0; j < visited[0].length; ++j) {
                visited[i][j] = false;
                dist.put(new 坐标类(j, i), Integer.MAX_VALUE);
                pred.put(new 坐标类(j, i), null);
            }
        }
        visited[当前房间.Y()][当前房间.X()] = true;
        dist.put(当前房间, 0);
        queue.add(当前房间);
        while (!queue.isEmpty()) {
            坐标类 u = queue.remove();
            for (坐标类 房间坐标 : 取当前可通行邻居房间坐标(u)) {
                if (!visited[房间坐标.Y()][房间坐标.X()]) {
                    visited[房间坐标.Y()][房间坐标.X()] = true;
                    dist.put(房间坐标, dist.get(房间坐标) + 1);
                    pred.put(房间坐标, u);
                    queue.add(房间坐标);
                    if (房间坐标.equals(BOSS房间)) break;
                }
            }
        }
        List<坐标类> path = new ArrayList<>();
        坐标类 坐标类 = BOSS房间;
        path.add(坐标类);
        while (pred.get(坐标类) != null) {
            path.add(pred.get(坐标类));
            坐标类 = pred.get(坐标类);
        }
        List<坐标类> ret = new ArrayList<>();
        for (int i = path.size()-1; i >= 0; --i) {
            ret.add(path.get(i));
        }
        return ret;
    }

    public int 取地图宽度() {
        return 地图宽度;
    }

    public int 取地图高度() {
        return 地图高度;
    }

    public 坐标类 取BOSS房间() {
        return BOSS房间;
    }

    public 方向枚举 取过图方向(坐标类 curr) {
        坐标类 next = 取下一个房间坐标(curr);
        if (next == null) return 方向枚举.未知;
        if (next.X() > curr.X()) return 方向枚举.右;
        if (next.X() < curr.X()) return 方向枚举.左;
        if (next.Y() > curr.Y()) return 方向枚举.下;
        if (next.Y() < curr.Y()) return 方向枚举.上;
        return 方向枚举.未知;
    }
}
