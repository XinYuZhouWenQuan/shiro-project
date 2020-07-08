package com.shiro.example.system.util;

import lombok.Data;

/**
 * @author zhouwenquan
 * @date 2020/7/6 17:42
 * description
 */
@Data
public class Response<T> {

    private String message;

    private T data;

    Response() {
    }

    Response(String message, T data) {
        this.message = message;
        this.data = data;
    }

    Response(String message) {
        this.message = message;
    }
}
