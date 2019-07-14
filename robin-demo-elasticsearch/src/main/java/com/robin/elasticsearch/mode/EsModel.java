/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/13 15:21
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.elasticsearch.mode;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/7/13 15:21
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/7/13 15:21
 */
@Data
@ToString
@NoArgsConstructor
public class EsModel {

    private String id;
    private String name;
    private int age;
    private Date date;
}
