package com.dxf.ui;

import com.dxf.core.GameMaster;
import com.dxf.util.DXF;
import com.google.common.base.Strings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
            if (hwnd > 0) {
                String str = textField.getText();
                if (Strings.isNullOrEmpty(str) || "请填写物品代码（多个请用，分割）...".equals(str)) {
                    for (int code : DXF.OBJECT_CODE.DEFAULT_BUFF) {
                        DXF.consumeObject(hwnd, code);
                    }
                } else {
                    String[] fields = str.split(",", -1);
                    for (String s : fields) {
                        DXF.consumeObject(hwnd, Integer.parseInt(s));
                    }
                }
            }

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
