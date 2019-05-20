/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/26 9:23
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.export;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/26 9:23
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/4/26 9:23
 */
public class ExportInTest {

    public static void main(String[] args) {
        try {
            ExportInTest path = new ExportInTest();
            InputStream stream = path.path("file/clearBlack.xlsx");
            ExcelListener excelListener = new ExcelListener();
            EasyExcelFactory.readBySax(stream, new Sheet(3,2,ReadModel.class), excelListener);
//            long b = System.currentTimeMillis();
//            List<Object> list = EasyExcelFactory.read(stream, new Sheet(2,1,ReadModel.class));
//            long e = System.currentTimeMillis()-b;
//            System.out.println("用时"+e+""+list.size());

            stream.close();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public InputStream  path(String fileName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("" + fileName);
//        URL fileName = this.getClass().getResource("/file/clearBlack.xlsx");
//        String fileName1 = this.getClass().getResource("/file/clearBlack.xlsx").getFile();
//        System.out.println(fileName);
//        System.out.println(fileName1);
//        return fileName.getFile();
    }
}
