package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @author 范正荣
 * @date   2018.03.27
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -2332275348019972760L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "account")
    private String account;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "create_time")
    private Date createTime;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
            name="user_role",
            joinColumns= {@JoinColumn(name="user_id", referencedColumnName="user_id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="role_id")})
    private Set<Role> roles;




}
