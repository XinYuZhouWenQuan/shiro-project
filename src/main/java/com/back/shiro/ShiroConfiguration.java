package com.back.shiro;

import com.back.filter.UrlPathMatchingFilter;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:15
 * description
 */
@Configuration
public class ShiroConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAap = new DefaultAdvisorAutoProxyCreator();
        defaultAap.setProxyTargetClass(true);
        return defaultAap;
    }

    //将自己的验证方式加入容器
    @Bean
    public ShiroRealm myShiroRealm() {
        return new ShiroRealm();
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        return securityManager;
    }

    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        System.out.println("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/shiro/login");
        // 登录成功后要跳转的链接(没用,在js中跳转了)
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //访问权限配置
        filtersMap.put("requestURL", getURLPathMatchingFilter());

        shiroFilterFactoryBean.setFilters(filtersMap);

        /* 配置映射关系*/
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/shiro/login", "anon");
        filterChainDefinitionMap.put("/index", "authc");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/updateSelf", "authc");
        filterChainDefinitionMap.put("/updatePswd", "authc");
        filterChainDefinitionMap.put("/mypermission", "authc");
        filterChainDefinitionMap.put("/shiro/test", "authc");
        filterChainDefinitionMap.put("/online", "authc");
        filterChainDefinitionMap.put("/role", "authc");
        filterChainDefinitionMap.put("/Roleassignment", "authc");
        filterChainDefinitionMap.put("/permissionlist", "authc");
        filterChainDefinitionMap.put("/PermissionAssignment", "authc");

        /*加入自定义过滤器*/
        filterChainDefinitionMap.put("/**", "authc");
        //下面的配置路径 都需要在上面配置 authc 否则访问不到filter
        filterChainDefinitionMap.put("/shiro/test","requestURL");
        filterChainDefinitionMap.put("/list", "requestURL");
        filterChainDefinitionMap.put("/role", "requestURL");
        filterChainDefinitionMap.put("/Roleassignment", "requestURL");
        filterChainDefinitionMap.put("/permissionlist", "requestURL");
        filterChainDefinitionMap.put("/PermissionAssignment", "requestURL");


        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     *   访问 权限 拦截器
     * @return
     */
    public UrlPathMatchingFilter getURLPathMatchingFilter() {
        return new UrlPathMatchingFilter();
    }
}
