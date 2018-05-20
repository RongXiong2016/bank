package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 范正荣
 * @Date 2018/5/20 0020 21:29.
 */
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @RequestMapping("/list")
    public String list(){
        return "/notice/list";
    }
}
