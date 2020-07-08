package com.shiro.example.system.repository;

import com.shiro.example.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhouwenquan
 * @date 2020/7/3 14:05
 * description
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    int countByUsername(String username);
}
