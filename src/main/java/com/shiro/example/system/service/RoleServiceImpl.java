package com.shiro.example.system.service;

import com.shiro.example.system.entity.Role;
import com.shiro.example.system.repository.RoleRepository;
import com.shiro.example.system.util.ResUtils;
import com.shiro.example.system.util.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author zhouwenquan
 * @date 2020/7/7 15:32
 * description
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Response list() {
        List<Role> all = roleRepository.findAll();
        return CollectionUtils.isNotEmpty(all) ? ResUtils.success(all) : ResUtils.fail();
    }

    @Override
    public Response save(Role role) {
        submit(role);
        return ResUtils.success();
    }

    @Override
    public Response update(Role role) {
        submit(role);
        return ResUtils.success();
    }

    @Override
    public Response find(int id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.map(ResUtils::success).orElseGet(ResUtils::fail);
    }

    @Override
    public Response delete(int id) {
        roleRepository.deleteById(id);
        return ResUtils.success();
    }

    private void submit(Role role){
        roleRepository.save(role);
    }
}
