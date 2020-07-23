package com.back.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhouwenquan
 * @date 2020/7/23 11:57
 * description 权限表
 */
@Data
@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String permission;

    private String url;

    private int pid;
}
