package com.dxf.util;

import com.dxf.DXF;
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
    }

    @After
    public void cleanUp() {
        dxf.cleanUp();
    }
}