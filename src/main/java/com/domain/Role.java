package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 * @author 范正荣
 * @Date 2018/3/17 0017 11:34.
 */
@Entity
@Getter
@Setter
@ToString
public class Role {
    private Long id;
    private String name;
}
