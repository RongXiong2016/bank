package com.service;

import com.projection.TradeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TradeService {

    Page<TradeProjection> findAllWithUserAndProductWithPro(Pageable pageable);
}
