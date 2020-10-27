package com.dxf.util;

import com.dxf.core.GameMaster;

public class DXF {

    private final int hwnd;

    public DXF(int hwnd) {
        this.hwnd = hwnd;
    }

    public void setUp() {
        GameMaster.bindWindowEx(
                hwnd,
                "dx.graphic.2d",
                "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.cursor",
                "dx.keypad.input.lock.api|dx.keypad.state.api|dx.keypad.api",
                "dx.public.active.api|dx.public.active.message",
                0
                );
    }

    public void cleanUp() {
        GameMaster.unbindWindow();
    }

    // 基址
    public static class BASE_ADDR {
        private static final long PLAYER            = 5549592112L; // 人物基址
        private static final long GAME_STATE        = 0L; // 游戏状态


    }

    // 偏移
    public static class OFFSET_ADDR {

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
     * @param code 消耗品代码
     */
    public void consumeObject(int code) {
        GameMaster.asmClear();
        GameMaster.asmAdd("push rbx");
        GameMaster.asmAdd("sub rsp, 28");
        GameMaster.asmAdd("mov rax," + Long.toHexString(BASE_ADDR.PLAYER));
        GameMaster.asmAdd("mov rax, [rax]");
        GameMaster.asmAdd("mov r8, [rax]");
        GameMaster.asmAdd("mov edx," + Integer.toHexString(code));
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
