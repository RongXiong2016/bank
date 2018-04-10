package com.service.impl;

import com.dao.TradeRepository;
import com.projection.TradeProjection;
import com.service.TradeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 范正荣
 * @date 2018/4/10
 */
@Service
public class TradeServiceImpl implements TradeService {

    @Resource
    TradeRepository tradeRepository;

    @Override
    public Page<TradeProjection> findAllWithUserAndProductWithPro(Pageable pageable) {

        return tradeRepository.findAllWithUserAndProductWithPro(pageable);
    }
}
