package com.service.impl;

import com.dao.NoticeRepository;
import com.domain.Notice;
import com.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    public void add(Notice notice) {

    }

    @Override
    public Page<Notice> findAll(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }
}
