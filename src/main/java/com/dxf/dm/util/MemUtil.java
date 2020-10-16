package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.google.common.base.Strings;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;

import java.util.ArrayList;
import java.util.List;


/**
 * 大漠内存工具集
 *
 * @author GuJun
 * @date 2020/9/21
 */
public class MemUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    /**
     * 把单精度浮点数转换成二进制形式.
     * @param val 需要转化的单精度浮点数
     * @return 字符串形式表达的二进制数据. 可以用于WriteData FindData FindDataEx等接口.
     * @throws DmOptException
     */
    public static String floatToData(float val) throws DmOptException {
        return Dispatch.call(dm, "FloatToData", val).getString();
    }

    /**
     * 把双精度浮点数转换成二进制形式
     * @param val 需要转化的双精度浮点数
     * @return 字符串形式表达的二进制数据. 可以用于WriteData FindData FindDataEx等接口.
     * @throws DmOptException
     */
    public static String doubleToData(double val) throws DmOptException {
        return Dispatch.call(dm, "DoubleToData", val).getString();
    }


    public static String int8ToData(int val) throws DmOptException {
        return Dispatch.call(dm, "IntToData", val, 2L).getString();
    }

    public static String int16ToData(int val) throws DmOptException {
        return Dispatch.call(dm, "IntToData", val, 1L).getString();
    }

    /**
     * 把整数转换成二进制形式.
     * @param val
     * @return
     * @throws DmOptException
     */
    public static String int32ToData(int val) throws DmOptException {
        long type = 0L;
        return Dispatch.call(dm, "IntToData", val, type).getString();
    }

    public static String int64ToData(long val) throws DmOptException {
        long type = 3L;
        return Dispatch.call(dm, "IntToData", val, type).getString();
    }

    public static long readInt(int hwnd, String addr, int type) throws DmOptException {
        return Dispatch.call(dm, "ReadInt", hwnd, addr, type).getLong();
    }

    public static int readInt(int hwnd, long addr, int type) throws DmOptException {
        return (int)Dispatch.call(dm, "ReadIntAddr", hwnd, addr, type).getLong();
    }

    public static void writeInt(int hwnd, String addr, int type, long val) throws DmOptException {
        int retCode = Dispatch.call(dm, "WriteInt", hwnd, addr, type, val).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to WriteInt, retCode: " + retCode);
        }
    }

    public static void writeInt(int hwnd, long addr, int type, long val) throws DmOptException {

    }

    public static String readString(int hwnd, long addr, int type, int len) throws DmOptException {
        String str = Dispatch.call(dm, "ReadStringAddr", hwnd, addr, type, len).getString();
        int errCode = BasicUtil.getLastError();
        if (errCode != 0) {
            throw new DmOptException("Failed to do ReadStringAddr, errCode: " + errCode);
        }
        return str;
    }

    /**
     * 对指定地址写入二进制数据
     * @param hwnd 窗口句柄
     * @param addr 用字符串来描述地址，类似于CE的地址描述，数值必须是16进制,里面可以用[ ] + -这些符号来描述一个地址。+表示地址加，-表示地址减
     *             模块名必须用<>符号来圈起来:
     *             1.         "4DA678" 最简单的方式，用绝对数值来表示地址
     *             2.         "<360SE.exe>+DA678" 相对简单的方式，只是这里用模块名来决定模块基址，后面的是偏移
     *             3.         "[4DA678]+3A" 用绝对数值加偏移，相当于一级指针
     *             4.         "[<360SE.exe>+DA678]+3A" 用模块定基址的方式，也是一级指针
     *             5.         "[[[<360SE.exe>+DA678]+3A]+5B]+8" 这个是一个三级指针
     *
     * @param data 二进制数据，以字符串形式描述，比如"12 34 56 78 90 ab cd"
     * @throws DmOptException
     */
    public static void writeData(int hwnd, String addr, String data) throws DmOptException {
        int retCode = Dispatch.call(dm, "WriteData", hwnd, addr, data).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to WriteData, retCode: " + retCode);
        }
    }

    public static void writeData(int hwnd, long addr, String data) throws DmOptException {
        int retCode = Dispatch.call(dm, "WriteDataAddr", hwnd, addr, data).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to WriteDataAddr, retCode: " + retCode);
        }
    }

    public static void writeStringAddr(int hwnd, long addr, int type, String str) {
        int retCode = Dispatch.call(dm, "WriteStringAddr", hwnd, addr, type, str).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to WriteStringAddr, retCode: " + retCode);
        }
    }

    /**
     * 根据指定的窗口句柄，来获取对应窗口句柄进程下的指定模块的基址
     * @param hwnd  窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId
     * @param module  模块名
     * @return 模块的基址
     */
    public static long getModuleBaseAddr(int hwnd, String module) {
        return Dispatch.call(dm, "GetModuleBaseAddr", hwnd, module).getLong();
    }

    /**
     * 根据指定的窗口句柄，来获取对应窗口句柄进程下的指定模块的大小
     * @param hwnd 窗口句柄或者进程ID
     * @param module 模块名
     * @return 模块的大小
     */
    public static int getModuleSize(int hwnd, String module) {
        return Dispatch.call(dm, "GetModuleSize", hwnd, module).getInt();
    }

    /**
     * 根据指定的目标模块地址,获取目标窗口(进程)内的导出函数地址.
     * @param hwnd  窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param baseAddr 目标模块地址,比如user32.dll的地址,可以通过GetModuleBaseAddr来获取.
     * @param funName 需要获取的导出函数名.  比如"SetWindowTextA".
     * @return 获取的地址. 如果失败返回0
     */
    public static long getRemoteApiAddress(int hwnd, long baseAddr, String funName) {
        long retCode = Dispatch.call(dm, "GetRemoteApiAddress", hwnd, baseAddr, funName).getLong();
        if (retCode == 0) {
            throw new DmOptException("Failed to do GetRemoteApiAddress, retCode: " + retCode);
        }
        return retCode;
    }


    /**
     * 在指定的窗口所在进程分配一段内存.
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr 预期的分配地址。 如果是0表示自动分配，否则就尝试在此地址上分配内存.
     * @param size 需要分配的内存大小.
     * @param type 需要分配的内存类型，取值如下:
     *             0 : 可读可写可执行
     *             1 : 可读可执行，不可写
     *             2 : 可读可写,不可执行
     * @return 分配的内存地址，如果是0表示分配失败.
     */
    public static long virtualAllocEx(int hwnd, long addr, int size, int type) throws DmOptException {
        long retCode = Dispatch.call(dm, "VirtualAllocEx", hwnd, addr, size, type).getLong();
        if (retCode == 0) {
            throw new DmOptException("Failed to do VirtualAllocEx, retCode: " + retCode);
        }
        return retCode;
    }

    /**
     * 释放用VirtualAllocEx分配的内存.
     * @param hwnd 窗口句柄或者进程ID.  默认是窗口句柄. 如果要指定为进程ID,需要调用SetMemoryHwndAsProcessId.
     * @param addr VirtualAllocEx返回的地址
     * @throws DmOptException
     */
    public static void virtualFreeEx(int hwnd, long addr) throws DmOptException {
        int retCode = Dispatch.call(dm, "VirtualFreeEx", hwnd, addr).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do VirtualFreeEx, retCode: " + retCode);
        }
    }

    /**
     * 搜索指定的二进制数据,默认步长是1.如果要定制步长，请用FindDataEx
     * @param hwnd
     * @param addrRange
     * @param data
     * @return
     */
    public List<String> findData(int hwnd, String addrRange, String data) {
        List<String> ans = new ArrayList<>();
        return ans;
    }


    /**
     * 搜索指定的二进制数据.
     * @param hwnd 指定搜索的窗口句柄或者进程ID.
     * @param addrRange 指定搜索的地址集合，字符串类型，这个地方可以是上次FindXXX的返回地址集合,可以进行二次搜索.(类似CE的再次扫描)
     *                  如果要进行地址范围搜索，那么这个值为的形如如下(类似于CE的新搜索)
     *                  "00400000-7FFFFFFF" "80000000-BFFFFFFF" "00000000-FFFFFFFF" 等.
     * @param data 要搜索的二进制数据 以字符串的形式描述比如"00 01 23 45 67 86 ab ce f1"等
     *             这里也可以支持模糊查找,用??来代替单个字节. 比如"00 01 ?? ?? 67 86 ?? ce f1"等.
     * @param step 搜索步长.
     * @param multiThread 表示是否开启多线程查找.  0不开启，1开启.
     *                    开启多线程查找速度较快，但会耗费较多CPU资源. 不开启速度较慢，但节省CPU.
     * @param mode 1 表示开启快速扫描(略过只读内存)  0表示所有内存类型全部扫描.
     * @return
     */
    public List<String> findDataEx(int hwnd, String addrRange, String data, int step, int multiThread, int mode) throws DmOptException {
        List<String> ans = new ArrayList<>();
        String ret = Dispatch.call(dm, "FindDataEx", hwnd, addrRange, data, step, multiThread, mode).getString();
        int errCode = BasicUtil.getLastError();
        if (errCode != 0) {
            throw new DmOptException("Failed to do FindDataEx, errCode: " + errCode);
        }
        if (!Strings.isNullOrEmpty(ret)) {
            String[] fields = ret.split("\\|", -1);
            for (String s : fields) {
                ans.add(s);
            }
        }
        return ans;
    }
}
