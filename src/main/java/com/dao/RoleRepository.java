package com.dao;

import com.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author 范正荣
 * @Date 2018/3/27 0027 20:13.
 * 角色相关Repository
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String name);
}
