/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/6 19:34
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.java;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/3/6 19:34
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/3/6 19:34
 */
public class CollectionTest {


    @Test
    public void mapTest() {

        Map<String,Integer> map = new HashMap<>();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        Integer o1 = map.compute("1",(s, o) -> o);
        Integer o2 = map.compute("4",(s, o) ->{
            if (o == null) {
                return 0;
            }
            return o;
        });
        System.out.println(map);
    }

    @Test
    public void FunctionTest() {

        System.out.println(compute(10,a -> a+1));//10
        System.out.println(compute(10,a -> a+1,b->b*9));//91
        System.out.println(compute(10,a -> a*2,b->b-5));//10
        System.out.println(compute2(10,a -> a*2,b->b-5));//15
        System.out.println(compute2(10,a -> a+1,b->b*9));//99

    }

    @Test
    public void concurrentMapTest() {

        Map<String,Integer> con = new ConcurrentHashMap<>();
        con.put("1",1);
        con.put("2",2);

    }

    private int compute(int a,Function<Integer,Integer> function ) {

        return function.apply(a);
    }

    private int compute(int a,Function<Integer,Integer> function,Function<Integer,Integer> function1 ) {

        //先执行funtion1,获得的结果当参数运行funtion
        return function.compose(function1).apply(a);
    }
    private int compute2(int a,Function<Integer,Integer> function,Function<Integer,Integer> function1 ) {

        //先执行function自身得到的结果在执行funtion1
        return function.andThen(function1).apply(a);
    }


    @FunctionalInterface
    public interface FunctionV1<T,R> {

        R apply(T t);

        default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
            Objects.requireNonNull(after);
            return (T t) -> after.apply(apply(t));
        }
    }

}
