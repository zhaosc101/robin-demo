/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/27 11:40
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.demo.spring.aop.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * xss过滤器
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/27 11:40
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/27 11:40
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {

        return super.getHeader(name);
    }

    @Override
    public Object getAttribute(String name) {

        return super.getAttribute(cleanXss(name));
    }

    @Override
    public Map getParameterMap() {

        Map<String, String[]> requestMap = super.getParameterMap();
        Iterator<Map.Entry<String, String[]>> it = requestMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            String[] value = entry.getValue();
            for (int i = 0; i < value.length; i++) {
                value[i] = cleanXss(value[i]);
            }
            requestMap.put(entry.getKey(),value);
        }
        return requestMap;
    }


    public String cleanXss(String value) {

        if (value != null) {

        }
        return value;
    }



}
