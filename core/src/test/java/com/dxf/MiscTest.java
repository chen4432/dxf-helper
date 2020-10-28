package com.dxf;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author GuJun
 * @date 2020/10/28
 */
public class MiscTest {

    @Test
    public void sort() {
        List<Integer> a = Arrays.asList(2, 8, 3, 4, 6, 3, 3, 5);
        System.out.println(a);
        a.sort((x, y) -> {
            if (x == y) return 0;
            return x > y ? 1 : -1; // 从小到大排序
        });
        System.out.println(a);
        a.sort((x, y) -> {
            if (x == y) return 0;
            return x > y ? 1 : -1; // 从大到小排序
        });
        System.out.println(a);


    }

}
