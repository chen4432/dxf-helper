package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

/**
 * 大漠汇编工具集
 *
 * @author GuJun
 * @date 2020/9/21
 */
public class AsmUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    /**
     * 添加指定的MASM汇编指令到缓冲区. 支持标准的masm汇编指令.
     * @param code MASM汇编指令,大小写均可以  比如 "mov eax,1" ,也支持直接加入字节，比如"emit 90 90 90 90"等. 同时也支持跳转指令，标记.
     *        标记必须以":"开头. 跳转指令后必须接本次AsmCall之前的存在的有效Label. 另外跳转只支持短跳转,就是跳转的字节码不能超过128个字节.
     * @throws DmOptException
     */
    public static void add(String code) throws DmOptException {
        int retCode = Dispatch.call(dm, "AsmAdd", code).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do AsmAdd, retCode: " + retCode);
        }
    }

    /**
     * 清除汇编指令缓冲区 用add添加到缓冲的指令全部清除
     */
    public static void clear() throws DmOptException {
        int retCode = Dispatch.call(dm, "AsmClear").getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do AsmClear retCode " + retCode);
        }
    }

    /**
     * 执行用汇编缓冲区中的指令.
     * @param hwnd 窗口句柄
     * @param mode 模式，取值如下
     *              0 : 在本进程中进行执行，这时hwnd无效. 注: 此模式会创建线程.
     *              1 : 对hwnd指定的进程内执行,注入模式为创建远程线程
     *              2 ：必须在对目标窗口进行注入绑定后,才可以用此模式(直接在目标进程创建线程).此模式下的call的执行是排队的,如果同时有多个call在此窗口执行,那么必须排队.所以执行效率不如模式1. 同时此模式受目标窗口刷新速度的影响,目标窗口刷新太慢，也会影响此模式的速度. 注: 此模式会创建线程.
     *              3 ：同模式2,但是此模式不会创建线程,而直接在hwnd所在线程执行.
     *              4 ：同模式0, 但是此模式不会创建线程,直接在当前调用AsmCall的线程内执行.
     *              5 : 对hwnd指定的进程内执行,注入模式为APC. 此模式必须开启memory盾。任意一个memory盾都可以.
     *              6 : 直接hwnd所在线程执行.
     * @throws DmOptException
     */
    public static void call(int hwnd, int mode) throws DmOptException {
        Dispatch.call(dm, "AsmCall", hwnd, mode);
        long code = BasicUtil.getLastError();
        if (code != 0) {
            throw new DmOptException("Failed to do AsmCall, errCode: " + code);
        }
    }

}
