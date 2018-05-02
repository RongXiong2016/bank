package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/4/7 0007 22:20.
 */
@Entity
@Getter
@Setter
@ToString
public class Trade implements Serializable {

    private static final long serialVersionUID = -3659954773438491776L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="trade_id")
    private Long id;

    @Column(name = "trade_no")
    private String trade_no;
/*
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "product_id")
    private Long product_id;*/

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "income")
    private BigDecimal income;

    @Column(name = "trade_time")
    private String trade_time;

    @ManyToOne
    @JoinColumn(name = "user_id",foreignKey = @ForeignKey(name = "fk_user_fc"))
    @JsonIgnoreProperties("trades")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id",foreignKey = @ForeignKey(name = "fk_product_fc"))
    //@JsonIgnoreProperties("trades")
    @JsonIgnore
    private Product product;

}
