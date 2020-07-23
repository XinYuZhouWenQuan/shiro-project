package com.back.repository;

import com.back.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:39
 * description
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findByUserId(int userId);

}
