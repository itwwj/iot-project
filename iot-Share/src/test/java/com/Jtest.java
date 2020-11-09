package com;


import cn.hutool.core.codec.Base64;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jie
 */
public class Jtest {
    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String a = i + "";
        }
        long l1 = System.currentTimeMillis();
        System.out.println("1耗时：" + (l1 - l));
        long l2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            String a = String.valueOf(i);
        }
        long l3 = System.currentTimeMillis();
        System.out.println("2耗时：" + (l3 - l2));

    }

    public static <T extends Number> List<T> ingotSort(Map<String, T> map, int size) {
        List<T> list = new ArrayList();
        for (int i = 0; i < size; i++) {
            Number t = map.get(String.valueOf(size));
            if (t == null) {
                t = 0;
            }
            list.add((T) t);
        }
        return list;
    }

    @Test
    public void test() {
        String encode = Base64.encode("0000", "utf8");
        System.out.println(encode);
    }
}
