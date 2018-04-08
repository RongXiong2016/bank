package com.service;

import com.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    /**
     * 1.产品期限
     * 2.产品风险等级
     * 3.类型
     * 4.产品关键字
     * 5.销售状态
     * 6.分页
     **/
    Page<Product> list(String name,String term,String riskLevel,String type,String saleStatus,Pageable pageable);
}
