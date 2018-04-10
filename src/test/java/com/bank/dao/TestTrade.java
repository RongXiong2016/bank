package com.bank.dao;

import com.BankApplication;
import com.dao.ProductRepository;
import com.dao.TradeRepository;
import com.dao.UserRepository;
import com.domain.Product;
import com.domain.User;
import com.dto.TradeDTO;
import com.projection.TradeProjection;
import com.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BankApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class TestTrade {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    TradeRepository tradeRepository;


    @Transactional
    @Test
    public void testBuyProduct(){
        User user = userRepository.findOne(1L);
        Product product = productRepository.findOne(3L);

        Map resMap = userService.buyProuct(user,product);
        System.out.println(resMap.get("trade"));
    }



    @Test
    public void testList2(){
        Pageable pageable = new PageRequest(0,10);
        Page<TradeProjection> trades = tradeRepository.findAllWithUserAndProductWithPro(pageable);
        System.out.println(trades.getTotalElements());
    }

}
