package com.dao;

import com.domain.User;
import com.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    Page<User> findAllByNameLike(String name, Pageable pageable);

    List<User> findUsersByUserRoleList(Long roleId);
}
