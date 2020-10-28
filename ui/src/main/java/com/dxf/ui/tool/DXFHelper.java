package com.dxf.ui.tool;


import com.dxf.core.GameMaster;
import com.google.common.base.Joiner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author GuJun
 * @date 2020/10/28
 */
public class DXFHelper {
    private JTabbedPane tabbedPane1;
    private JPanel rootPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("DXFHelper");
        frame.setContentPane(new DXFHelper().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    enum CodeTransType {
        BYTEARRAY_TO_ASM,
        ASM_TO_BYTEARRAY;
    }

    public DXFHelper() {
        cbType.addItem("字节集->汇编");
        cbType.addItem("汇编->字节集");
        btnTrans.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String source = taSource.getText();
                if ("字节集->汇编".equals(cbType.getSelectedItem())) {
                    List<String> hexBytes;
                    if (source.contains(",")) {
                        String[] fields = source.split(",", -1);
                        hexBytes = Arrays.stream(fields).map(Byte::new).map(Integer::toHexString).collect(Collectors.toList());
                    } else {
                        String[] fields = source.split(" ", -1);
                        hexBytes = Arrays.stream(fields).collect(Collectors.toList());
                    }
                    taTarget.setText(GameMaster.disAssemble(Joiner.on(" ").join(hexBytes), 0,1));
                }
            }
        });
    }

    private JTabbedPane tabbedPane2;
    private JTextArea taSource;
    private JTextArea taTarget;
    private JComboBox<String> cbType;
    private JButton btnTrans;
    private JTextField textField1;
    private JTextField textField2;
    private JButton MOVEButton;
}
