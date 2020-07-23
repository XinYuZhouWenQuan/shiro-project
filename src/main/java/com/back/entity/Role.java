package com.back.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhouwenquan
 * @date 2020/7/23 11:56
 * description 角色表
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String role;
}
