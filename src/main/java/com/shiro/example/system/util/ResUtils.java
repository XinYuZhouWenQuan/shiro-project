package com.shiro.example.system.util;

/**
 * @author zhouwenquan
 * @date 2020/7/6 17:55
 * description
 */
public class ResUtils {

    public static <T> Response success(T data){
        return new Response<T>("success",data);
    }

    public static <T> Response success(){
        return new Response<T>("success");
    }

    public static <T> Response fail(){
        return new Response<T>("fail",null);
    }
}
