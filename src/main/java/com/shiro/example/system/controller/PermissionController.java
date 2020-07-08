package com.shiro.example.system.controller;

import com.shiro.example.system.entity.Permissions;
import com.shiro.example.system.service.PermissionService;
import com.shiro.example.system.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouwenquan
 * @date 2020/7/7 15:39
 * description
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping(value = "/list")
    public Response list(){
        return permissionService.list();
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Permissions permissions){
        return permissionService.save(permissions);
    }

    @PutMapping(value = "/update")
    public Response update(@RequestBody Permissions permissions){
        return permissionService.update(permissions);
    }

    @GetMapping(value = "/{id}")
    public Response find(@PathVariable int id){
        return permissionService.find(id);
    }

    @DeleteMapping(value = "/{id}")
    public Response delete(@PathVariable int id){
        return permissionService.delete(id);
    }

}
