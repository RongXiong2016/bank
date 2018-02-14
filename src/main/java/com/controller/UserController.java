package com.controller;

import com.domain.User;
import com.service.UserService;
import com.sun.org.apache.xml.internal.dtm.DTMDOMException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 范正荣
 * @Date 2018/2/12 0012 14:13.
 * 用户控制器
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/register")
    public String register() {
        return "/user/register";
    }

    @RequestMapping("/login")
    public String login() {
        return "/user/login";
    }

    @RequestMapping("/list")
    public String list() {
        return "/user/list";
    }

    /*@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(Model model) {
        List<User> users = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("/user/list");
        return modelAndView;
    }*/

    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<User> users = userService.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", users.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    /*@RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        Page<User> users = userService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("/list");
        return modelAndView;
    }*/

    @RequestMapping("/toEdit")
    public String edit(Model model, Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "/user/edit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {
        userService.edit(user);
        return "redirect:/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }
}
