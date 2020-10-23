package com.dxf.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMasterApp {

    private final JFrame frame = new JFrame("v0.1.20201023.1542");
    private final JPanel panel = new JPanel();
    private final JTextField textField = new JTextField("请填写物品代码（多个请用，分割）...", 10);
    private final JButton button = new JButton("OK");
    private final Box box = Box.createHorizontalBox();

    private void init() {
        frame.add(panel);
        button.setBackground(Color.ORANGE);
        button.addActionListener((e) -> {
            System.out.println(textField.getText());
        });
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }
        });
        box.add(textField);
        box.add(Box.createHorizontalStrut(2));
        box.add(button);
        frame.add(box);
        frame.setIconImage(new ImageIcon("C:\\Users\\jun\\IdeaProjects\\dxf-helper\\ui\\src\\main\\resources\\alien.png").getImage());
        frame.setBounds(100, 100, 300, 60);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new GameMasterApp().init();
    }


}
