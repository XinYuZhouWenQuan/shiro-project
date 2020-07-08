package com.shiro.example.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhouwenquan
 * @date 2020/3/11 14:25
 * description
 */
@Data
@ToString
@Entity
@Table(name = "permission")
@AllArgsConstructor
public class Permissions {

    @Id
    @GeneratedValue
    private int id;

    /**
     * url地址
     */
    private String url;

    /**
     * url描述
     */
    private String name;

    /**
     * 权限
     */
    private String permission;

}
