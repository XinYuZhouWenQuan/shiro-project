package com.back.service;

import com.alibaba.fastjson.JSONObject;
import com.back.response.Response;
import com.back.response.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author zhouwenquan
 * @date 2020/7/23 15:09
 * description
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public Response login(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResultUtil.fail("缺少必要参数");
        }
        //当前登录的用户
        Subject subject = SecurityUtils.getSubject();
        //如果这个用户没有登录,进行登录功能
        if (!subject.isAuthenticated()) {
            try {
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                subject.login(token);
            } catch (UnknownAccountException e) {
                return ResultUtil.fail("此账号不存在");
            } catch (IncorrectCredentialsException e) {
                return ResultUtil.fail("用户名或者密码错误，请重试！");
            } catch (LockedAccountException e) {
                return ResultUtil.fail("该账号已被锁定，请联系管理员！");
            } catch (AuthenticationException e) {
                return ResultUtil.fail("未知错误，请联系管理员！");
            }
        }
        return ResultUtil.success();
    }
}
