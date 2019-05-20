/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/6 19:17
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/6 19:17
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/6 19:17
 */
public class PredicateTest {

    public static void main(String[] args) {
        long h = 0xFFFFFFFF;//11111111
        char a =1;
        char c ='\u0571';
        byte d=01;
        int m='a';
        System.out.println("h"+h);
        System.out.println(a);
        System.out.println(c);
        System.out.println(d);
        System.out.println(m);
//        long f=4
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            Employee employee = new Employee();
            employee.setAge(i);
            employee.setName("aa"+i);
            employee.setSex(i%2==1?"man":"no-man");
            employees.add(employee);
        }
        List<Employee> man = Employee.filter(employees,Employee.isMan());
        System.out.println(man.size());
        System.out.println(man);
        List<Employee> youn = Employee.filter(employees,Employee.isYoung());
        System.out.println(youn.size());
        System.out.println(youn);
    }

    public static class Employee {

        private String name;
        private String sex;
        private Integer age;

        public static Predicate<Employee> isMan(){
            return x->x.sex.equals("man");
        }
        public static Predicate<Employee> isYoung(){
            return x -> x.age < 30;
        }

        public static List<Employee> filter(List<Employee> employees,Predicate<Employee> pre) {
            return employees.stream().filter(pre).collect(Collectors.toList());
        }

        @Override public String toString() {
            return "Employee{" + "name='" + name + '\'' + ", sex='" + sex + '\'' + ", age=" + age + '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
