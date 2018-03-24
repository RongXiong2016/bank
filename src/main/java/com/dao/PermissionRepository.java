package com.dao;

import com.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 范正荣
 * @Date 2018/3/17 0017 14:19.
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
