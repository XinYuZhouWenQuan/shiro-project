package com.back.service;

import com.back.entity.User;
import com.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:08
 * description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User selectUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
