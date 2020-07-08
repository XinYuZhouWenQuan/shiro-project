package com.shiro.example.system.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhouwenquan
 * @date 2020/7/1 10:44
 * description 角色权限
 */
@Data
@Entity
@Table(name = "role_permission")
public class RolePermission {

    @Id
    @GeneratedValue
    private int id;

    private int roleId;

    private int permissionId;

}
