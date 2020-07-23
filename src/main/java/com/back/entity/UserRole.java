package com.back.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhouwenquan
 * @date 2020/7/23 14:33
 * description 用户角色关联表
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private int roleId;
}
