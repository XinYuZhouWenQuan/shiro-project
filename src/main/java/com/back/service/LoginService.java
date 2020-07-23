package com.back.service;

import com.alibaba.fastjson.JSONObject;
import com.back.response.Response;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:09
 * description
 */
public interface LoginService {

    Response login(JSONObject jsonObject);
}
