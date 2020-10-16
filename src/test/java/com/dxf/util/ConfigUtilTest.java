package com.dxf.util;

import com.typesafe.config.Config;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigUtilTest {

    @Test
    public void test() {
        Config conf = ConfigUtil.getConfig("constant.conf");
        System.out.println(conf.getLong("商店基址"));
    }

    @Test
    public void test_2() {
        Config conf = ConfigUtil.getConfig("基址.conf");
        System.out.println(conf.getLong("商店基址"));
    }

}