/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/5 13:44
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java;

import sun.misc.VM;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/5 13:44
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/5 13:44
 */

public class ForRemoveTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();

        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.removeIf(x->x.equals("01"));
        System.out.println(list);
    }
private static int a = 1;
    public void test(Predicate predicate) {
        if(predicate.test(a)) {

            System.out.println(1);
        }

    }

}
