package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author 范正荣
 * @Date 2018/3/17 0017 11:29.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "permission")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //权限名称
    @Column(name = "name")
    private String name;
    //权限描述
    @Column(name = "descritpion")
    private String descritpion;
    //授权链接
    @Column(name = "url")
    private String url;
    //父节点id
    @Column(name = "pid")
    private int pid;
}
