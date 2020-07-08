package com.shiro.example.system.controller;

import com.shiro.example.system.entity.Role;
import com.shiro.example.system.service.RoleService;
import com.shiro.example.system.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouwenquan
 * @date 2020/7/7 15:27
 * description
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/list")
    public Response list(){
        return roleService.list();
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Role role){
        return roleService.save(role);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestBody Role role){
        return roleService.update(role);
    }

    @GetMapping(value = "/{id}")
    public Response find(@PathVariable int id){
        return roleService.find(id);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable int id){
        return roleService.delete(id);
    }
}
