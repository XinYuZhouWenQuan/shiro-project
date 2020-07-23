package com.back.service;

import com.back.entity.Permission;
import com.back.entity.RolePermission;
import com.back.entity.User;
import com.back.entity.UserRole;
import com.back.repository.PermissionRepository;
import com.back.repository.RolePermissionRepository;
import com.back.repository.UserRoleRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:38
 * description
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 获取用户所有拥有的url
     *
     * @param user
     */
    @Override
    public List<String> listPermissionUrls(User user) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(userRoles)) {
            List<String> urls = new ArrayList<>();
            for (UserRole userRole : userRoles) {
                List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(userRole.getRoleId());
                if (CollectionUtils.isNotEmpty(rolePermissions)) {
                    for (RolePermission rolePermission : rolePermissions) {
                        Optional<Permission> permissionOptional = permissionRepository.findById(rolePermission.getPermissionId());
                        permissionOptional.ifPresent(permission -> urls.add(permission.getUrl()));
                    }
                }
            }
            return urls;
        }
        return null;
    }

    /**
     * 获取用户拥有的权限
     *
     * @param user
     */
    @Override
    public Set<String> listPermission(User user) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(userRoles)) {
            Set<String> permissions = new HashSet<>();
            for (UserRole userRole : userRoles) {
                List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(userRole.getRoleId());
                if (CollectionUtils.isNotEmpty(rolePermissions)) {
                    for (RolePermission rolePermission : rolePermissions) {
                        Optional<Permission> permissionOptional = permissionRepository.findById(rolePermission.getPermissionId());
                        permissionOptional.ifPresent(permission -> permissions.add(permission.getName()));
                    }
                }
            }
            return permissions;
        }
        return null;
    }
}
