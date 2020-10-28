package com.dxf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author GuJun
 * @date 2020/10/28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point2D {
    public int x;
    public int y;

    public static double distance(Point2D a, Point2D b) {
        return Math.sqrt(Math.pow((a.x-b.x), 2) + Math.pow((a.y-b.y), 2));
    }

    public static void main(String[] args) {
        Point2D x = new Point2D(0, 0);
        Point2D y = new Point2D(3, 4);
        System.out.println(distance(x, y));
    }
}
