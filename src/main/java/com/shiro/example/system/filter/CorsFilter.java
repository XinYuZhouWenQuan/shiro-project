package com.shiro.example.system.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 渡莫凡
 * @date 2019/3/13 12:00
 *
 * 解决跨域问题
 */
@WebFilter(filterName = "corsFilter", urlPatterns = {"/*"})
@Order(1)
public class CorsFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        // 请求的Origin相关
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("*"));
        // 请求的方法相关
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        // 请求头相关
        res.setHeader("Access-Control-Allow-Headers", "Origin, Accept, Pragma, DNT, X-CustomHeader, Keep-Alive,User-Agent, " +
                "X-Requested-With, If-Modified-Since, Cache-Control, Content-Type, Authorization");
        // 跨域时, 如果不加下面的设置, 只能拿到
        // Cache-Control、Content-Language、Content-Type、Expires、Last-Modified、Pragma
        // 以上6个基本值, 其他需要得到的要放入下面的设置中
        res.setHeader("Access-Control-Expose-Headers", "Origin, Accept, Pragma, DNT, X-CustomHeader, Keep-Alive,User-Agent, " +
                "X-Requested-With, If-Modified-Since, Cache-Control, Content-Type, Authorization");
        // 允许携带cookie访问
        res.setHeader("Access-Control-Allow-Credentials", "true");
        // 预检请求有效期: 单位秒，在此期间，不用发出另一条预检请求
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setContentType("application/json;charset=utf-8");
        res.setCharacterEncoding("utf-8");

        if (req.getMethod().equals(RequestMethod.OPTIONS.name())) {
            res.setStatus(HttpStatus.OK.value());
            res.getWriter().print(JSONObject.toJSONString("A02令牌过期或错误, 请重新登录"));
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
