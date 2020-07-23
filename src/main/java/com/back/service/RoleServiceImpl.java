package com.back.service;

import com.back.entity.Role;
import com.back.entity.User;
import com.back.entity.UserRole;
import com.back.repository.RoleRepository;
import com.back.repository.UserRoleRepository;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:27
 * description
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> selectRolesByUserId(int userId) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(userId);
        if (CollectionUtils.isNotEmpty(userRoles)) {
            List<Role> roles = new ArrayList<>();
            for (UserRole userRole : userRoles) {
                Optional<Role> roleOptional = roleRepository.findById(userRole.getRoleId());
                roleOptional.ifPresent(roles::add);
            }
            return roles;
        }
        return null;
    }

    @Override
    public Set<String> listRoles(User user) {
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(userRoles)) {
            Set<String> roles = new HashSet<>();
            for (UserRole userRole : userRoles) {
                Optional<Role> roleOptional = roleRepository.findById(userRole.getRoleId());
                roleOptional.ifPresent(role -> roles.add(role.getRole()));
            }
            return roles;
        }
        return null;
    }
}
