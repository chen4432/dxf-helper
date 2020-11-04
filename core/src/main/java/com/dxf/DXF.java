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

    private class 循环执行任务线程 extends Thread {
        private final ExecutorService 线程池;
        private final Runnable 任务;
        private Future<?> 任务句柄;

        public 循环执行任务线程(Runnable 任务) {
            线程池 = Executors.newSingleThreadExecutor();
            this.任务 = 任务;
        }

        @Override
        public void run() {
            int cnt = 0;
            while (!Thread.currentThread().isInterrupted() && 基础功能类.取角色剩余疲劳值(窗口句柄) >= 8) {
                try {
                    long 开始时间 = System.currentTimeMillis();
                    任务句柄 = 线程池.submit(任务);
                    任务句柄.get(3*60, TimeUnit.SECONDS);
                    long 结束时间 = System.currentTimeMillis();
                    System.out.printf("任务执行完成！次数：%d, 耗时：%d\n", cnt, (结束时间-开始时间) / 1000);
                } catch (InterruptedException | ExecutionException e) {
                    System.out.printf("任务执行失败！原因：%s\n", e.getMessage());
                    break;
                } catch (TimeoutException e) {
                    System.out.println("检测任务超时，继续执行~");
                }
                cnt++;
            }
            任务句柄.cancel(true);
            线程池.shutdown();
            System.out.println("循环执行任务线程停止~");
        }
    }

    public void 开始() {
        绑定窗口();
        if (thread == null) {
            激活窗口();
            //thread = new TaskThread();
            thread = new 循环执行任务线程(new 根特皇宫刷图任务());
            thread.start();
        }
    }

    public void 停止() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        解绑窗口();
    }

    private class 根特皇宫刷图任务 implements Runnable {
        @Override
        public void run() {
            try {
                激活窗口();
                人物角色类 player = new 人物角色类(窗口句柄);
                if (基础功能类.取游戏状态(窗口句柄) == 游戏状态枚举.城镇) {
                    player.进图_根特皇宫();
                }
                player.执行刷图任务();
            } catch (InterruptedException e) {
                System.out.println("刷图任务被中断！");
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
