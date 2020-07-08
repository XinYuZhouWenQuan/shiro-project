package com.shiro.example.system.repository;

import com.shiro.example.system.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhouwenquan
 * @date 2020/7/3 15:50
 * description
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
}
