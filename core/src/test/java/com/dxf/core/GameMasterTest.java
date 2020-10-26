package com.dxf.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameMasterTest {

    @Test
    public void ver() throws Exception {
        System.out.println(GameMaster.ver());
    }

    @Test
    public void set_and_get_path() throws Exception {
        System.out.println(GameMaster.getPath());
        System.out.println(GameMaster.setPath("C:\\GameMaster\\"));
        System.out.println(GameMaster.getPath());
    }

    @Test
    public void find_window() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
    }

    @Test
    public void read_int() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long val = GameMaster.readInt(hwnd, "14A894594", 0);
        System.out.println(val);
    }

    @Test
    public void read_int_2() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long val = GameMaster.readInt(hwnd, "[14AC80E30]+F20", 0);
        System.out.println(val);
    }

    @Test
    public void write_int() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        GameMaster.writeInt(hwnd, "[14AC80E30]+F20", 0, 0);
        long val = GameMaster.readInt(hwnd, "[14AC80E30]+F20", 0);
        System.out.println(val);
    }

    @Test
    public void read_string() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        String val = GameMaster.readData(hwnd, "<dnf.exe>+D5500D5", 8); // 读取8个字节
        System.out.println(val);
    }

    @Test
    public void get_module_base_addr() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long addr = GameMaster.getModuleBaseAddr(hwnd, "dnf.exe");
        System.out.println(addr);
        System.out.printf("%x\n", addr);
    }

    @Test
    public void get_module_base_addr_2() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        long addr = GameMaster.getModuleBaseAddr(hwnd, "ACE-Base64.dll");
        System.out.println(addr);
        System.out.printf("%x\n", addr);
    }

    @Test
    public void assemble() throws Exception {
        GameMaster.asmAdd("add [rcx+rbx*2+4800], bh");
        GameMaster.asmAdd("add [rcx+rbx*2+4800], bh");
        GameMaster.asmAdd("add [rcx+rbx*2+4800], bh");
        String str = GameMaster.assemble(0, 1);
        System.out.println("汇编代码：" + str);
        GameMaster.asmClear();
        str = GameMaster.assemble(0, 1);
        System.out.println("汇编代码：" + str);
    }

    @Test
    public void assemble_2() throws Exception {
        GameMaster.asmAdd("push dword ptr[112233bb]");
        GameMaster.asmAdd("mov ecx,dword ptr[aabbccdd]");
        GameMaster.asmAdd("mov cx,word ptr[aabbccdd]");
        GameMaster.asmAdd("mov cl,byte ptr[aabbccdd]");
        String str = GameMaster.assemble(0, 1);
        System.out.println(str);
    }

    @Test
    public void disAssemble() throws Exception {
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        System.out.println(GameMaster.disAssemble("81 05 E0 5A 47 00 01 00 00 00", 0, 1));
        System.out.println(GameMaster.disAssemble("4D 5A", 0, 1));
        System.out.println(GameMaster.disAssemble("00 BC 59 00480000", 0, 1));
    }

    @Test
    public void disAssemble_2() throws Exception {
        System.out.println(GameMaster.disAssemble("48 BA 0000000000000000", 0, 1));
        System.out.println(GameMaster.disAssemble("48 BE 9B82AC1400000000", 0, 1));
        System.out.println(GameMaster.disAssemble("48 8B 36", 0, 1));
        System.out.println(GameMaster.disAssemble("48 89 F1", 0, 1));
        System.out.println(GameMaster.disAssemble("BF FF FF FF FF", 0, 1));
        System.out.println(GameMaster.disAssemble("48 B8 0000000000000000", 0, 1));
        System.out.println(GameMaster.disAssemble("FF D0", 0, 1));
    }

    @Test
    public void find_color() throws Exception {
        String ret = GameMaster.findColorE(645, 557, 778, 678, "a2ff3d-202020", 0.8, 0);
        System.out.println(ret);
    }




}