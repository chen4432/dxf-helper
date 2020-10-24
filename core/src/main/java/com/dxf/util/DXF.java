package com.dxf.util;

import com.dxf.core.GameMaster;

public class DXF {

    public static class BASE_ADDR {
        private static final long PLAYER = 5549592112L; // 人物基址
    }

    public static class OBJECT_CODE {

        public static final int[] DEFAULT_BUFF = {
                2600021,    // 精神刺激 20冷却
                2600561,    // 顶级175力量药
                2600562,    // 顶级175智力药
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
        GameMaster.asmCall(hwnd, 5);
    }

}
