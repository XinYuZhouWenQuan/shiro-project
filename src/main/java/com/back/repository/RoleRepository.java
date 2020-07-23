package com.back.repository;

import com.back.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:41
 * description
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
