/**
 * All rights Reserved, Designed By Suixingpay.
 *
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/21 14:54
 * @Copyright ©2019 Suixingpay. All rights reserved.
 */
package com.robin.spring;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * {TODO}
 * @author: zhao_sc[zhao_sc@suixingpay.com]
 * @date: 2019/2/21 14:54
 * @version: V1.0
 * @review: zhao_sc[zhao_sc@suixingpay.com]/2019/2/21 14:54
 */
public class IPatternResolver implements ResourcePatternResolver {
    @Override public Resource[] getResources(String locationPattern) throws IOException {
        return new Resource[0];
    }

    @Override public Resource getResource(String location) {
        return null;
    }

    @Override public ClassLoader getClassLoader() {
        return null;
    }
}
