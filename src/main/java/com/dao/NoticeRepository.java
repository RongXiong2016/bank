package com.dao;

import com.domain.Notice;
import com.domain.User;
import com.domain.UserProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface NoticeRepository extends JpaRepository<Notice, Long> {

}

