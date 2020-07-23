package com.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.back.response.Response;
import com.back.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouwenquan
 * @date 2020/7/23 13:48
 * description
 */
@RestController
@RequestMapping("/shiro")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping(value = "/login")
    public Response login(@RequestBody JSONObject jsonObject) {
        return loginService.login(jsonObject);
    }

}
