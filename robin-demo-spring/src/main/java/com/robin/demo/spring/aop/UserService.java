/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 11:55
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.spring.aop;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/26 11:55
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/26 11:55
 */
public class UserService {

    public List<Object> getUserList(String aa) {

        return Arrays.asList("111","222");
    }
    public List<Object> getUserList(String aa,String bb) {

        return Arrays.asList(aa,bb);
    }
}
