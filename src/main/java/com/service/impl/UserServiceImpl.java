package com.service.impl;

import com.dao.TradeRepository;
import com.dao.UserRepository;
import com.domain.Product;
import com.domain.Trade;
import com.domain.User;
import com.service.UserService;
import com.util.CommonUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TradeRepository tradeRepository;


    @Override
    public List<User> getUserList() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void edit(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findAllByNameLike(String name, Pageable pageable) {
        name = "%"+name+"%";
        return userRepository.findAllByNameLike(name,pageable);
    }

    @Override
    public boolean hasUser(String username, String password) {
        User user = userRepository.findByName(username);
        if(password.equals(user.getPassword())){
            return true;
        }else{
        return false;
        }
    }

    @Override
    public Map<String, Object> buyProuct(User user, Product product) {
        Trade trade = new Trade();
        Integer quantity = 1;
        trade.setTrade_no(CommonUtils.getTradeNo());
        trade.setAmount( product.getPrice().multiply(new BigDecimal(quantity.toString())));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //计算收益
        String ran_out_time = product.getRan_out_time();
        String start_time = sdf.format(new Date());
        Integer days =  CommonUtils.getDays(start_time,ran_out_time).intValue();
        //收益=本金*收益率*投资天数/365
        trade.setIncome(trade.getAmount().multiply(product.getReturnRate()).multiply(new BigDecimal((days.toString()))).divide(new BigDecimal(365+"")));
        trade.setUser_id(user.getId());
        trade.setProduct_id(product.getId());

        tradeRepository.save(trade);
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("trade",trade);
        return resMap;
    }

}
