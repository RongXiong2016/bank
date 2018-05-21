package com.service;

import com.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeService {
    void add(Notice notice);

    Page<Notice> findAll(Pageable pageable);
}
