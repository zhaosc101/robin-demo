/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 13:30
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 13:30
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/26 13:30
 */
public class AppTest {

    public static void main(String[] args) throws Throwable {

        ApplicationContext context = new FileSystemXmlApplicationContext("classpath:application.xml");
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.getUserList("1111","222222"));
    }
}
