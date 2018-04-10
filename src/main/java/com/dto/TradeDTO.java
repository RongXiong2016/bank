package com.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Value
public class TradeDTO {

    private Long trade_id;

    private String trade_no;

    private String user_name;

    private String product_name;

    private String trade_time;

    /**
     * 购买金额
     */
    private String amount;

    /**
     * 预期收益
     */
    private String income;



}
