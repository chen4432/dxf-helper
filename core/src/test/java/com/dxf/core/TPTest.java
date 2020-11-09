package com.dxf.core;

import com.dxf.component.基础功能类;
import org.junit.Test;

import java.util.Arrays;

public class TPTest {

    @Test
    public void ver() throws Exception {
        System.out.println(TP.ver());
    }

    @Test
    public void set_and_get_path() throws Exception {
        System.out.println(TP.getPath());
        System.out.println(TP.setPath("C:\\GameMaster\\"));
        System.out.println(TP.getPath());
    }

    @Test
    public void find_window() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
    }

    @Test
    public void move_window() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        TP.moveWindow(hwnd, 0, 0);
    }


    @Test
    public void read_int() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long val = TP.readInt(hwnd, "14A894594", 0);
        System.out.println(val);
    }

    @Test
    public void read_int_2() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long val = TP.readInt(hwnd, "[14AC80E30]+F20", 0);
        System.out.println(val);
    }

    @Test
    public void write_int() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        TP.writeInt(hwnd, "[14AC80E30]+F20", 0, 0);
        long val = TP.readInt(hwnd, "[14AC80E30]+F20", 0);
        System.out.println(val);
    }

    @Test
    public void read_string() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        String val = TP.readData(hwnd, "<dnf.exe>+D5500D5", 8); // 读取8个字节
        System.out.println(val);
    }

    @Test
    public void get_module_base_addr() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long addr = TP.getModuleBaseAddr(hwnd, "dnf.exe");
        System.out.println(addr);
        System.out.printf("%x\n", addr);
    }

    @Test
    public void get_module_base_addr_2() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long addr = TP.getModuleBaseAddr(hwnd, "ACE-Base64.dll");
        System.out.println(addr);
        System.out.printf("%x\n", addr);
    }

    @Test
    public void assemble() throws Exception {
        TP.asmAdd("add [rcx+rbx*2+4800], bh");
        TP.asmAdd("add [rcx+rbx*2+4800], bh");
        TP.asmAdd("add [rcx+rbx*2+4800], bh");
        String str = TP.assemble(0, 1);
        System.out.println("汇编代码：" + str);
        TP.asmClear();
        str = TP.assemble(0, 1);
        System.out.println("汇编代码：" + str);
    }

    @Test
    public void assemble_2() throws Exception {
        TP.asmAdd("push dword ptr[112233bb]");
        TP.asmAdd("mov ecx,dword ptr[aabbccdd]");
        TP.asmAdd("mov cx,word ptr[aabbccdd]");
        TP.asmAdd("mov cl,byte ptr[aabbccdd]");
        String str = TP.assemble(0, 1);
        System.out.println(str);
    }

    @Test
    public void disAssemble() throws Exception {
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        System.out.println(TP.disAssemble("81 05 E0 5A 47 00 01 00 00 00", 0, 1));
        System.out.println(TP.disAssemble("4D 5A", 0, 1));
        System.out.println(TP.disAssemble("00 BC 59 00480000", 0, 1));
    }

    @Test
    public void disAssemble_2() throws Exception {
        System.out.println(TP.disAssemble("48 BA 0000000000000000", 0, 1));
        System.out.println(TP.disAssemble("48 BE 9B82AC1400000000", 0, 1));
        System.out.println(TP.disAssemble("48 8B 36", 0, 1));
        System.out.println(TP.disAssemble("48 89 F1", 0, 1));
        System.out.println(TP.disAssemble("BF FF FF FF FF", 0, 1));
        System.out.println(TP.disAssemble("48 B8 0000000000000000", 0, 1));
        System.out.println(TP.disAssemble("FF D0", 0, 1));
    }

    @Test
    public void find_color() throws Exception {
        String ret = TP.findColorE(645, 557, 778, 678, "a2ff3d-202020", 0.8, 0);
        System.out.println(ret);
    }

    // 按ESC按键，判断菜单有没有出现。选择角色 355,462,402,476,宽高(47,14)
    @Test
    public void 判断_1() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(355-5, 462-5, 402+5, 476+5, "选择角色.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

    // 点击选择角色，判断选择角色界面有没有出现。选择角色 355,462,402,476,宽高(47,14)
    @Test
    public void 判断_2() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(575-5, 565-5, 624+5, 579+5, "结束游戏.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

    // 角色选中框
    @Test
    public void 判断_3() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(0, 0, 800, 600, "角色选中框.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

    // 吃药移动
    @Test
    public void 判断_4() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(0, 0, 800, 600, "选择地图.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

    // 吃药移动
    @Test
    public void 地图_世界_4() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(0, 0, 800, 600, "世界.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

    //
    @Test
    public void 地图_根特皇宫_4() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(0, 0, 800, 600, "根特皇宫.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

    //
    @Test
    public void 地图_根特皇宫_冒险() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(566-5, 329-5, 590+5, 340+5, "冒险.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

    @Test
    public void 判断_赛利亚房间() throws Exception {
        TP.setPath("C:\\Users\\jun\\Desktop\\lib");
        for (int i = 0; i <10000; ++i) {
            String ret = TP.findPicE(421-5, 194-5, 460+5, 210+5, "赛利亚.bmp", "202020", 0.9, 0);
            System.out.println(ret);
            Thread.sleep(1000);
        }
    }

}