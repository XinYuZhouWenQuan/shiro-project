package com.shiro.example.system.repository;

import com.shiro.example.system.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhouwenquan
 * @date 2020/7/3 16:11
 * description
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission,Integer> {

    List<RolePermission> findByRoleId(int roleId);

}
