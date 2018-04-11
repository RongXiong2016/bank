package com.service;

import com.dto.TradeDTO;
import com.projection.TradeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface TradeService {

    Page<TradeProjection> findAllWithUserAndProductWithPro(Pageable pageable);

}
