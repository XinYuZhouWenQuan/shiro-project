package com.back.controller;

import com.alibaba.fastjson.JSONObject;
import com.back.response.Response;
import com.back.response.ResultUtil;
import com.back.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test")
    public Response test(){
        return ResultUtil.success("为什么在这里呢");
    }

}
