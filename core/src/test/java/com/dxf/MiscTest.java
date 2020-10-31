package com.dxf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;

/**
 * @author GuJun
 * @date 2020/10/28
 */
public class MiscTest {

    @Test
    public void sort() {
        List<Integer> a = Arrays.asList(2, 8, 3, 4, 6, 3, 3, 5);
        System.out.println(a);
        a.sort((x, y) -> {
            if (x == y) return 0;
            return x > y ? 1 : -1; // 从小到大排序
        });
        System.out.println(a);
        a.sort((x, y) -> {
            if (x == y) return 0;
            return x > y ? 1 : -1; // 从大到小排序
        });
        System.out.println(a);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Point {
        private int x;
        private int y;
    }

    private List<Point> getNeighbors(int[][] channels) {
        return null;
    }


    int W = 3;
    int H = 5;
    private int[][] channels = new int[H][W];
    Point src = new Point(0, 0);
    Point dst = new Point(2, 4);
    public void bfs() {
        LinkedList<Point> queue = new LinkedList<Point>();
        Map<Point, Point> pred = new HashMap<>(W * H);
        Map<Point, Integer> dist = new HashMap<>();
        boolean[][] visited = new boolean[H][W];
        for (int i = 0; i < H; ++i) {
            for (int j = 0; j < W; ++j) {
                visited[i][j] = false;
            }
        }
        visited[src.x][src.y] = true;
        dist.put(src, 0);
        queue.add(src);
        while (!queue.isEmpty()) {
            Point u = queue.remove();
            for (Point neighbor : getNeighbors(channels)) {
                if (visited[neighbor.x][neighbor.y] == false) {
                    visited[neighbor.x][neighbor.y] = true;
                    //dist.put()
                }
            }
        }

    }

    @Test
    public void test() {
        int[][] channels = new int[4][3];
        Point start = new Point(0, 0);
        Point stop = new Point(3, 2);
        Map<Point, Point> parents = new HashMap<>();
        parents.put(start, null);
        List<Point> queue = new ArrayList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Point curr = queue.get(0);
            List<Point> neighbors = getNeighbors(channels);
            for (int i = 0; i < neighbors.size(); ++i) {
                Point neighbor = neighbors.get(0);
                boolean visited = parents.containsKey(neighbor);
                if (visited) continue;
                queue.add(neighbor);
                parents.put(neighbor, curr);
                if (neighbor.equals(stop)) break;
            }
        }
        List<Point> path = new ArrayList<>();
        Map<Point, Point> next = new HashMap<>();
        Point p = stop;
        while (p != null) {
            next.put(p, path.get(0));
            path.add(0, p);
            p = parents.get(p);
        }

        System.out.println(path);
    }

}
