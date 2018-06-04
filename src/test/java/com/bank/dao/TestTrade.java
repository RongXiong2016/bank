package com.bank.dao;

import com.BankApplication;
import com.dao.ProductRepository;
import com.dao.TradeRepository;
import com.dao.UserRepository;
import com.domain.Product;
import com.domain.Trade;
import com.domain.User;
import com.dto.TradeDTO;
import com.projection.TradeProjection;
import com.service.UserService;
import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
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

        Map resMap = userService.buyProuct(user,product,new Date());
        System.out.println(resMap.get("trade"));
    }


/*

    @Test
    public void testList2(){
        Pageable pageable = new PageRequest(0,10);
        Collection<TradeProjection> trades = tradeRepository.findAllWithUserAndProductWithPro(pageable);
        System.out.println(trades);
    }
*/


    @Test
    public void supportsProjectionInCombinationWithPagination() {
        Pageable pageable = new PageRequest(0,10);
        Page<TradeProjection> page = tradeRepository
                .findAllWithUserAndProductWithPro(pageable);
        System.out.println(page.getContent().get(0).getTradeid());
    }


    @Test
    public void testUserWithTradeDetail(){
        List<Trade> ts = tradeRepository.findTradesByUserId(1l);
        System.out.println(ts.size());
        for(Trade t:ts){
            System.out.println(t.getUser().getName());
            System.out.println(t.getProduct().getName());
        }
    }
}
