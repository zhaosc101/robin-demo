/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 17:12
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.spring;

import lombok.Getter;
import lombok.Setter;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 17:12
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/26 17:12
 */
@Getter
@Setter
public class Student {

    private String name;
    private int age;
    private Father father;

    @Getter
    @Setter
    public static class Father {

        private String name;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Student student = new Student();
        Father father = new Student.Father();
        father.setName("limi");
        student.setAge(1);
        student.setName("zhaosc");
        student.setFather(father);

    }
}
