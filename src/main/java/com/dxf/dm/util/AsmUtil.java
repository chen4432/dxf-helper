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
     * 示例1：
     *             add("push 100")
     *             add("push 60304d")
     *             add("emit 90 90 90")
     *             add("push dword ptr[112233bb]")
     *             add("call 678fed")
     *
     * 示例2：
     *             add("mov eax, 1")
     *             add("cmp eax, 1")
     *             add("je Label1")
     *             add("mov eax, 3")
     *             add("jmp Exit")
     *             add(":Label1")
     *             add("mov eax, 2")
     *             add(":Exit")
     *             call(hwnd, 1)
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
            throw new DmOptException("Failed to do AsmClear, retCode " + retCode);
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
     *              5 ：对hwnd指定的进程内执行,注入模式为APC. 此模式必须开启memory盾。任意一个memory盾都可以.
     *              6 ：直接hwnd所在线程执行.
     * @throws DmOptException
     */
    public static void call(int hwnd, int mode) throws DmOptException {
        Dispatch.call(dm, "AsmCall", hwnd, mode);
        long code = BasicUtil.getLastError();
        if (code != 0) {
            throw new DmOptException("Failed to do AsmCall, errCode: " + code);
        }
    }

    /**
     * 执行用AsmAdd加到缓冲中的指令.  这个接口同AsmCall,但是由于插件内部在每次AsmCall时,都会有对目标进程分配内存的操作,这样会不够效率.
     * 所以增加这个接口，可以让调用者指定分配好的内存,并在此内存上执行call的操作
     * @param hwnd 窗口句柄
     * @param mode 同call
     * @param baseAddr 16进制格式. 比如"45A00000",此参数指定的地址必须要求有可读可写可执行属性. 并且内存大小最少要200个字节. 模式6要求至少400个字节. 如果Call的内容较多,那么长度相应也要增加.   如果此参数为空,那么效果就和AsmCall一样.
     * @throws DmOptException
     */
    public static void callEx(int hwnd, int mode, String baseAddr) throws DmOptException {
        Dispatch.call(dm, "AsmCallEx", hwnd, mode);
        long code = BasicUtil.getLastError();
        if (code != 0) {
            throw new DmOptException("Failed to do AsmCallEx, errCode: " + code);
        }
    }

    /**
     * 此接口对AsmCall和AsmCallEx中的模式5和6中内置的一些延时参数进行设置.
     * @param timeout 具体含义看以下说明.(默认值10000) 单位毫秒
     * @param param 具体含义看以下说明. (默认值100) 单位毫秒
     * 注: time_out同时影响模式5和6.单位是毫秒。 表示执行此AsmCall时，最长的等待时间. 超过此时间后，强制结束. 如果是-1，表示无限等待.
     * 比如，当执行某个寻路call时,需要到寻路结束，call才会返回. 那么就需要把此参数设置大一些，甚至设置为-1.
     * param仅影响模式6.  这个值越大,越不容易引起目标进程崩溃，同时call的执行速度相对慢一些. 越小越容易崩溃,同时call的执行速度快一些. 可根据自己情况设置. 一般默认的就可以了.
     * @throws DmOptException
     */
    public static void setCallTimeout(int timeout, int param) throws DmOptException {
        int code = Dispatch.call(dm, "AsmSetTimeout", timeout, param).getInt();
        if (code != 1) {
            throw new DmOptException("Failed to do AsmSetTimeout, errCode: " + code);
        }
    }

    /**
     * 把汇编缓冲区的指令转换为机器码 并用16进制字符串的形式输出
     * @param baseAddr 用AsmAdd添加到缓冲区的第一条指令所在的地址
     * @param is64Bit 表示缓冲区的指令是32位还是64位. 32位表示为0,64位表示为1
     * @return
     * @throws DmOptException
     */
    public static String assemble(long baseAddr, boolean is64Bit) throws DmOptException {
        String ret = Dispatch.call(dm, "Assemble", baseAddr, is64Bit ? 1 : 0).getString();
        long code = BasicUtil.getLastError();
        if (code != 0) {
            throw new DmOptException("Failed to do Assemble, errCode: " + code);
        }
        return ret;
    }

    /**
     * 把指定的机器码转换为汇编语言输出
     * @param code 机器码，形式如 "aa bb cc"这样的16进制表示的字符串(空格无所谓)
     * @param baseAddr 指令所在的地址
     * @param is64Bit 表示asm_code表示的指令是32位还是64位. 32位表示为0,64位表示为1
     * @return
     * @throws DmOptException
     */
    public static String disAssemble(String code, long baseAddr, boolean is64Bit) throws DmOptException {
        String ret = Dispatch.call(dm, "DisAssemble", code, baseAddr, is64Bit ? 1 : 0).getString();
        long errCode = BasicUtil.getLastError();
        if (errCode != 0) {
            throw new DmOptException("Failed to do DisAssemble, errCode: " + code);
        }
        return ret;
    }

}
