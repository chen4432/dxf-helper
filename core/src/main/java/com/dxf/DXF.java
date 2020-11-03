package com.dxf;

import com.dxf.component.人物角色类;
import com.dxf.component.地图信息类;
import com.dxf.component.基础功能类;
import com.dxf.component.房间信息类;
import com.dxf.core.GameMaster;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DXF {

    private final int 窗口句柄;

    public DXF(int 窗口句柄) {
        this.窗口句柄 = 窗口句柄;
    }

    public DXF() {
        窗口句柄 = GameMaster.findWindow("地下城与勇士", "地下城与勇士");
        if (窗口句柄 == 0) {
            System.out.println("地下城与勇士游戏未启动！");
            System.exit(-1);
        }
    }

    public int get窗口句柄() {
        return 窗口句柄;
    }

    public void setUp() {
        /*
        int ret = GameMaster.bindWindowEx(
                窗口句柄,
                "dx.graphic.2d",
                "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.cursor",
                "dx.keypad.input.lock.api|dx.keypad.state.api|dx.keypad.api",
                "dx.public.active.api|dx.public.active.message",
                0
                );
        System.out.println("BindWindowState:" + ret);
        基础功能类.延时(1000);
         */
        GameMaster.setWindowState(窗口句柄, 1);
        基础功能类.延时(1000);
    }

    public void cleanUp() {
        GameMaster.unbindWindow();
    }

    public static void main(String[] args) {
        DXF dxf = new DXF();
        dxf.setUp();
        人物角色类 player = new 人物角色类(dxf.get窗口句柄());
        log.info("人物角色信息: " + player);
        while (player.get剩余疲劳值() > 8) {
            player.进图_根特皇宫();
            player.刷一次图();
        }
        dxf.cleanUp();
        System.exit(0);
    }

}
