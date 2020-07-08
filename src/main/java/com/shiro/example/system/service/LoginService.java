package com.shiro.example.system.service;

import com.shiro.example.system.entity.User;

/**
 * @author zhouwenquan
 * @date 2020/3/11 14:27
 * description
 */
public interface LoginService {

    User getUserByName(String username);

}
