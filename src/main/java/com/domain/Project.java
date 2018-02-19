package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/2/18 0018 15:59.
 */
@Entity
@Getter
@Setter
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "create_admin_id")
    private Long createAdminId;

    @Column(name = "create_time")
    private Date createTime;

}
