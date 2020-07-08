package com.shiro.example.system.controller;

import com.shiro.example.system.entity.User;
import com.shiro.example.system.service.LoginService;
import com.shiro.example.system.shiro.JWTUtil;
import com.shiro.example.system.util.ResUtils;
import com.shiro.example.system.util.Response;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouwenquan
 * @date 2020/3/11 15:21
 * description
 */
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService service;

    @PostMapping(value = "/login")
    public Response login(@RequestBody User user){
        logger.info("user:{}",user);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        token.setRememberMe(true);
        return ResUtils.success(subject.getSession().getId().toString());
    }

    @GetMapping(value = "/jwt/login")
    public String jwtLogin(@RequestParam String username, @RequestParam String password){
        User userBean = service.getUserByName(username);
        if (userBean.getPassword().equals(password)){
            String str = JWTUtil.sign(username, password);
            return str;
        }
        return null;
    }

    /**
     * 注解验证角色和权限
     * @return
     */
    @GetMapping("/test")
    public String index(){
        return "我进来了";
    }

    @GetMapping("/token")
    public String getToken(){
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        System.err.println("我返回的是"+principal);
        return principal;
    }

    @GetMapping("/noToken")
    public String noToken(@RequestParam String token){

        String t = JWTUtil.getUsername(token);
        System.err.println(t);

        return t;
    }

    @GetMapping(value = "/loginOverTime")
    public Response loginOverTime(){
        return ResUtils.fail();
    }
}