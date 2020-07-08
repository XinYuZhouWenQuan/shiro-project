package com.shiro.example.system.service;

import com.shiro.example.system.entity.Role;
import com.shiro.example.system.util.Response;

/**
 * @author zhouwenquan
 * @date 2020/7/7 15:32
 * description
 */
public interface RoleService {

    Response list();
    Response save(Role role);
    Response update(Role role);
    Response find(int id);
    Response delete(int id);

}
