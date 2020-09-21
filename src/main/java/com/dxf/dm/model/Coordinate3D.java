package com.dxf.dm.model;

/**
 * 3-D 坐标
 */
public class Coordinate3D {
    public int x;
    public int y;
    public int z;

    public Coordinate3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coordinate3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
}
