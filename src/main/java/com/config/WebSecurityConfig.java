//package com.config;
//
//import com.service.impl.CustomUserService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//
///**
// * @author 范正荣
// * @Date 2018/3/17 0017 11:28.
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
//        return new CustomUserService();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //user Details Service验证
//        auth.userDetailsService(customUserService());
//
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest().authenticated() //任何请求,登录后可以访问
//                .and()
//                .formLogin()
////                .loginPage("/index/admin")
////                .loginPage("/user/login")
//                .failureUrl("/user/login?error")
//                .permitAll() //登录页面用户任意访问
//                .and()
//                .logout().permitAll(); //注销行为任意访问
//
//
//    }
//}
