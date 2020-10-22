package com.dxf.core;

import org.junit.Test;

public class AsmTest {

    // 物品CALL
    @Test
    public void test_1() {

        // push rbx|
        // sub rsp, 28|
        // mov rax, 78563412|
        // mov rax, [rax]|
        // mov r8, [rax]|
        // mov edx, 78563412|
        // add [rax], al|
        // add [rax], al|
        // mov rcx, rax|
        // call qword [r8+78563412]|
        // add [rax], al|
        // add [rax], al|
        // add rsp, 28|
        // pop rbx|
        // ret

        String code = "53 48 83 EC 28 48 B8";
        code += "1234567800000000";
        code += "48 8B 00 4C 8B 00 BA";
        code += "1234567800000000";
        code += "48 8B C8 41 FF 90";
        code += "1234567800000000";
        code += "48 83 C4 28 5B C3";
        String s1 = GameMaster.disAssemble(code, 0, 1);
        System.out.println(s1);
    }
}
