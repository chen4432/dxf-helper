package com.dxf.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class DXFTest {

    final private DXF dxf = new DXF();

    @Before
    public void setUp() {
        dxf.setUp();
    }

    @After
    public void cleanUp() {
        dxf.cleanUp();
    }

    @Test
    public void get_player_pos() throws Exception {
        for (int i = 0; i < 1; ++i) {
            System.out.println(dxf.getPlayerPos());
            //Thread.sleep(333);
        }
    }

    @Test
    public void move_to() throws Exception {
        dxf.moveTo(new Point(400, 148));
    }

    @Test
    public void traversal_map() throws Exception {
        DXF.RoomInfo roomInfo = new DXF.RoomInfo(dxf);
    }

    @Test
    public void measure_speed() throws Exception {
        dxf.measureSpeed();
    }

}