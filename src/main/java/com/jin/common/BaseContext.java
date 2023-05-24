package com.jin.common;

/**
 * @Author xiaojin
 * @Date 2023/5/24 8:34
 */
public class BaseContext {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentName(String name){
        threadLocal.set(name);
    }
    public static String getCurrentName(){
        return threadLocal.get();
    }
}
