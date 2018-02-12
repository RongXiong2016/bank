package com.bank;

import com.BankApplication;
import com.dao.UserRepository;
import com.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BankApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class ApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Rollback(true)
    @Test
    public void test() throws Exception {
        userRepository.save(new User("AAA", "aa@qq.com"));
        userRepository.save(new User("BBB", "aa@qq.com"));
        userRepository.save(new User("CCC", "aa@qq.com"));


        User user = userRepository.findByName("AAA");
        System.out.println(user.getId() + user.getName() + user.getEmail());

    }
}
