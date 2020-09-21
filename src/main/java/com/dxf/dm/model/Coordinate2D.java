package com.dxf.dm.model;

/**
 * 2-D 坐标
 */
public class Coordinate2D {
    public int x;
    public int y;

    public Coordinate2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate2D() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public String toString() {
        return "Coordinate2D{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
