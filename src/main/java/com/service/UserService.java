package com.service;

import com.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> getUserList();

    User findUserById(Long id);

    void edit(User user);

    void delete(Long id);

    void save(User user);

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

}
