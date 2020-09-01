package com.dxf.dm;

import org.junit.Before;
import org.junit.Test;

public class DMTest {

    private DM dm;

    @Before
    public void setUp() {
        dm = new DM();
    }

    @Test
    public void get_ver() {
        System.out.println(dm.getVersion());
    }

    @Test
    public void register() {
        System.out.println(dm.register());
    }

    @Test
    public void find_window() {
        System.out.println(dm.findWindow("地下城与勇士", "地下城与勇士"));
    }

    @Test
    public void get_window_process_id() {
        int hwnd = dm.findWindow("地下城与勇士", "地下城与勇士");
        System.out.println(dm.getWindowProcessId(hwnd));
    }


}