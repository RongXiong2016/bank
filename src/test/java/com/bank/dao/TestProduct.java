package com.bank.dao;

import com.BankApplication;
import com.dao.ProductRepository;
import com.domain.Product;
import com.util.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

/**
 * @author 范正荣
 * @Date 2018/4/11 0011 21:39.
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BankApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class TestProduct {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void testAdd(){
        Product product = null;
        for(int i=0;i<10;i++){
            product = new Product();
            product.setProductCode(CommonUtils.getProductCode());
            product.setCurrency("人民币");
            product.setDescription("测试增加理财产品"+i);
            product.setStart_sale_time("2018-04-11");
            product.setEnd_sale_time("2018-04-30");
            product.setRan_out_time("2018-04-17");
            product.setName("测试理财产品"+i);
            product.setPrice(BigDecimal.valueOf(1000));
            product.setProjectId(1L);
            product.setReturnRate(BigDecimal.valueOf(i));
            product.setTerm(CommonUtils.getDays(product.getEnd_sale_time(),product.getRan_out_time()));
            product.setRiskLevel("R1");
            product.setType("保本浮动");

            productRepository.save(product);
        }
    }
}
