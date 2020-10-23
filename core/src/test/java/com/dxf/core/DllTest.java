package com.dxf.core;

import com.sun.jna.Library;
import com.sun.jna.Native;
import org.junit.Test;

public class DllTest {

    public interface Dll extends Library {
        Dll INSTANCE = Native.load("DmReg", Dll.class);
        public void SetDllPathA(String path, int mode);
        public void setDllPathW(String path, int mode);
    }

    @Test
    public void test() {
        Dll.INSTANCE.SetDllPathA("src/main/resources/dm.dll", 0);

    }

}
