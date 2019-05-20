/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/21 14:13
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.*;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/21 14:13
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/21 14:13
 */
public class ResourceTest {

    public static void main(String[] args) {

//        ResourceLoader loader = new DefaultResourceLoader();
//        Resource resource1 = loader.getResource("D:/test.txt");
//        System.out.println(resource1 instanceof FileSystemResource);
//        Resource resource2 = loader.getResource("D:/test.txt");
//        System.out.println(resource1 instanceof ClassPathResource);
        ApplicationContext context = new FileSystemXmlApplicationContext("application.xml");
    }

}
