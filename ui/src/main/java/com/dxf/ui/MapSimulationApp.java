package com.dxf.ui;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author GuJun
 * @date 2020/10/27
 */
public class MapSimulationApp {

    private final JFrame frame = new JFrame("模拟");
    private final JPanel panel = new JPanel();
    private final Random random = new Random();

    private CopyOnWriteArrayList<Point> pointList = new CopyOnWriteArrayList<>();

    public void layout() {
        frame.add(panel);
        frame.setVisible(true);
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MapSimulationApp() {
        layout();
        for (int i = 0; i < 10; ++i) {
            pointList.add(new Point(Math.abs(random.nextInt() % 800), Math.abs(random.nextInt() % 600)));
        }
    }

    public static void main(String[] args) {
        new MapSimulationApp();
    }

    static class MapPanel extends Panel {
        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);
            Graphics2D g2d = (Graphics2D)g;
        }
    }

}
