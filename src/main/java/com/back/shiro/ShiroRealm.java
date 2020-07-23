package com.back.shiro;

import com.back.entity.User;
import com.back.service.PermissionService;
import com.back.service.RoleService;
import com.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:09
 * description
 */
@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //能进入到这里，表示账号已经通过验证了
        //授权对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        log.info("user:{}", user.toString());

        Set<String> rolesSet = new HashSet<>();
        Set<String> permsSet = new HashSet<>();

        //获取用户的角色和权限
        Set<String> roles = roleService.listRoles(user);
        Set<String> permissions = permissionService.listPermission(user);

        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);

        return simpleAuthorizationInfo;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = String.valueOf(token.getPassword());

        User user = userService.selectUserByUsername(username);

        if (user == null) {
            return null;
        }

        //如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
        if(null==user.getPassword() || !user.getPassword().equals(password)){
            throw new AuthenticationException();
        }

        //判断是否为冻结状态
        if ("Y".equals(user.getFrozen())) {
            throw new LockedAccountException();
        }

        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :ShiroRealm
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, password, getName());
        log.info("--------------- 认证完毕！ ---------------");
        return simpleAuthenticationInfo;
    }
}
