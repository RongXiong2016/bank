package com.service.impl;

import com.dao.TradeRepository;
import com.dto.TradeDTO;
import com.projection.TradeProjection;
import com.service.TradeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

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

    @Override
    public List<TradeProjection> findAllWithUserAndProductWithProList(Pageable pageable) {
        return tradeRepository.findAllWithUserAndProductWithProList(pageable);
    }
}
