package com.robin.demo.common;

import ch.qos.logback.classic.ClassicConstants;
import com.google.common.collect.Maps;
import com.suixingpay.antifraud.common.support.utils.JsonUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class HttpServletContextHolder {

    public static HttpSession getSession() {
        return getRequest().getSession();
    }
    /**
     * <pre>
     *     获取HttpServletRequest
     *
     * @return request
     * </pre>
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }
    
    /**
     * <pre>
     *     获取HttpServletResponse
     *
     * @return response
     * </pre>
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }
    
    /**
     * <pre>
     *     获取RequestAttributes
     *
     * @return attributes
     * </pre>
     */
    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes();
    }
    
    
    /**
     * <pre>
     *     获取ServletContext
     *
     * @return servletContext
     * </pre>
     */
    public static ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }
    
    /**
     * <pre>
     *     获取请求IP地址
     *
     * @return ip地址
     * </pre>
     */
    public static String getRequestClientIp() {
        
        return getRequestClientIp(getRequest());
    }
    
    /**
     * <pre>
     *     获取客户端IP地址
     *
     * @param request
     * @return
     * </pre>
     */
    public static String getRequestClientIp(HttpServletRequest request) {
    
        String UNKNOWN = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * <pre>
     *     获取请求指纹
     *
     * @return 请求指纹
     * </pre>
     */
    public static String getRequestFinger() {
    
        HttpServletRequest request = getRequest();
        
        Map<String, String> dataMap = Maps.newHashMap();
        dataMap.put("req.method", request.getMethod());
        dataMap.put(ClassicConstants.REQUEST_REQUEST_URI, request.getRequestURI());
        dataMap.put(ClassicConstants.REQUEST_REQUEST_URL, request.getRequestURL().toString());
        dataMap.put(ClassicConstants.REQUEST_USER_AGENT_MDC_KEY, request.getHeader("User-Agent"));
        dataMap.put("clientId", request.getHeader("X-Forwarded-For"));
        return JsonUtils.object2String(dataMap);
    }
    
    /**
     * <pre>
     *     获取当前Request Header Value
     *
     * @param  headerKey header Key
     * @return HeaderValue
     * </pre>
     */
    public static String getReqHeaderValue(String headerKey) {
        return getRequest().getHeader(headerKey);
    }
    
}
