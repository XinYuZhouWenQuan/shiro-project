package com.back.response;

import lombok.Data;

/**
 * @author zhouwenquan
 * @date 2020/7/23 13:54
 * description
 */
@Data
public class Response<T> {

    private String msg;

    private T data;
}
