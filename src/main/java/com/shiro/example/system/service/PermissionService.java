package com.shiro.example.system.service;

import com.shiro.example.system.entity.Permissions;
import com.shiro.example.system.util.Response;

/**
 * @author zhouwenquan
 * @date 2020/7/7 15:41
 * description
 */
public interface PermissionService {

    Response list();
    Response save(Permissions permissions);
    Response update(Permissions permissions);
    Response find(int id);
    Response delete(int id);

}
