package com.dxf.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Optional;

public class DXFTest {

    final private DXF dxf = new DXF();

    @Before
    public void setUp() {
        dxf.setUp();
        dxf.ignoreMapAndObstacle();
    }

    @After
    public void cleanUp() {
        dxf.unIgnoreMapAndObstacle();
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
        dxf.moveTo(new Point2D(0, 0));
    }

    @Test
    public void move_to_next_door() throws Exception {
        DXF.RoomInfo roomInfo = new DXF.RoomInfo(dxf);
        Point2D door = roomInfo.nextDoorPos(DXF.Direction.LL);
        System.out.println("nextDoor: " + door);
        dxf.moveTo(door);
        Thread.sleep(1000);
        for (int i = 0; i < 10; ++i) {
            roomInfo.update();
            Point ps = dxf.getPlayerPos();
            Optional<Point2D> opt = roomInfo.nextMonsterPos(new Point2D(ps.x, ps.y));
            System.out.println("nextMonster: " + opt);
            if (opt.isPresent()) {
                dxf.moveTo(opt.get());
            } else {
                break;
            }
            Thread.sleep(1000);
        }
    }

    @Test
    public void move_to_next_monster() throws Exception {
        for (int i = 0; i < 10; ++i) {
            DXF.RoomInfo roomInfo = new DXF.RoomInfo(dxf);
            Point ps = dxf.getPlayerPos();
            Optional<Point2D> opt = roomInfo.nextMonsterPos(new Point2D(ps.x, ps.y));
            System.out.println("nextMonster: " + opt);
            if (opt.isPresent()) {
                dxf.moveTo(opt.get());
            } else {
                break;
            }
            Thread.sleep(1000);
        }
    }

    @Test
    public void measure_speed() throws Exception {
        dxf.measureSpeed();
    }

    @Test
    public void traverse_map() throws Exception {
        DXF.RoomInfo roomInfo = new DXF.RoomInfo(dxf);
    }

    @Test
    public void get_map() throws Exception {
        DXF.MapInfo mapInfo = new DXF.MapInfo(dxf);
    }

}