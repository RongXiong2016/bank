package com.service.impl;

import com.dao.ProjectRepository;
import com.domain.Project;
import com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 范正荣
 * @Date 2018/2/18 0018 16:14.
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public void edit(Project project) {
        Project project1 = projectRepository.findOne(project.getId());
        project.setCreate_user_id(project.getCreate_user_id());
        project.setCreateTime(project1.getCreateTime());
        projectRepository.save(project);
    }

    @Override
    public void delete(Long id) {
        projectRepository.delete(id);
    }

    @Override
    public Project findOne(Long id) {
        return projectRepository.findOne(id);
    }

    @Override
    public void save(Project project) {
        project.setCreateTime(new Date());
        project.setCreate_user_id(1L);
        projectRepository.save(project);
    }

    @Override
    public Page<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }

    @Override
    public Page<Project> findAllByNameLike(String name, Pageable pageable) {
        return projectRepository.findAllByNameLike(name, pageable);
    }
}
