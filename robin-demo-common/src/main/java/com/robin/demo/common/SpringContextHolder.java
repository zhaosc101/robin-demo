package com.robin.demo.common;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.Map;
public class SpringContextHolder {

    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * <pre>
     *     发布事件
     *
     * @param event 事件
     * </pre>
     */
    public static void eventPublish(Object event) {
        applicationContext.publishEvent(event);
    }
    
    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {

        return applicationContext.getBean(requiredType);

    }
    
    /**
     * <pre>
     *     获取指定类型的Bean
     *
     * @param beanName beanName
     * @param clazz    指定的类型
     * @param <T>      泛型T
     * @return         T t
     * </pre>
     */
    public static <T> T getBean(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }
    
    /**
     * <pre>
     *     获取当前profile
     *     默认获取第一个
     *
     * @return profile
     * </pre>
     */
    public static String getActiveProfile() {
        
        return applicationContext.getEnvironment().getActiveProfiles()[0];
    }
    
    /**
     * <pre>
     *
     *     获取RequestAttributes信息
     *
     * @return requestAttributes
     * </pre>
     */
    public static RequestAttributes getRequestAttributes() {
        return HttpServletContextHolder.getRequestAttributes();
    }
    
    /**
     * <pre>
     *     获取Request信息
     *
     * @return request
     * </pre>
     */
    public static HttpServletRequest getRequest() {
        return HttpServletContextHolder.getRequest();
    }
    
    /**
     * <pre>
     *     从Spring容器中获取指定类型的Bean
     *
     * @param clazz 指定类型
     * @param <T>   返回类型泛型
     * @return 结果map
     * </pre>
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return applicationContext.getBeansOfType(clazz);
    }
    
    
    /**
     * <pre>
     *     获取指定注解的Bean
     *
     * @param  annType 指定注解类型
     * @return 结果map
     * </pre>
     */
    public static Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annType) {
        return applicationContext.getBeansWithAnnotation(annType);
    }
    
    /**
     * <pre>
     *     当前是否开发/测试模式
     *
     * @return boolean true|false
     * </pre>
     */
    public static boolean isDemoMode() {
        return  isTestMode() || isDevMode();
    }
    
    /**
     * <pre>
     *     当前是否开发环境
     *
     * @return boolean
     * </pre>
     */
    public static boolean isDevMode() {
        return "dev".equalsIgnoreCase(getActiveProfile());
    }
    
    /**
     * <pre>
     *     当前是否测试环境
     *
     * @return boolean
     * </pre>
     */
    public static boolean isTestMode() {
        return "test".equalsIgnoreCase(getActiveProfile());
    }
    
}