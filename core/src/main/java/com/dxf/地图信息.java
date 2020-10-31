package com.dxf;

import com.dxf.core.GameMaster;
import com.dxf.model.坐标;
import com.dxf.util.DXF;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


@Slf4j
public class 地图信息 {

    private final DXF dxf;
    private final int 地图宽度;
    private final int 地图高度;
    private final 坐标 BOSS房间;
    private final Map<坐标, 坐标> 下一个房间;

    private final int[][] 地图通道;

    public 地图信息(DXF dxf) {
        this.dxf = dxf;
        long 地图数据 = GameMaster.readLong(dxf.getHwnd(), 基址.房间编号, 偏移.时间基址, 偏移.门型偏移);
        log.info("地图数据：{}", 地图数据);
        int BOSS房间X = 基础功能.解密(dxf.getHwnd(), 地图数据 + 偏移.BOSS房间X);
        int BOSS房间Y = 基础功能.解密(dxf.getHwnd(), 地图数据 + 偏移.BOSS房间Y);
        BOSS房间 = new 坐标(BOSS房间X, BOSS房间Y);
        int 地图编号 = 基础功能.解密(dxf.getHwnd(), 地图数据 + 偏移.地图编码);
        log.info("地图编号：{}", 地图编号);
        地图宽度 = GameMaster.readInt(dxf.getHwnd(), 地图数据 + 偏移.宽高偏移, 地图编号 * 8);
        地图高度 = GameMaster.readInt(dxf.getHwnd(), 地图数据 + 偏移.宽高偏移, 地图编号 * 8 + 4);
        地图通道 = new int[地图高度][地图宽度];
        long 通道数据 = GameMaster.readLong(dxf.getHwnd(), 地图数据 + 偏移.数组偏移, 地图编号 * 40 + 8);
        int n = 0;
        for (int i = 0; i < 地图高度; ++i) {
            for (int j = 0; j < 地图宽度; ++j) {
                地图通道[i][j] = GameMaster.readInt(dxf.getHwnd(), 通道数据 + n * 4);
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
        int 当前房间X = GameMaster.readInt(dxf.getHwnd(), 地图数据 + 偏移.当前房间X);
        int 当前房间Y = GameMaster.readInt(dxf.getHwnd(), 地图数据 + 偏移.当前房间Y);
        坐标 当前房间 = new 坐标(当前房间X, 当前房间Y);

        下一个房间 = new HashMap<>();
        List<坐标> 路径 = 生成路径(当前房间);
        if (路径.size() > 1) {
            for (int i = 1; i < 路径.size(); ++i) {
                下一个房间.put(路径.get(i-1), 路径.get(i));
            }
        }
    }

    public 坐标 取下一个房间坐标(坐标 当前房间) {
        return 下一个房间.get(当前房间);
    }

    public List<坐标> 取当前可通行邻居房间坐标(坐标 curr) {
        List<坐标> ret = new ArrayList<>();
        if ((地图通道[curr.getY()][curr.getX()] & 8) != 0) {
            // 下方有门
            ret.add(new 坐标(curr.getX(), curr.getY()+1));
        }
        if ((地图通道[curr.getY()][curr.getX()] & 4) != 0) {
            // 左边有门
            ret.add(new 坐标(curr.getX()-1, curr.getY()));
        }
        if ((地图通道[curr.getY()][curr.getX()] & 2) != 0) {
            // 上面有门
            ret.add(new 坐标(curr.getX(), curr.getY()-1));
        }
        if ((地图通道[curr.getY()][curr.getX()] & 1) != 0) {
            // 右边有门
            ret.add(new 坐标(curr.getX()+1, curr.getY()));
        }
        return ret;
    }

    public List<坐标> 生成路径(坐标 当前房间) {
        LinkedList<坐标> queue = new LinkedList<>();
        boolean[][] visited = new boolean[地图高度][地图宽度];
        Map<坐标, 坐标> pred = new HashMap<>();
        Map<坐标, Integer> dist = new HashMap<>();
        for (int i = 0; i < visited.length; ++i) {
            for (int j = 0; j < visited[0].length; ++j) {
                visited[i][j] = false;
                dist.put(new 坐标(j, i), Integer.MAX_VALUE);
                pred.put(new 坐标(j, i), null);
            }
        }
        visited[当前房间.getY()][当前房间.getX()] = true;
        dist.put(当前房间, 0);
        queue.add(当前房间);
        while (!queue.isEmpty()) {
            坐标 u = queue.remove();
            for (坐标 房间坐标 : 取当前可通行邻居房间坐标(u)) {
                if (!visited[房间坐标.getY()][房间坐标.getX()]) {
                    visited[房间坐标.getY()][房间坐标.getX()] = true;
                    dist.put(房间坐标, dist.get(房间坐标) + 1);
                    pred.put(房间坐标, u);
                    queue.add(房间坐标);
                    if (房间坐标.equals(BOSS房间)) break;
                }
            }
        }
        List<坐标> path = new ArrayList<>();
        坐标 坐标 = BOSS房间;
        path.add(坐标);
        while (pred.get(坐标) != null) {
            path.add(pred.get(坐标));
            坐标 = pred.get(坐标);
        }
        List<坐标> ret = new ArrayList<>();
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

    public 坐标 取BOSS房间() {
        return BOSS房间;
    }

    public 方向 取过图方向(坐标 curr) {
        坐标 next = 取下一个房间坐标(curr);
        if (next == null) return 方向.未知;
        if (next.getX() > curr.getX()) return 方向.右;
        if (next.getX() < curr.getX()) return 方向.左;
        if (next.getY() > curr.getY()) return 方向.下;
        if (next.getY() < curr.getY()) return 方向.上;
        return 方向.未知;
    }
}
