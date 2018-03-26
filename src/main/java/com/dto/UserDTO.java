package com.dto;

import com.domain.Role;
import com.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/3/26 0026 20:52.
 */
@Getter
@Setter
public class UserDTO {

    private Long id;


    private String name;


    private String password;


    private String idCard;


    private String account;


    private String phone;


    private String email;


    private String address;


    private Date createTime;

    private Long r_id;


    private String r_name;
}
