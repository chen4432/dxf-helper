package com.dxf;

import com.dxf.component.人物角色类;
import com.dxf.component.基础功能类;
import com.dxf.core.GameMaster;
import com.dxf.model.游戏状态枚举;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class DXF {

    private final int 窗口句柄;

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

    public void 激活窗口() {
        GameMaster.setWindowState(窗口句柄, 1);
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
        if (ret != 1) {
            System.out.println("绑定窗口失败！");
        } else {
            System.out.println("绑定窗口成功！");
        }
        */
        基础功能类.延时(1000);
        激活窗口();
    }

    public void cleanUp() {
        GameMaster.unbindWindow();
    }

    private ExecutorService 线程池 = Executors.newSingleThreadExecutor();
    private Future<?> future;

    public void 开始() {
        激活窗口();
        future = 线程池.submit(new Task());
    }

    @Data
    class Task implements Runnable {
        @Override
        public void run() {
            激活窗口();
            人物角色类 player = new 人物角色类(窗口句柄);
            log.info("人物角色信息: " + player);
            if (基础功能类.取游戏状态(窗口句柄) == 游戏状态枚举.在副本中) {
                player.刷一次图();
            }
            while (player.get剩余疲劳值() > 8) {
                player.进图_根特皇宫();
                player.刷一次图();
            }
        }
    }

    public void 停止() {
        if (future != null) {
            future.cancel(true);
            future = null;
        }
    }

    public void 绑定窗口() {
        setUp();
    }

    public void 解绑窗口() {
        cleanUp();
    }

    public static void main(String[] args) {
        DXF dxf = new DXF();
        dxf.setUp();
        人物角色类 player = new 人物角色类(dxf.get窗口句柄());
        log.info("人物角色信息: " + player);
        if (基础功能类.取游戏状态(dxf.get窗口句柄()) == 游戏状态枚举.在副本中) {
            player.刷一次图();
        }
        while (player.get剩余疲劳值() > 8) {
            player.进图_根特皇宫();
            player.刷一次图();
        }
        dxf.cleanUp();
        System.exit(0);
    }

}
