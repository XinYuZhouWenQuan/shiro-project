package com.back.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhouwenquan
 * @date 2020/7/23 11:32
 * description 用户表
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String password;

    private String frozen;
}
