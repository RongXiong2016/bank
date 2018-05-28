package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 范正荣
 * @Date 2018/5/28 0028 21:30.
 */
@Controller
@RequestMapping("/risk")
public class RiskController {
    @RequestMapping("/show1")
    public String show(){
        return "risk/show";
    }
}
