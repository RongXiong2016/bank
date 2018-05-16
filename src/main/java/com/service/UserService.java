package com.service;

import com.domain.Product;
import com.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService{
    List<User> getUserList();

    User findUserById(Long id);

    void edit(User user);

    void delete(Long id);

    void save(User user);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Page<User> findAllByNameLike(String name, Pageable pageable);

    boolean hasUser(String username, String password);

    Map<String,Object> buyProuct(User user, Product product,Date date);
}
