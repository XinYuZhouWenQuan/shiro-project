package com.shiro.example.system.entity;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zhouwenquan
 * @date 2020/4/8 16:48
 * description
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken() {
    }

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
