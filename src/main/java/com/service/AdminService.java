package com.service;

import com.domain.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    List<Admin> getAdminList();

    Admin findAdminById(Long id);

    void edit(Admin admin);

    void delete(Long id);

    void save(Admin admin);

    List<Admin> findAll();

    Page<Admin> findAll(Pageable pageable);
}
