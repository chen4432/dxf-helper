package com.dxf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleUI {
    private JButton 按键_绑定窗口;
    private JButton 按键_解绑窗口;
    private JButton 按键_开始运行;
    private JButton 按键_停止运行;
    private JPanel root;
    private JLabel 标签_绑定状态;
    private JLabel 标签_运行状态;


    public SimpleUI() {
        标签_绑定状态.setText("未绑定");
        标签_运行状态.setText("已停止");
        DXF dxf = new DXF();

        按键_绑定窗口.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dxf.绑定窗口();
                标签_绑定状态.setText("已绑定");
            }
        });
        按键_解绑窗口.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dxf.解绑窗口();
                标签_绑定状态.setText("未绑定");
            }
        });
        按键_开始运行.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dxf.开始();
                标签_运行状态.setText("运行中");
            }
        });
        按键_停止运行.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dxf.停止();
                标签_运行状态.setText("已停止");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SimpleUI");
        frame.setContentPane(new SimpleUI().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
