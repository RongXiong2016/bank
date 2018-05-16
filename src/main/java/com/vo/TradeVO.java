package com.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TradeVO {
    private String product_name;
    private String user_name;
    private BigDecimal amount;
    private String trade_time;

}
