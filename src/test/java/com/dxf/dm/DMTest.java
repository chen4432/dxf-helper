package com.dxf.dm;

import org.junit.Test;

public class DMTest {

    @Test
    public void get_ver() {
        System.out.println(DM.getVersion());
    }

    @Test
    public void register() {
        System.out.println(DM.register());
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