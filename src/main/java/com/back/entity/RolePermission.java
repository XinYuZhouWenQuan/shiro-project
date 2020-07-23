package com.back.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:34
 * description 角色权限关联表
 */
@Data
@Entity
@Table(name = "role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int roleId;

    private int permissionId;
}
