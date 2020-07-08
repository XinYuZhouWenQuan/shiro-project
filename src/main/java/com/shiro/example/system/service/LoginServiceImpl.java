package com.shiro.example.system.service;

import com.shiro.example.system.entity.User;
import com.shiro.example.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouwenquan
 * @date 2020/3/11 14:28
 * description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByName(String username) {
        return getByName(username);
    }

    /**
     * 模拟数据库查询用户
     * @param username
     * @return
     */
    private User getByName(String username){
        return userRepository.findByUsername(username);
    }
}
