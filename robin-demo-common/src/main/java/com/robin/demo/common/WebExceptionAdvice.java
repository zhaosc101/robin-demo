package com.robin.demo.common;

import com.suixingpay.antifraud.common.exception.PermissionDeniedException;
import com.suixingpay.antifraud.common.exception.ServiceException;
import com.suixingpay.antifraud.gateway.core.utils.OperationResult;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Optional;
@Slf4j
@ControllerAdvice
public class WebExceptionAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OperationResult<Void> illegalArgumentException(IllegalArgumentException iae, HttpServletRequest request) {
        log.warn("API请求:{}, 参数异常:{}", request.getRequestURI(), iae.getMessage());

        return new OperationResult<>(iae.getMessage(), OperationResult.BAD_PARAMETER);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OperationResult<Void> illegalArgumentTypeException(MethodArgumentTypeMismatchException iae, HttpServletRequest request) {
        log.warn("API请求:{}, 参数异常:{}", request.getRequestURI(), iae.getMessage());

        return new OperationResult<>("参数值类型不匹配,请检查参数", OperationResult.BAD_PARAMETER);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OperationResult<Void> argumentNotValidException(MethodArgumentNotValidException argNve, HttpServletRequest request) {

        Class<?> parameterType = argNve.getParameter().getParameterType();

        Optional<String> first = argNve.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> {

                    String fieldLabel = getFieldLabel(fieldError.getField(), parameterType);

                    return fieldLabel + fieldError.getDefaultMessage();

                })
                .findFirst();

        log.warn("API请求:{}, 参数异常:{}", request.getRequestURI(), first.orElse("请求异常"));

        return new OperationResult<>(first.orElse("请求异常"), OperationResult.BAD_PARAMETER);
    }

    private String getFieldLabel(String field, Class<?> type) {

        String label = field;

        try {
            Field fieldObj = getFieldByFieldPath(field, type);

            if (fieldObj != null) {
                ApiModelProperty modelProperty = fieldObj.getAnnotation(ApiModelProperty.class);

                label = modelProperty != null ? modelProperty.value() : label;
            }
        }
        catch (Exception e) {
            log.warn("获取字段label失败[field={}, clz={}]: {}", field, type.getName(), e.getMessage(), e);
        }

        return label;
    }

    private Field getFieldByFieldPath(String fpath, Class<?> type) {

        String[] fields = StringUtils.split(fpath, "\\.");

        Class<?>[] clzes = new Class[fields.length];
        clzes[0] = type;

        for (int i = 0; i < fields.length; i++) {

            String fname = fields[i];
            Class<?> clze = clzes[i];

            Field field = null;
            try {
                field = clze.getField(fname);
            }
            catch (NoSuchFieldException e) {
                try {
                    field = clze.getDeclaredField(fname);
                }
                catch (NoSuchFieldException e1) {
                    //不用记录
                }
            }

            if (field == null && clze.getSuperclass() != Object.class) {
                field = getFieldByFieldPath(fname, clze.getSuperclass());
            }

            if (field == null) return null;

            if (i == fields.length - 1) {
                return field;
            }

            clzes[i+1] = field.getType();
        }

        return null;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OperationResult<Void> messageNotReadableException(HttpMessageNotReadableException notRdEx, HttpServletRequest request) {

        log.warn("API请求:{}, 参数异常:{}", request.getRequestURI(), notRdEx.getMessage());

        return new OperationResult<>("HTTP报文格式错误或缺少报文", OperationResult.BAD_PARAMETER);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public OperationResult<Void> missingParameterException(MissingServletRequestParameterException missParaEx, HttpServletRequest request) {

        log.warn("API请求:{}, 参数异常:{}", request.getRequestURI(), missParaEx.getMessage());

        return new OperationResult<>("缺少参数" + missParaEx.getParameterName() + "(" + missParaEx.getParameterType() + ")", OperationResult.BAD_PARAMETER);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public OperationResult<Void> runtimeException(RuntimeException re, HttpServletRequest request) {
        log.error("API请求:{}, 未知异常:{}", request.getRequestURI(), re.getMessage(), re);

        return new OperationResult<>("服务异常,请稍后重试!", OperationResult.FAILURE);
    }
    
    @ResponseBody
    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public OperationResult webExceptionHandler(ServiceException ex, HttpServletRequest request) {
        
        log.error("\n-\tApi接口{}请求异常，异常信息：\n{}", request.getRequestURI(), ex.getMessage(), ex);
        return new OperationResult(ex.getMessage(), ex.getCode());
    }
    
    
    /**
     * <pre>
     *     前端要求返回状态码为200
     *
     * @param  ex       权限拦截信息
     * @param  request  请求req
     * @return 响应拦截结果
     * </pre>
     */
    @ResponseBody
    @ExceptionHandler(PermissionDeniedException.class)
    @ResponseStatus(HttpStatus.OK)
    public OperationResult permissionDeniedExceptionHandler(PermissionDeniedException ex, HttpServletRequest request) {
        
        log.error("\n-\t权限检查不通过。URI:{}， 拦截信息：{}", request.getRequestURI(), ex.getMessage(), ex);
        return new OperationResult(ex.getCode(), ex.getMessage());
    }
}
