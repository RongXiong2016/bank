package com.projection;


import org.springframework.beans.factory.annotation.Value;

public interface TradeProjection {
    @Value("#{target.trade_id}")
    Long getTradeid();

    @Value("#{target.trade_no}")
    String getTradeno();

    @Value("#{target.username}")
    String getUsername();

    @Value("#{target.productname}")
    String getProductname();

    @Value("#{target.income}")
    String getIncome();

    @Value("#{target.amount}")
    String getAmount();

    @Value("#{target.trade_time}")
    String getTradeTime();
}
