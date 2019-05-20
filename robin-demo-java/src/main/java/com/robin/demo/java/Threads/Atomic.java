/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/24 17:51
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.Threads;

import java.util.concurrent.atomic.AtomicReference;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/24 17:51
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/4/24 17:51
 */
public class Atomic {

    static class Man {

        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
    public static void main(String[] args) {


        Man man1 = new Man();
        man1.setAge("11");
        AtomicReference ar = new AtomicReference();
    }
}
