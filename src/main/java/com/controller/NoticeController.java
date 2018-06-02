package com.controller;

import com.domain.Notice;
import com.domain.User;
import com.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 范正荣
 * @Date 2018/5/20 0020 21:29.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @RequestMapping("/list")
    public String list(){
        return "/notice/list";
    }

    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Notice> notices = noticeService.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", notices.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    @RequestMapping("/show")
    public String show(){
        return "/notice/show";
    }
}
