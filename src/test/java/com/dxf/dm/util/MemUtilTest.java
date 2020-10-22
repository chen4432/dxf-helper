package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemUtilTest {

    @Test
    public void read_int() throws Exception {
        DmCore.register();
        GuardUtil.guard(true, "memory");
        int hwnd = WindowUtil.findWindow("", "地下城与勇士");
        for (int i = 0; i < 10000; ++i) {
            long val = MemUtil.readInt(hwnd, 5545481924L, 0);
            System.out.println("val: " + val);
            Thread.sleep(1000);
        }
    }

    @Test
    public void write_int() throws Exception {
        DmCore.register();
        int hwnd = WindowUtil.findWindow("", "MFC示例代码");
        //MemUtil.writeInt(hwnd, "0024F8C8", 0, 1000);
        while (true) {
            Thread.sleep(1000);
        }
    }

    // 无敌
    @Test
    public void write_int_2() throws Exception {
        DmCore.register();
        GuardUtil.guard(true, "memory");
        int hwnd = WindowUtil.findWindow("", "地下城与勇士");
        long val = MemUtil.readInt(hwnd, "[14AC80E30]+1080", 0);
        System.out.println(val);
        MemUtil.writeInt(hwnd, "[14AC80E30]+1080", 0, 100);
        val = MemUtil.readInt(hwnd, "[14AC80E30]+1080", 0);
        System.out.println(val);
    }

    // 无敌
    @Test
    public void write_int_3() throws Exception {
        DmCore.register();
        GuardUtil.guard(true, "memory");
        int hwnd = WindowUtil.findWindow("", "地下城与勇士");
        long val = MemUtil.readInt(hwnd, "[14AC80E30]+1050", 0);
        System.out.println(val);
        MemUtil.writeInt(hwnd, "[14AC80E30]+1050", 0, 0);
        val = MemUtil.readInt(hwnd, "[14AC80E30]+1050", 0);
        System.out.println(val);
    }



    @Test
    public void double_to_data() throws Exception {
        System.out.println(MemUtil.doubleToData(1.3)); // "cd cc cc cc cc cc f4 3f"
        System.out.println(MemUtil.doubleToData(1.4));
    }

    @Test
    public void int8_to_data() throws Exception {
        System.out.println(MemUtil.int8ToData(1)); // 01
    }

    @Test
    public void int16_to_data() throws Exception {
        System.out.println(MemUtil.int16ToData(1)); // 01 00
    }

    @Test
    public void int32_to_data() throws Exception {
        System.out.println(MemUtil.int32ToData(1)); // 01 00 00 00
    }

    @Test
    public void int64_to_data() throws Exception {
        System.out.println(MemUtil.int64ToData(1L)); // 01 00 00 00 00 00 00 00
    }

    @Test
    public void get_module_base_addr() throws Exception {
        DmCore.register();
        int hwnd = WindowUtil.findWindow("", "MFC示例代码");
        System.out.println(MemUtil.getModuleBaseAddr(hwnd, "gdi32.dll"));
    }

    @Test
    public void get_remote_api_address() throws Exception {
        DmCore.register();
        int hwnd = WindowUtil.findWindow("", "MFC示例代码");
        long user32Base = MemUtil.getModuleBaseAddr(hwnd, "user32.dll");
        long setWindowTextAAddr = MemUtil.getRemoteApiAddress(hwnd, user32Base, "SetWindowTextA");
        long addr = MemUtil.virtualAllocEx(hwnd, 0, 50, 0);
        MemUtil.writeStringAddr(hwnd, addr, 0, "哈哈");
        AsmUtil.clear();
        AsmUtil.add(String.format("mov rcx, %x", hwnd));
        AsmUtil.add(String.format("mov rdx, %x", addr));
        AsmUtil.add(String.format("mov rax, %x", setWindowTextAAddr));
        AsmUtil.add("sub rsp, 20");
        AsmUtil.add("call rax");
        AsmUtil.add("add rsp, 20");
        AsmUtil.call(hwnd, 1);
        MemUtil.virtualFreeEx(hwnd, addr);
    }
}