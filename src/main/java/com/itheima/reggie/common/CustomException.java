package com.itheima.reggie.common;

/**
 * 自定义业务类异常类
 */
public class CustomException extends RuntimeException{
    //构造方法
    public CustomException(String msg){//将错误的提示信息传入
        super(msg);
    }
}
