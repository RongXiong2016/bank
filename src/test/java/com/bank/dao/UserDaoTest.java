package com.bank.dao;

import com.BankApplication;
import com.dao.RoleRepository;
import com.dao.UserRepository;
import com.domain.Role;
import com.domain.User;
import com.domain.UserProjection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = BankApplication.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class UserDaoTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

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
        List<User> users = userRepository.findByRoles("USER");
        System.out.println(users.size());
        System.out.println(users.get(0).getName());
    }

    @Test
    public void testInsertUser(){
        Role role = new Role();
        role.setName("柜员");
        roleRepository.save(role);

        Set<Role> roles=new HashSet<Role>();
        roles.add(role);

        User user = new User();
        user.setName("test0327");
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Test
    public void testfindByNameAndRolesName(){

        List<User> users =
                userRepository.findByNameAndRolesName("user","用户",0,20);
        System.out.println(users.size());
        for(User u : users){
           System.out.println(u.getName());
       }
    }

    @Test
    public void test(){
        List<User> users = userRepository.findByName1("user");
        System.out.println(users.size());
    }

    @Test
    public void test2(){
        Collection<UserProjection> collections = userRepository.findProjectedBy();
        System.out.println(collections.size());
    }

}
