package com.itheima.reggie.common;

/**
 * 基于ThreadLocal封装工具类 用于保存和获取当前用户id
 * 作用范围：在某一个特定线程之内
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    //保存用户id
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    //获取用户id
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
