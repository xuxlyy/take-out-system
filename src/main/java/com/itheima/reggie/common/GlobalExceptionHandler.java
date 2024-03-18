package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 */
//通知 指定拦截哪些Controller
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody//返回JSON格式数据
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 异常处理方法
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)//表明此方法处理此类异常
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        //在日志中记录异常信息
        log.error(ex.getMessage());
        if(ex.getMessage().contains("Duplicate entry")){//进一步提示
            String[] split = ex.getMessage().split(" ");
            String msg = "账号" + split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

    /**
     * 自定义异常的处理方法
     * @param ex
     * @return
     */
    @ExceptionHandler(CustomException.class)//表明此方法处理此类异常
    public R<String> exceptionHandler(CustomException ex){
        //在日志中记录异常信息
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }
}
