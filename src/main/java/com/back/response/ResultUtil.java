package com.back.response;

import lombok.Data;

/**
 * @author zhouwenquan
 * @date 2020/7/23 13:56
 * description
 */
@Data
public class ResultUtil {

    private final static String SUCCESS = "success";
    private final static String FAIL = "fail";

    public static <T> Response<T> success(){
        Response<T> response = new Response<>();
        response.setMsg(SUCCESS);
        return response;
    }

    public static <T> Response<T> success(T data){
        Response<T> response = new Response<>();
        response.setMsg(SUCCESS);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(T data){
        Response<T> response = new Response<>();
        response.setMsg(FAIL);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(){
        Response<T> response = new Response<>();
        response.setMsg(FAIL);
        return response;
    }
}
