package com.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sun.rmi.runtime.Log;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/4/7 0007 11:14.
 * 理财产品
 */
@Entity
@Getter
@Setter
@ToString
public class Product {
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

    @Column(name = "channel")
    private String channel;

    @Column(name = "risk_level")
    private String riskLevel;

    @Column(name = "return_rate")
    private BigDecimal returnRate;

    @Column(name = "start_sale_time")
    private Date startSaleTime;

    @Column(name = "end_sale_time")
    private Date endSaleTime;

    @Column(name = "start_interest_time")
    private Date startInterestTime;

    @Column(name = "description")
    private String description;

    @Column(name = "project_id")
    private Long projectId;
}
