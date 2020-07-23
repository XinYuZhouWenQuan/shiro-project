package com.back.repository;

import com.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:14
 * description
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

}
