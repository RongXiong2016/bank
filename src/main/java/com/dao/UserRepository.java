package com.dao;

import com.domain.User;
import com.domain.UserRole;
import com.dto.UserDTO;
import com.vo.UserVo;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    Page<User> findAllByNameLike(String name, Pageable pageable);

    @Query(value = "select u,r as r_name from User u join u.roles r ")
    List<UserRole> findRoleAndUser();
}

