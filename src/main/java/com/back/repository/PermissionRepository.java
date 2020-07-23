package com.back.repository;

import com.back.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:31
 * description
 */
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

    int countByUrl(String url);

}
