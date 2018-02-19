package com.dao;

import com.domain.Project;
import com.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author 范正荣
 * @Date 2018/2/18 0018 16:06.
 */
public interface ProjectRepository extends PagingAndSortingRepository<Project,Long> {

    Page<Project> findAllByNameLike(String name, Pageable pageable);
}
