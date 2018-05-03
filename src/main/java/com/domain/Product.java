package com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author 范正荣
 * @Date 2018/4/7 0007 11:14.
 * 理财产品
 */
@Entity
@Getter
@Setter
@ToString
public class Product implements Serializable {

    private static final long serialVersionUID = 8608574579715433815L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id")
    private Long id;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;

    @Column(name = "currency")
    private String currency;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "return_rate")
    private BigDecimal returnRate;

    @Column(name = "start_sale_time")
    private String start_sale_time;

    @Column(name = "end_sale_time")
    private String end_sale_time;

    @Column(name = "ran_out_time")
    private String ran_out_time;

    @Column(name = "term")
    private Long term;

    @Column(name = "description")
    private String description;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "price")
    private BigDecimal price;

   /* @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "product")
    private Set<Trade> trades = new TreeSet<>();*/

}
