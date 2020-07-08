package com.shiro.example.system.shiro;

import com.shiro.example.system.entity.*;
import com.shiro.example.system.repository.PermissionRepository;
import com.shiro.example.system.repository.RolePermissionRepository;
import com.shiro.example.system.repository.RoleRepository;
import com.shiro.example.system.repository.UserRoleRepository;
import com.shiro.example.system.service.LoginService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author zhouwenquan
 * @date 2020/3/11 14:50
 * description
 */
public class MyRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 角色权限和对应权限添加
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        User user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId());
        if (CollectionUtils.isNotEmpty(userRoles)) {
            for (UserRole userRole : userRoles) {
                Optional<Role> roleOptional = roleRepository.findById(userRole.getRoleId());
                roleOptional.ifPresent(role -> {
                    logger.info("{}拥有的角色是:{}", name, role.getRoleName());
                    List<RolePermission> rolePermissions = rolePermissionRepository.findByRoleId(role.getId());
                    if (CollectionUtils.isNotEmpty(rolePermissions)) {
                        for (RolePermission rolePermission : rolePermissions) {
                            Optional<Permissions> permissionsOptional = permissionRepository.findById(rolePermission.getPermissionId());
                            permissionsOptional.ifPresent(permissions -> {
                                logger.info("{}拥有的权限是:{}", name, permissions.getPermission());
                                simpleAuthorizationInfo.addStringPermission(permissions.getPermission());
                            });
                        }
                    }
                });
            }
        }
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //加这一步的目的是在Post请求的时候会先进认证, 然后在到请求
        if (authenticationToken == null) {
            return null;
        }
        String name = authenticationToken.getPrincipal().toString();
        User user = loginService.getUserByName(name);
        if (user == null) {
            //这里会返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken 和 simpleAuthenticationInfo 的信息
            return new SimpleAuthenticationInfo(name, user.getPassword(), getName());
        }
    }
}
