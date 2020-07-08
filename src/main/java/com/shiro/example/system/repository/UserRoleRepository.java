package com.shiro.example.system.repository;

import com.shiro.example.system.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhouwenquan
 * @date 2020/7/3 16:07
 * description
 */
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {

    List<UserRole> findByUserId(int userId);
}
