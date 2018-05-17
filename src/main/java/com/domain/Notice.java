package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/5/17 0017 21:44.
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "password")
    private String password;

    @Column(name = "trade_sn")
    private String tradeSn;

    @Column(name = "create_user_id")
    private Long createUserId;

    @Column(name = "create_time")
    private Date createTime;
}
