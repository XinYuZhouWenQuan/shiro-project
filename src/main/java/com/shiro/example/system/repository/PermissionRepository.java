package com.shiro.example.system.repository;

import com.shiro.example.system.entity.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhouwenquan
 * @date 2020/7/3 15:51
 * description
 */
public interface PermissionRepository extends JpaRepository<Permissions,Integer> {
}
