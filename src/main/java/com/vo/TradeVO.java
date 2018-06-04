package com.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.math.BigDecimal;

@Getter
@Setter
public class TradeVO {
    private Long id;
    private String product_name;
    private String user_name;
    private BigDecimal amount;
    private String trade_time;
    private BigDecimal income;
    private String start_sale_time;
    private String end_sale_time;
    private String ran_out_time;

}
