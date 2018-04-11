package com.dao;

import com.domain.Trade;
import com.dto.TradeDTO;
import com.projection.TradeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    /*@Query(value = "SELECT t.trade_no,u.name as user_name,p.name as product_name,t.amount,t.income " +
            "               FROM trade t " +
            "       inner join user u on t.user_id = u.user_id " +
            "       inner join product p on t.product_id=p.product_id ORDER BY ?#{#pageable}",

            countQuery = "SELECT count(*) FROM trade ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<TradeDTO> findAllWithUserAndProduct(Pageable pageable);*/


    @Query(value = "SELECT t.trade_id,t.trade_no,u.name as username,p.name as productname,t.amount,t.income,t.trade_time FROM trade t inner join user u on t.user_id = u.user_id inner join product p on t.product_id=p.product_id ORDER BY ?#{#pageable}",
            countQuery = "SELECT count(*) FROM trade ORDER BY ?#{#pageable}",
            nativeQuery = true)
    Page<TradeProjection> findAllWithUserAndProductWithPro(Pageable pageable);


}
