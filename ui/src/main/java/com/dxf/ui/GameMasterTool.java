package com.dxf.ui;

import com.dxf.core.GameMaster;

import javax.swing.*;

/**
 * @author GuJun
 * @date 2020/10/28
 */
public class GameMasterTool {

    private final JFrame frame = new JFrame("v0.1.20201028.1632");
    private final JPanel panel = new JPanel();
    private final JButton button1 = new JButton("OK");
    private final Box box = Box.createHorizontalBox();

    private void layout() {
        box.add(button1);
        panel.add(box);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
    }

    public GameMasterTool() {
        layout();
    }

    public static void main(String[] args) {
        new GameMasterTool();
    }
}
