package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author 范正荣
 * @Date 2018/3/17 0017 11:34.
 */
@Entity
@Getter
@Setter
@ToString
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<UserRole> userRoleList;

}
