package com.shiro.example.system.service;

import com.shiro.example.system.entity.Permissions;
import com.shiro.example.system.entity.Role;
import com.shiro.example.system.repository.PermissionRepository;
import com.shiro.example.system.util.ResUtils;
import com.shiro.example.system.util.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zhouwenquan
 * @date 2020/7/7 15:42
 * description
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Response list() {
        List<Permissions> all = permissionRepository.findAll();
        return CollectionUtils.isNotEmpty(all) ? ResUtils.success(all) : ResUtils.fail();
    }

    @Override
    public Response save(Permissions permissions) {
        submit(permissions);
        return ResUtils.success();
    }

    @Override
    public Response update(Permissions permissions) {
        submit(permissions);
        return ResUtils.success();
    }

    @Override
    public Response find(int id) {
        Optional<Permissions> permissionOptional = permissionRepository.findById(id);
        return permissionOptional.map(ResUtils::success).orElseGet(ResUtils::fail);
    }

    @Override
    public Response delete(int id) {
        permissionRepository.deleteById(id);
        return ResUtils.success();
    }

    private void submit(Permissions permissions){
        permissionRepository.save(permissions);
    }
}
