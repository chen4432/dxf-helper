package com.dxf.component;

import com.dxf.core.TP;
import com.dxf.model.坐标类;
import com.dxf.model.方向枚举;
import com.dxf.model.物品类型枚举;
import com.dxf.model.物品阵营枚举;
import com.dxf.constant.偏移类;
import com.dxf.constant.基址类;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;


@Slf4j
public class 房间信息类 {

    private final int 窗口句柄;
    private final long 房间数据;

    private final CopyOnWriteArrayList<坐标类> 门列表;
    private final CopyOnWriteArrayList<坐标类> 怪物列表;
    private final CopyOnWriteArrayList<坐标类> 材料列表;

    public 房间信息类(int 窗口句柄) {
        this.窗口句柄 = 窗口句柄;
        门列表 = new CopyOnWriteArrayList<>();
        怪物列表 = new CopyOnWriteArrayList<>();
        材料列表 = new CopyOnWriteArrayList<>();
        房间数据 = TP.readLong(窗口句柄, 基址类.人物基址, 偏移类.地图偏移);
        log.info("房间数据：{}", 房间数据);
        update();
    }

    public void update() {
        long 首地址 = TP.readLong(窗口句柄, 房间数据 + 偏移类.地图开始2);
        long 尾地址 = TP.readLong(窗口句柄, 房间数据 + 偏移类.地图结束2);
        门列表.clear();
        怪物列表.clear();
        材料列表.clear();
        for (long addr = 首地址; addr < 尾地址; addr += 8) {
            long 物品数据 = TP.readLong(窗口句柄, addr);
            String 物品名称 = TP.readStringAddr(窗口句柄, TP.readLong(窗口句柄, 物品数据 + 偏移类.名称偏移), 1, 50);
            物品类型枚举 类型1 = 物品类型枚举.到物品类型(TP.readInt(窗口句柄, 物品数据 + 偏移类.类型偏移));
            物品类型枚举 类型2 = 物品类型枚举.到物品类型(TP.readInt(窗口句柄, 物品数据 + 偏移类.类型偏移 + 4));
            if (类型1 == 物品类型枚举.人形 || 类型2 == 物品类型枚举.人形) continue;
            坐标类 物品坐标 = 基础功能类.取物品坐标(窗口句柄, 物品数据);
            物品阵营枚举 阵营 = 物品阵营枚举.到物品阵营(TP.readInt(窗口句柄, 物品数据 + 偏移类.阵营偏移));
            if (类型2 == 物品类型枚举.门) {
                int state = TP.readInt(窗口句柄, 物品数据 + 3392);
                if (state == 0 || state == 1) {
                    门列表.add(物品坐标);
                    continue;
                }
            }
            int HP = 0;
            if (类型2 == 物品类型枚举.怪物) {
                HP = TP.readInt(窗口句柄, 物品数据 + 偏移类.怪物血量);
                if (HP > 0) {
                    怪物列表.add(物品坐标);
                    log.info("名称:{}\t类型1:{}\t类型2:{}\t阵营:{}\tHP:{}\t坐标:{}",
                            物品名称,
                            类型1,
                            类型2,
                            阵营,
                            HP,
                            物品坐标);
                }
            }
            if (类型2 == 物品类型枚举.地面物品) {
                材料列表.add(物品坐标);
            }
        }
    }

    public 坐标类 取当前房间坐标() {
        long 地图数据 = TP.readLong(窗口句柄, 基址类.房间编号, 偏移类.时间基址, 偏移类.门型偏移);
        int startRoomX = TP.readInt(窗口句柄, 地图数据 + 偏移类.当前房间X);
        int startRoomY = TP.readInt(窗口句柄, 地图数据 + 偏移类.当前房间Y);
        return new 坐标类(startRoomX, startRoomY);
    }

    public boolean 判断是否通关() {
        long 地图数据 = TP.readLong(窗口句柄, 基址类.房间编号, 偏移类.时间基址, 偏移类.门型偏移);
        int val = TP.readInt(窗口句柄, 地图数据 + 偏移类.篝火判断);
        return val == 2 || val == 0;
    }

    public List<坐标类> 取怪物列表() {
        return 怪物列表;
    }

    public List<坐标类> 取材料列表() {
        return 材料列表;
    }

    public List<坐标类> 取门列表() {
        return 门列表;
    }

    public 坐标类 取过图门坐标(方向枚举 dir) {
        switch (dir) {
            case 上:
                门列表.sort((a, b) -> {
                    if (a.Y() == b.Y()) return 0;
                    return a.Y() > b.Y() ? 1 : -1;
                });
                break;
            case 下:
                门列表.sort((a, b) -> {
                    if (a.Y() == b.Y()) return 0;
                    return a.Y() < b.Y() ? 1 : -1;
                });
                break;
            case 左:
                门列表.sort((a, b) -> {
                    if (a.X() == b.X()) return 0;
                    return a.X() > b.X() ? 1 : -1;
                });
                break;
            case 右:
                门列表.sort((a, b) -> {
                    if (a.X()== b.X()) return 0;
                    return a.X() < b.X() ? 1 : -1;
                });
                break;
        }
        return 门列表.get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        房间信息类 房间信息类 = (房间信息类) o;
        return 窗口句柄 == 房间信息类.窗口句柄 && 房间数据 == 房间信息类.房间数据;
    }

    @Override
    public int hashCode() {
        return Objects.hash(窗口句柄, 房间数据);
    }
}
