package com.service;

import com.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author 范正荣
 * @Date 2018/2/18 0018 16:12.
 */
public interface ProjectService {

    void edit(Project project);

    void delete(Long id);

    Project findOne(Long id);

    void save(Project project);

    Page<Project> findAll(Pageable pageable);

    Page<Project> findAllByNameLike(String name,Pageable pageable);


}
