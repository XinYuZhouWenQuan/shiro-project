package com.back.filter;

import com.back.entity.User;
import com.back.repository.PermissionRepository;
import com.back.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:06
 * description
 */
@Slf4j
@Component
public class UrlPathMatchingFilter extends PathMatchingFilter {

    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private PermissionService permissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String requestUrl = getPathWithinApplication(request);
        System.err.println("请求的url :"+requestUrl);

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            //如果没有登录, 直接返回true进入登录流程
            log.info("请登录");
            UnauthorizedException exception = new UnauthorizedException("请登录");
            subject.getSession().setAttribute("exception",exception);
            WebUtils.issueRedirect(request, response, "/shiro/login");
            return false;
        }
        
        // 看看这个路径权限里有没有维护，如果没有维护，一律放行(也可以改为一律不放行)
        int needInterceptor = permissionRepository.countByUrl(requestUrl);
        if (needInterceptor <= 0) {
            return true;
        } else {
            boolean hasPermission = false;
            User user = (User) subject.getPrincipal();
            List<String> permissionUrls = permissionService.listPermissionUrls(user);
            if (CollectionUtils.isNotEmpty(permissionUrls)) {
                for (String url : permissionUrls) {
                    //表示当前用户拥有这个url的访问权限
                    if (url.equals(requestUrl)) {
                        hasPermission = true;
                        break;
                    }
                }
            }

            if (hasPermission) {
                return true;
            } else {
                UnauthorizedException exception = new UnauthorizedException("当前用户没有访问路径 " + requestUrl + " 的权限");
                subject.getSession().setAttribute("exception",exception);
                WebUtils.issueRedirect(request, response, "/unauthorized");
                return false;
            }
        }
    }
}
