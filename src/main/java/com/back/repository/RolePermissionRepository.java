package com.back.repository;

import com.back.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:41
 * description
 */
public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {

    List<RolePermission> findByRoleId(int roleId);

}
