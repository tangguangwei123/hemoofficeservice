package com.hemooffice.suopu.globalexception;

import com.alibaba.fastjson.JSONObject;
import com.hemooffice.suopu.exception.CusAuthException;
import com.hemooffice.suopu.exception.CusSystemException;
import com.hemooffice.suopu.exception.LoginAuthException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object errorHandler(Exception e, HttpServletRequest httpServletRequest) throws Exception {
        logger.error(e.getMessage());
        JSONObject jsonObject = new JSONObject();
        if (e instanceof NoHandlerFoundException) {
            jsonObject.put("status", 404);
            jsonObject.put("message", "?????????????????????");
        } else if (e instanceof MissingServletRequestParameterException) {
            jsonObject.put("status", -200);
            jsonObject.put("message", "????????????");
        } else if (e instanceof UnauthenticatedException) {
            jsonObject.put("status", 401);
            jsonObject.put("message", "???????????????,?????????");
        } else if (e instanceof LoginAuthException) {
            jsonObject.put("status", 401);
            jsonObject.put("message", e.getMessage());
        } else if (e instanceof AuthorizationException) {
            jsonObject.put("status", 402);
            jsonObject.put("message", "????????????");
        } else if (e instanceof AuthenticationException) {
            jsonObject.put("status", 403);
            jsonObject.put("message", "??????????????????");
        } else if (e instanceof MaxUploadSizeExceededException) {
            jsonObject.put("status", 240);
            jsonObject.put("message", "??????????????????????????????");
        } else if (e instanceof SQLException) {
            jsonObject.put("status", 250);
            jsonObject.put("message", "?????????????????????");
        } else if (e instanceof SocketTimeoutException) {
            jsonObject.put("status", 260);
            jsonObject.put("message", "??????????????????");
        } else if (e instanceof SocketException) {
            jsonObject.put("status", 240);
            jsonObject.put("message", "??????????????????");
        }else if (e instanceof IOException) {
            jsonObject.put("status", 500);
            jsonObject.put("message", "????????????");
            e.printStackTrace();
        } else if (e instanceof NullPointerException) {
            jsonObject.put("status", 500);
            jsonObject.put("message", "????????????????????????");
            e.printStackTrace();
        } else if (e instanceof HttpMessageNotReadableException) {
            jsonObject.put("status", 506);
            jsonObject.put("message", "????????????????????????:"+e.getMessage() );
            e.printStackTrace();
        } else if (e instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            String errorMesssage = "????????????:";
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMesssage += fieldError.getDefaultMessage() + ", ";
            }
            jsonObject.put("status", 403);
            jsonObject.put("message", errorMesssage);
        }else if (e instanceof ConstraintViolationException) {
            jsonObject.put("status", 403);
            jsonObject.put("message", e.getMessage());
            e.printStackTrace();
        } else if (e instanceof CusSystemException) {
            jsonObject.put("status", 505);
            jsonObject.put("message", e.getMessage());
            e.printStackTrace();
        } else if (e instanceof CusAuthException) {
            jsonObject.put("status", 505);
            jsonObject.put("message", e.getMessage());
            e.printStackTrace();
        }else if (e instanceof MethodArgumentTypeMismatchException) {
            jsonObject.put("status", 505);
            jsonObject.put("message", "??????????????????:" + e.getMessage());
            e.printStackTrace();
        } else {
            jsonObject.put("status", 500);
            jsonObject.put("message", "???????????????????????????????????????");
            e.printStackTrace();
        }
        return jsonObject;
    }
}
