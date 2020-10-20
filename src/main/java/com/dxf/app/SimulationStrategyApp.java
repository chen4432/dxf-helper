package com.dxf.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationStrategyApp {

    private static class RunningState {
        boolean state;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("模拟战略");
        JButton button = new JButton("开始");
        JRadioButton radioButton = new JRadioButton("勾选占地");
        final RunningState runningState = new RunningState();
        runningState.state = false;

        JPanel panel = new JPanel();
        panel.add(button);
        panel.add(radioButton);
        panel.setLayout(new FlowLayout());
        frame.setContentPane(panel);
        frame.setBounds(100, 100, 180, 60);
        frame.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (runningState.state) {
                    runningState.state = false;
                    button.setText("开始");
                } else {
                    runningState.state = true;
                    button.setText("停止");
                }
            }
        });
        radioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
