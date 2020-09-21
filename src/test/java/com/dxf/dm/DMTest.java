package com.dxf.dm;

import com.dxf.dm.core.DM;
import org.junit.Test;

import java.util.List;

public class DMTest {

    @Test
    public void register() throws RuntimeException {
        DM.register();
    }

    @Test
    public void get_ver() {
        System.out.println(DM.getVersion());
    }

    @Test
    public void enum_process() throws Exception {
        List<Integer> pids = DM.WindowUtil.enumProcess("chrome.exe");
        System.out.println(pids);
    }

    @Test
    public void find_window() {
        System.out.println(DM.WindowUtil.findWindow("地下城与勇士", "地下城与勇士"));
    }

    @Test
    public void get_window_process_id() {
        int hwnd = DM.WindowUtil.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(DM.WindowUtil.getWindowProcessId(hwnd));
    }


}