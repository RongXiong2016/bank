package com.bank.dao;

import com.BankApplication;
import com.dao.UserRepository;
import com.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BankApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class UserDaoTest {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    //@Rollback(true)
    @Test
    public void testAdd() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setName("testAdd");
        user.setPassword("123456");
        user.setAccount("134324");
        user.setCreateTime(new Date());
        user.setIdCard("332525199510125318");

        userRepository.save(user);

    }
    @Test
    public void testFindNameLike(){
        Pageable pageable = new PageRequest(0, 10);
        Page<User> users = userRepository.findAllByNameLike("%测试%",pageable);
        System.out.println(users.getTotalElements());
    }

    @Test
    public void testfindUsersByUserRoleList(){
        List<User> users = userRepository.findUsersByUserRoleList(1L);
        System.out.println(users.size());
    }
}
