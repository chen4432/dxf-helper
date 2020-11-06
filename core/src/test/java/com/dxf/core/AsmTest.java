package com.dxf.core;

import org.junit.Test;

public class AsmTest {

    // 物品CALL
    @Test
    public void test_1() {

        // push rbx
        // sub rsp, 28
        // mov rax, 人物基址（14AC80E30）
        // mov rax, [rax]
        // mov r8, [rax]
        // mov edx, 物品代码
        // add [rax], al
        // add [rax], al
        // mov rcx, rax
        // call qword [r8+物品CALL]
        // add [rax], al
        // add [rax], al
        // add rsp, 28
        // pop rbx
        // ret

        System.out.println(TP.ver());
        String code = "";
        code += "53 48 83 EC 28 48 B8";
        code += "300EC84A01000000"; // 14AC80E30
        code += "48 8B 00 4C 8B 00 BA";
        code += "EF933F1D00000000";
        code += "48 8B C8 41 FF 90";
        code += "8012000000000000"; // 8012000000000000
        code += "48 83 C4 28 5B C3";
        //String s1 = GameMaster.disAssemble(code, 0, 1);
       // System.out.println(s1);
    }

    /**
     * 缓冲CALL
     * sub rsp, 100
     * mov rax, [14ac80e30]; 发包基址
     * mov edx, 0
     * mov rax, 14ac80e30; 缓冲CALL
     * call rax
     * add rsp, 100
     *
     * sub rsp, 100
     * mov rax, [1]
     * mov rcx, rax
     * mov edx, 2
     * add [rax], al
     * add [rax], al
     * mov rax, 3
     * call rax
     * add rsp, 100
     */
    @Test
    public void 缓冲CALL_() {
        String code = "";
        code += "48 81 EC 00 01 00 00";
        code += "48 A1 E0 C4 E6 4A 01 00 00 00"; // 发包基址 14AE6C4E0
        code += "48 8B C8";
        code += "BA 2A 00 00 00 00 00 00 00"; // 包头
        code += "48 B8 40 72 25 45 01 00 00 00"; // 缓冲CALL
        code += "FF D0";
        code += "48 81 C4 00 01 00 00";
        String s = TP.disAssemble(code, 0, 1);
        System.out.println(s);
    }

    @Test
    public void 加密CALL() {
        String code = "";
        code += "48 81 EC 00 01 00 00";
        code += "48 A1 01 00 00 00 00 00 00 00";
        code += "48 8B C8";
        code += "48 BA 02 00 00 00 00 00 00 00";
        code += "48 B8 03 00 00 00 00 00 00 00";
        code += "FF D0";
        code += "48 81 C4 00 01 00 00";
        String s = TP.disAssemble(code, 0, 1);
        System.out.println(s);
    }

    @Test
    public void 发包CALL() {
        String code = "";
        code += "48 81 EC 00 01 00 00";
        code += "48 B8 40 AA 35 45 01 00 00 00";
        code += "FF D0";
        code += "48 81 C4 00 01 00 00";
        String s = TP.disAssemble(code, 0, 1);
        System.out.println(s);
    }


    /**
     * 缓冲CALL
     * sub rsp, 100
     * mov rax, [14ac80e30]; 发包基址
     * mov edx, 0
     * mov rax, 14ac80e30; 缓冲CALL
     * call rax
     * add rsp, 100
     *
     */

    @Test
    public void test_2() {
        TP.reg("suifengyunnuo5fe95058910f5da8bb59e01fb48b93d2", "83Y4N");
        TP.dmGuard(1, "memory2");
        TP.dmGuard(1, "hm dm.dll 1");
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        TP.asmClear();
        TP.asmAdd("push rbx");
        TP.asmAdd("sub rsp, 28");
        TP.asmAdd("mov rax, 14ac80e30");
        TP.asmAdd("mov rax, [rax]");
        TP.asmAdd("mov r8, [rax]");
        //GameMaster.asmAdd("mov edx, 1d3f93ef"); // 1d3f93ef: 春之祝福2000力智
        //GameMaster.asmAdd("mov edx, 1d3503e0"); // 1d3503e0: 雪人药 14000力智, 三速50%
        TP.asmAdd("mov edx, 27AC56"); // 27AC56: 霸体
        TP.asmAdd("add [rax], al");
        TP.asmAdd("add [rax], al");
        TP.asmAdd("mov rcx, rax");
        TP.asmAdd("call qword [r8+1280]");
        TP.asmAdd("add [rax], al");
        TP.asmAdd("add [rax], al");
        TP.asmAdd("add rsp, 28");
        TP.asmAdd("pop rbx");
        TP.asmAdd("ret");
        TP.asmCall(hwnd, 1);
    }

    @Test
    public void drug() {
        String drug = "1D3F93EF";
        int hwnd = TP.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        TP.asmClear();
        TP.asmAdd("push rbx");
        TP.asmAdd("sub rsp, 28");
        TP.asmAdd("mov rax, 14ac80e30");
        TP.asmAdd("mov rax, [rax]");
        TP.asmAdd("mov r8, [rax]");
        //GameMaster.asmAdd("mov edx, 1d3f93ef"); // 1d3f93ef: 春之祝福2000力智
        TP.asmAdd("mov edx," + drug); // 1d3503e0: 雪人药 14000力智, 三速50%
        TP.asmAdd("add [rax], al");
        TP.asmAdd("add [rax], al");
        TP.asmAdd("mov rcx, rax");
        TP.asmAdd("call qword [r8+1280]");
        TP.asmAdd("add [rax], al");
        TP.asmAdd("add [rax], al");
        TP.asmAdd("add rsp, 28");
        TP.asmAdd("pop rbx");
        TP.asmAdd("ret");
        TP.asmCall(hwnd, 1);
    }
}
