package com.dxf.model;

import java.util.Objects;

public class 坐标类 {
    public int x;
    public int y;

    public 坐标类(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public 坐标类() {}

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static double 计算距离(坐标类 a, 坐标类 b) {
        return Math.sqrt(Math.pow((a.x-b.x), 2) + Math.pow((a.y-b.y), 2));
    }

    @Override
    public String toString() {
        return "坐标{" + x + ", " + y + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        坐标类 坐标 = (坐标类) o;
        return x == 坐标.x && y == 坐标.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public static void main(String[] args) {
        System.out.println(new 坐标类(12, 32));
    }
}
