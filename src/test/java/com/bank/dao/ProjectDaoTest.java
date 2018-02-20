package com.bank.dao;

import com.BankApplication;
import com.dao.ProjectRepository;
import com.dao.UserRepository;
import com.domain.Project;
import com.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BankApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class ProjectDaoTest {
    @Autowired
    private ProjectRepository projectRepository;


    @Test
    public void testfindAllJoinAdminId(){
        
    }
}
