package com.shiro.example.system.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhouwenquan
 * @date 2020/7/1 10:51
 * description
 */
@Data
@Entity
@Table(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue
    private int id;

    private int userId;

    private int roleId;

}
