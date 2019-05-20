/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/6 19:58
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java;

import java.util.Comparator;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/6 19:58
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/6 19:58
 */
public class ComparableAndComparator {

    public static void main(String[] args) {
        Student stu1 = new Student();
        stu1.setName("zhaosc");
        Student stu2 = new Student();
        stu2.setName("zhaosc");
        System.out.println(stu1.compareTo(stu2));
        StuComparator comparator = new StuComparator();
        System.out.println(comparator.compare(stu1,stu2));
    }

    public static class Student implements Comparable<Student>{

        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override public int compareTo(Student o) {

            return o.name.compareTo(name);
        }
    }

    public static class StuComparator implements Comparator<Student> {

        @Override public int compare(Student o1, Student o2) {

            return o1.getAge().compareTo(o2.getAge());
        }
    }


}
