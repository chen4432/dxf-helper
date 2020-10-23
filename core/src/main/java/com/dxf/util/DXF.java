package com.dxf.util;

import com.dxf.core.GameMaster;

public class DXF {

    public static class BASE_ADDR {
        private static final long PLAYER = 5549592112L; // 人物基址
    }

    public static class OBJECT_CODE {

        private static final int[] DEFAULT_BUFF = {
                1298,       // 超速 30三速
                2600021,    // 精神刺激 20冷却
                2600022,    // 霸体
                2600027,    // 透明
                2600656,    // 斗神 12伤害
        };
    }

    /**
     * 消耗品CLL
     * @param code
     */
    public static void consumeObject(int hwnd, int code) {
        String objectCode = Integer.toHexString(code);
        String playerBaseAddr = Long.toHexString(BASE_ADDR.PLAYER);
        GameMaster.asmClear();
        GameMaster.asmAdd("push rbx");
        GameMaster.asmAdd("sub rsp, 28");
        GameMaster.asmAdd("mov rax," + playerBaseAddr);
        GameMaster.asmAdd("mov rax, [rax]");
        GameMaster.asmAdd("mov r8, [rax]");
        GameMaster.asmAdd("mov edx," + objectCode); // 物品BUFF代码
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
