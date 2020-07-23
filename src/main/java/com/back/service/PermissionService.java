package com.back.service;

import com.back.entity.User;

import java.util.List;
import java.util.Set;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:37
 * description
 */
public interface PermissionService {

    /**
     * 获取用户所有拥有的url
     */
    List<String> listPermissionUrls(User user);

    /**
     * 获取用户拥有的权限
     */
    Set<String> listPermission(User user);
}
