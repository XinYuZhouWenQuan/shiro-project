package com.shiro.example.system.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhouwenquan
 * @date 2020/3/11 14:24
 * description
 */
@Data
@Entity
@Table(name = "role")
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private int id;

    private String roleName;

    private String roleType;
}
