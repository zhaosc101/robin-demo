/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/8 10:43
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/8 10:43
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/8 10:43
 */
public class AssertTest {

    @Test
    public void testPrecondition_not_null() {

        try {
            Preconditions.checkNotNull(null,"this is null value,%s",2);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testCheckIndex() {

        try {
            List<String> list = ImmutableList.of();
            Preconditions.checkElementIndex(10,list.size());

        } catch (Exception e) {
//          Assert.assertThat(e,Assert.fail());
        }
    }

    @Test
    public void testObjects() {
        Objects.isNull(null);
//        Assert.assertThat("1","1".endsWith("1"));
    }
    @Test
    public void testAssert() {

        String str="";
        assert str.equals("1");
    }
}
