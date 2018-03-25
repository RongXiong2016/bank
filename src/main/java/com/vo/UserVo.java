package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/2/16 0016 16:12.
 */
@Getter
@Setter
public class UserVo {
    private Long id;

    private String name;

    private String password;

    private String idCard;

    private String account;

    private String phone;

    private String email;

    private String address;

    private Date createTime;

    private String sex;

    private Integer age;

    private String roleName;
}
