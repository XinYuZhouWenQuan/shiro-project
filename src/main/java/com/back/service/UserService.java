package com.back.service;

import com.back.entity.User;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:08
 * description
 */
public interface UserService {

    User selectUserByUsername(String username);

}
