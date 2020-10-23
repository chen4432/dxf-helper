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

        System.out.println(GameMaster.ver());
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

    @Test
    public void test_2() {
        GameMaster.reg("suifengyunnuo5fe95058910f5da8bb59e01fb48b93d2", "83Y4N");
        GameMaster.dmGuard(1, "memory2");
        GameMaster.dmGuard(1, "hm dm.dll 1");
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        GameMaster.asmClear();
        GameMaster.asmAdd("push rbx");
        GameMaster.asmAdd("sub rsp, 28");
        GameMaster.asmAdd("mov rax, 14ac80e30");
        GameMaster.asmAdd("mov rax, [rax]");
        GameMaster.asmAdd("mov r8, [rax]");
        //GameMaster.asmAdd("mov edx, 1d3f93ef"); // 1d3f93ef: 春之祝福2000力智
        //GameMaster.asmAdd("mov edx, 1d3503e0"); // 1d3503e0: 雪人药 14000力智, 三速50%
        GameMaster.asmAdd("mov edx, 27AC56"); // 27AC56: 霸体
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("mov rcx, rax");
        GameMaster.asmAdd("call qword [r8+1280]");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add rsp, 28");
        GameMaster.asmAdd("pop rbx");
        GameMaster.asmAdd("ret");
        GameMaster.asmCall(hwnd, 1);
    }

    @Test
    public void drug() {
        String drug = "1D3F93EF";
        int hwnd = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(hwnd);
        GameMaster.asmClear();
        GameMaster.asmAdd("push rbx");
        GameMaster.asmAdd("sub rsp, 28");
        GameMaster.asmAdd("mov rax, 14ac80e30");
        GameMaster.asmAdd("mov rax, [rax]");
        GameMaster.asmAdd("mov r8, [rax]");
        //GameMaster.asmAdd("mov edx, 1d3f93ef"); // 1d3f93ef: 春之祝福2000力智
        GameMaster.asmAdd("mov edx," + drug); // 1d3503e0: 雪人药 14000力智, 三速50%
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("mov rcx, rax");
        GameMaster.asmAdd("call qword [r8+1280]");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add [rax], al");
        GameMaster.asmAdd("add rsp, 28");
        GameMaster.asmAdd("pop rbx");
        GameMaster.asmAdd("ret");
        GameMaster.asmCall(hwnd, 1);
    }
}
