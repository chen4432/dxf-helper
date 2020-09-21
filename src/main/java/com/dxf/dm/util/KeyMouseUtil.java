package com.dxf.dm.util;

import com.dxf.dm.core.DmCore;
import com.dxf.dm.exception.DmOptException;
import com.dxf.dm.model.Coordinate2D;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * 大漠键鼠工具集
 *
 * @author GuJun
 * @date 2020/9/21
 */
public class KeyMouseUtil {

    private static final ActiveXComponent dm = DmCore.getDm();

    public static Coordinate2D getCursorPos() throws DmOptException {
        Variant x = new Variant(-1, true);
        Variant y = new Variant(-1, true);
        int retCode = Dispatch.call(dm, "GetCursorPos", x, y).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do GetCursorPos, retCode: " + retCode);
        }
        return new Coordinate2D(x.getInt(), y.getInt());
    }

    /**
     * 按住指定的虚拟键码
     * @param vkCode 虚拟按键码
     * @throws DmOptException
     */
    public static void keyDown(int vkCode) throws DmOptException {
        int retCode = Dispatch.call(dm, "KeyDown", vkCode).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do KeyDown, retCode: " + retCode);
        }
    }

    /**
     * 按住指定的虚拟键码
     * @param keyStr 字符串描述的键码. 大小写无所谓
     * @throws DmOptException
     */
    public static void keyDownChar(String keyStr) throws DmOptException {
        int retCode = Dispatch.call(dm, "KeyDownChar", keyStr).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do KeyDownChar, retCode: " + retCode);
        }
    }

    /**
     * 按下指定的虚拟键码
     * @param vkCode 虚拟按键码
     * @throws DmOptException
     */
    public static void keyPress(int vkCode) throws DmOptException {
        int retCode = Dispatch.call(dm, "KeyPress", vkCode).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do KeyPress, retCode: " + retCode);
        }
    }

    /**
     * 按下指定的虚拟键码
     * @param keyStr 字符串描述的键码. 大小写无所谓
     * @throws DmOptException
     */
    public static void keyPressChar(String keyStr) throws DmOptException {
        int retCode = Dispatch.call(dm, "KeyPressChar", keyStr).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do KeyPressChar, retCode: " + retCode);
        }
    }

    /**
     * 弹起来虚拟键vk_code
     * @param vkCode 虚拟按键码
     * @throws DmOptException
     */
    public static void keyUp(int vkCode) throws DmOptException {
        int retCode = Dispatch.call(dm, "KeyUp", vkCode).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do KeyUp, retCode: " + retCode);
        }
    }

    /**
     * 弹起来虚拟键key_str
     * @param keyStr 字符串描述的键码. 大小写无所谓
     * @throws DmOptException
     */
    public static void keyUpChar(String keyStr) throws DmOptException {
        int retCode = Dispatch.call(dm, "KeyUpChar", keyStr).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do KeyUpChar, retCode: " + retCode);
        }
    }

    /**
     * 点击鼠标左键
     * @throws DmOptException
     */
    public static void leftClick() throws DmOptException {
        int retCode = Dispatch.call(dm, "LeftClick").getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do LeftClick, retCode: " + retCode);
        }
    }

    /**
     * 点击鼠标左键
     * @throws DmOptException
     */
    public static void rightClick() throws DmOptException {
        int retCode = Dispatch.call(dm, "RightClick").getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do RightClick, retCode: " + retCode);
        }
    }

    /**
     * 把鼠标移动到目的点(x,y)
     * @param pos
     * @throws DmOptException
     */
    public static void moveTo(Coordinate2D pos) throws DmOptException {
        int retCode = Dispatch.call(dm, "MoveTo", pos.x, pos.y).getInt();
        if (retCode != 1) {
            throw new DmOptException("Failed to do MoveTo, retCode: " + retCode);
        }
    }


}
