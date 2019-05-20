/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 11:00
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.reflect;

import java.lang.reflect.Proxy;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/14 11:00
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/14 11:00
 */
public class ProxyTest {

    final String a = null;
    String b ;
    public static void main(String[] args) {
        
        char a = 'b';
        System.out.println(a);//b
        int c = a;
        System.out.println(c);//98
        byte d = 'h';
        System.out.println(d);//104
        short f = d;
        System.out.println(f);//104
        JdkInvoke in = new JdkInvoke();
        Notice notice = (Notice) in.bind(new SmsNotice());
        System.out.println(notice.sendSms("小学妹"));

//        Notice po = new SmsNotice();
//        MyInvocationHandler my = new MyInvocationHandler(po);
//
//        Notice pro = (Notice) my.getProxy();
//
//        Notice pro1 = (Notice)Proxy.newProxyInstance(my.getClass().getClassLoader(),
//                po.getClass().getInterfaces(),my);
//        System.out.println(pro.send());
    }
}
