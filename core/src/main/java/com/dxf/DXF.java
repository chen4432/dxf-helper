package com.dxf;

import com.dxf.component.人物角色类;
import com.dxf.component.基础功能类;
import com.dxf.core.GameMaster;
import com.dxf.model.游戏状态枚举;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;


@Slf4j
public class DXF {

    private final ExecutorService 线程池 = Executors.newSingleThreadExecutor();

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
        int ret = GameMaster.bindWindowEx(
                窗口句柄,
                "dx.graphic.2d",
                "dx.mouse.position.lock.api|dx.mouse.position.lock.message|dx.mouse.clip.lock.api|dx.mouse.input.lock.api|dx.mouse.state.api|dx.mouse.api|dx.mouse.cursor",
                "dx.keypad.input.lock.api|dx.keypad.state.api|dx.keypad.api",
                "dx.public.active.api|dx.public.active.message",
                0
                );
        if (ret != 1) {
            System.out.println("绑定窗口失败！错误码：" + ret);
        } else {
            System.out.println("绑定窗口成功！");
        }
        激活窗口();
    }

    public void cleanUp() {
        int ret = GameMaster.unbindWindow();
        if (ret != 1) {
            System.out.println("解绑窗口失败！错误码：" + ret);
        } else {
            System.out.println("解绑窗口成功！");
        }
    }

    private Thread thread = null;

    private Future<刷图任务状态枚举> 刷图任务句柄 = null;

    private int 计次 = 0;
    
    public void 执行循环刷根特皇宫任务() {
        System.out.println("执行循环刷根特皇宫任务~");
        while (running && 基础功能类.取角色剩余疲劳值(窗口句柄) >= 8) {
            try {
                long 计时开始 = System.currentTimeMillis();
                刷图任务句柄 = 线程池.submit(new 根特皇宫刷图任务());
                刷图任务状态枚举 状态 = 刷图任务句柄.get(3 * 60, TimeUnit.SECONDS);
                long 计时结束 = System.currentTimeMillis();
                System.out.printf("刷图状态： %s, 次数： %d, 耗时： %d 秒。", 状态, ++计次, (计时结束-计时开始)/1000);
            } catch (TimeoutException e) {
                e.printStackTrace();
                System.out.println("任务超时： " + e.getMessage());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("任务中断异常：" + e.getMessage());
            } catch (ExecutionException e) {
                e.printStackTrace();
                System.out.println("任务执行异常: " + e.getMessage());
            }
        }
    }

    public void 停止循环刷根特皇宫任务() {
        System.out.println("停止循环刷根特皇宫任务");
        if (刷图任务句柄 != null) {
            刷图任务句柄.cancel(true);
            刷图任务句柄 = null;
        }
    }

    public void 开始() {
        if (thread == null) {
            激活窗口();
            thread = new TaskThread();
            thread.start();
            running = true;
        }
    }

    public void 停止() {
        running = false;
        if (刷图任务句柄 != null) {
            刷图任务句柄.cancel(true);
            刷图任务句柄 = null;
        }
    }

    private boolean running = false;

    enum 刷图任务状态枚举 {完成, 被中断, 异常退出}

    private class 根特皇宫刷图任务 implements Callable<刷图任务状态枚举> {
        @Override
        public 刷图任务状态枚举 call() throws Exception {
            try {
                激活窗口();
                人物角色类 player = new 人物角色类(窗口句柄);
                if (基础功能类.取游戏状态(窗口句柄) == 游戏状态枚举.城镇) {
                    player.进图_根特皇宫();
                }
                player.执行刷图任务();
                return 刷图任务状态枚举.完成;
            } catch (InterruptedException e) {
                System.out.println("刷图任务被中断！");
                return 刷图任务状态枚举.被中断;
            } catch (Exception e) {
                return 刷图任务状态枚举.异常退出;
            }
        }
    }

    class TaskThread extends Thread {
        @Override
        public void run() {
            try {
                执行循环刷根特皇宫任务();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void 绑定窗口() {
        setUp();
    }

    public void 解绑窗口() {
        cleanUp();
    }

    public static void main(String[] args) throws Exception {
        DXF dxf = new DXF();
        dxf.setUp();
        人物角色类 player = new 人物角色类(dxf.get窗口句柄());
        log.info("人物角色信息: " + player);
        if (基础功能类.取游戏状态(dxf.get窗口句柄()) == 游戏状态枚举.在副本中) {
            player.执行刷图任务();
        }
        while (player.get剩余疲劳值() > 8) {
            player.进图_根特皇宫();
            player.执行刷图任务();
        }
        dxf.cleanUp();
        System.exit(0);
    }

}
