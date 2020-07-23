package com.back.service;

import com.back.entity.Role;
import com.back.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:26
 * description
 */
public interface RoleService {

    List<Role> selectRolesByUserId(int userId);

    Set<String> listRoles(User user);
}
