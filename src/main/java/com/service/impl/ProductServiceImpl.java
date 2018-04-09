package com.service.impl;

import com.dao.ProductRepository;
import com.domain.Product;
import com.domain.User;
import com.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 范正荣
 * @date 2018/4/8
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Override
    public Page<Product> list(String name, String term, String riskLevel, String type, String saleStatus,Pageable pageable) {
        pageable = new PageRequest(1,10);
        Specification<Product> specification = new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicatesList = new ArrayList<>();

                if (!StringUtils.isEmpty(name)){
                    Predicate nickNamePredicate = cb.like(root.get("name"), '%'+name+'%');
                    predicatesList.add(nickNamePredicate);
                }

                if (!StringUtils.isEmpty(term)){
                    Predicate namePredicate = cb.equal(root.get("term"), term);
                    predicatesList.add(namePredicate);
                }

                if (!StringUtils.isEmpty(riskLevel)){
                    Predicate namePredicate = cb.equal(root.get("riskLevel"), riskLevel);
                    predicatesList.add(namePredicate);
                }

                if (!StringUtils.isEmpty(type)){
                    Predicate namePredicate = cb.equal(root.get("type"), type);
                    predicatesList.add(namePredicate);
                }

                if (!StringUtils.isEmpty(saleStatus)){
                    Predicate namePredicate = cb.equal(root.get("saleStatus"), saleStatus);
                    predicatesList.add(namePredicate);
                }

                query.orderBy(cb.asc(root.get("name")));

                Predicate[] predicates = new Predicate[predicatesList.size()];
                return cb.and(predicatesList.toArray(predicates));
            }
        };
        return productRepository.findAll(specification,pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public Product findProductById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void edit(Product product) {
        productRepository.save(product);
    }
}
