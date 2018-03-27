package com.dao;

import com.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    Page<User> findAllByNameLike(String name, Pageable pageable);


    @Query(value = "select u from User u join u.roles r where r.name=?1")
    List<User> findByRoles(String role);

    @Query(nativeQuery = true, value = "select * from user u " +
            "inner join user_role ur on u.user_id = ur.user_id " +
            "inner join role r on r.role_id = ur.role_id where u.name= ?1 and r.name= ?2 " +
            "limit ?3 ,?4 ;")
    List<User> findByNameAndRolesName(@Param("name1") String userName, @Param("name2")String roleName,
                                      @Param("offset")Integer offset,@Param("q")Integer size);


   /* @Query(nativeQuery = true, value = "select * from user u " +
            "inner join user_role ur on u.user_id = ur.user_id " +
            "inner join role r on r.role_id = ur.role_id where u.name= ?1 and r.name= ?2" +
            "limit ?3,?4 ;")
    List<User> findByNameAndRolesName(@Param("name1") String userName, @Param("name2")String roleName,
                                      @Param("offset")Integer offset,@Param("q")Integer size);*/


}

