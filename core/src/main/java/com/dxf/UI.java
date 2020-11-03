package com.dxf;

import javax.swing.*;

public class UI {
    private JTextArea 日志;
    private JButton 按键_开始运行;
    private JButton 按键_停止运行;
    private JButton 按键_解绑窗口;
    private JButton 按键_绑定窗口;
    private JLabel 标签_绑定状态;
    private JLabel 标签_运行状态;
    private JPanel root;

    public static void main(String[] args) {
        JFrame frame = new JFrame("DXF伴侣");
        frame.setContentPane(new UI().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
