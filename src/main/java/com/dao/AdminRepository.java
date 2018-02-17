package com.dao;

import com.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 范正荣
 * @Date 2018/2/17 0017 14:33.
 */
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
