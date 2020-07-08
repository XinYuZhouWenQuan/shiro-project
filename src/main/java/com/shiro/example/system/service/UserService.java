package com.shiro.example.system.service;

import com.shiro.example.system.entity.User;
import com.shiro.example.system.util.Response;

/**
 * @author zhouwenquan
 * @date 2020/7/6 18:01
 * description
 */
public interface UserService {

    Response list();

    Response update(User user);

    Response save(User user);

    Response delete(int id);
}
