package com.dxf.dm;

import com.dxf.dm.core.DmCore;
import org.junit.Test;

import java.util.List;

public class DmCoreTest {

    @Test
    public void register() throws RuntimeException {
        DmCore.register();
    }

    @Test
    public void get_ver() {
        System.out.println(DmCore.getVersion());
    }

    @Test
    public void enum_process() throws Exception {
        List<Integer> pids = DmCore.WindowUtil.enumProcess("chrome.exe");
        System.out.println(pids);
    }

    @Test
    public void find_window() {
        System.out.println(DmCore.WindowUtil.findWindow("地下城与勇士", "地下城与勇士"));
    }

    @Test
    public void get_window_process_id() {
        int hwnd = DmCore.WindowUtil.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(DmCore.WindowUtil.getWindowProcessId(hwnd));
    }


}