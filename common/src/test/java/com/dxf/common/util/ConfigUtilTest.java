package com.dxf.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigUtilTest {

    @Test
    public void test() {
        System.out.println(ConfigUtil.getConfig().getLong("商店基址"));
    }

    @Test
    public void test2() {
        String 哈哈 = "哈哈";
        System.out.println(哈哈);
    }

}