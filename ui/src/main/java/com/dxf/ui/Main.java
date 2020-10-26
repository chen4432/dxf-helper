package com.dxf.ui;

import javax.swing.*;

public class Main {
    private JTabbedPane mapTraversalPane;
    private JTable tbl;
    private JPanel rootPanel;
    private JTabbedPane toolTabledPane;
    private JPanel toolPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
