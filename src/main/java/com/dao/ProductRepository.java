package com.dao;

import com.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 理财产品Dao
 * @author 范正荣
 */
public interface ProductRepository extends JpaSpecificationExecutor<Product>,JpaRepository<Product, Long> {

}
