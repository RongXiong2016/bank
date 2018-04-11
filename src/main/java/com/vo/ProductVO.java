package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @author 范正荣
 * @Date 2018/4/11 0011 20:54.
 */
@Getter
@Setter
public class ProductVO {
    private Long id;

    private String productCode;

    private String name;

    private String type;

    private String status;

    private String currency;

    private String riskLevel;

    private BigDecimal returnRate;

    private String start_sale_time;

    private String end_sale_time;

    private String ran_out_time;

    private String description;

    private Long projectId;

    private BigDecimal price;

    private String term;
}
