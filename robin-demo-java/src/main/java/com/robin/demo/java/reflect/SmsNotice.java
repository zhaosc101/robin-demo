/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 14:03
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.reflect;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 14:03
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/14 14:03
 */
public class SmsNotice implements Notice{
    @Override public String send() {
        System.out.println("1");
        return "success";
    }

    @Override public String sendSms(String hello) {
        return "你能"+hello;
    }
}
