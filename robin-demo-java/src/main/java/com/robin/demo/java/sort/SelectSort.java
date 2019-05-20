/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/24 15:14
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java.sort;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/4/24 15:14
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/4/24 15:14
 */

public class SelectSort {

    public static void main(String[] args) {
//        String s = ;
        char [] a = "zhaosc".toCharArray();

    }

    public void selectSort(int[] a) {
        int n =  a.length;
        //
        for (int i = 0; i <n-1 ; i++) {//遍历次数
            //遍历一次记录最小值
            int min = i;
            for (int j = i+1; j <n ; j++) {
                if(a[j]<a[min]) min = j;
                if(min !=j) {
                    int temp = a[j];
                    a[j] =a[min];
                    a[min] = temp;
                }
            }
        }
    }
}
