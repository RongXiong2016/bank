package com.service.impl;

import com.dao.ProductRepository;
import com.dao.TradeRepository;
import com.dao.UserRepository;
import com.domain.Product;
import com.domain.Trade;
import com.domain.User;
import com.projection.TradeProjection;
import com.service.TradeService;
import com.util.CommonUtils;
import com.vo.TradeVO;
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
    @Resource
    ProductRepository productRepository;
    @Resource
    UserRepository userRepository;

    @Override
    public Page<TradeProjection> findAllWithUserAndProductWithPro(Pageable pageable) {
        Page<TradeProjection> tradeProjections = tradeRepository.findAllWithUserAndProductWithPro(pageable);
        return tradeProjections;
    }

    @Override
    public void save(TradeVO tradeVo) {
        Trade trade = new Trade();
        String tradeNo = CommonUtils.getTradeNo();
        User user =  userRepository.findByName(tradeVo.getUser_name());
        Product product = productRepository.findByName(tradeVo.getProduct_name());
        trade.setProduct(product);
        trade.setUser(user);
        trade.setTrade_no(tradeNo);
        trade.setAmount(tradeVo.getAmount());
        trade.setTrade_time(tradeVo.getTrade_time());
        tradeRepository.save(trade);
    }

}
