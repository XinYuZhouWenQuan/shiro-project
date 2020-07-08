package com.shiro.example.system.controller;

import com.shiro.example.system.entity.User;
import com.shiro.example.system.service.UserService;
import com.shiro.example.system.util.Response;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouwenquan
 * @date 2020/7/6 17:40
 * description
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping(value = "/list")
    public Response list() {
        logger.info("/user/list");
        return userService.list();
    }

    @PostMapping(value = "/update")
    public Response update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping(value = "/delete")
    public Response delete(@RequestParam int id){
        return userService.delete(id);
    }
}
