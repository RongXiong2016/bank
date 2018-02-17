package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 范正荣
 * @Date 2018/2/13 0013 13:42.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    /**
     * 后台首页
     * @return
     */
    @RequestMapping("/admin")
    public String admin(){
        return "index";
    }

    @RequestMapping()
    public String index(){
        return "/user/index";
    }
}
