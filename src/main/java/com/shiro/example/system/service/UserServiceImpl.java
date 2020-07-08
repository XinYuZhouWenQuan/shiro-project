package com.shiro.example.system.service;

import com.shiro.example.system.entity.User;
import com.shiro.example.system.repository.UserRepository;
import com.shiro.example.system.util.ResUtils;
import com.shiro.example.system.util.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhouwenquan
 * @date 2020/7/6 18:01
 * description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Response list() {
        List<User> all = userRepository.findAll();
        return CollectionUtils.isNotEmpty(all) ? ResUtils.success(all) : ResUtils.fail();
    }

    @Override
    public Response update(User user) {
        userRepository.save(user);
        return ResUtils.success();
    }

    @Override
    public Response save(User user) {
        int i = userRepository.countByUsername(user.getUsername());
        System.err.println(i);
        if (i > 0) {
            return ResUtils.fail();
        }
        userRepository.save(user);
        return ResUtils.success();
    }

    @Override
    public Response delete(int id) {
        userRepository.deleteById(id);
        return ResUtils.success();
    }
}
