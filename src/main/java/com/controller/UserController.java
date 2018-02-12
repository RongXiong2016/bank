package com.controller;

import com.domain.User;
import com.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }


    @RequestMapping(value = "/list1",method = RequestMethod.GET)
    @ResponseBody
    public Object list1(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        Page<User> users = userService.findAll(pageable);
        model.addAttribute("users",users);
        model.addAttribute("totalCount",users.getTotalElements());
        model.addAttribute("totalPage", users.getTotalElements() / size + 1);
        return userService.findAll(pageable);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "5") Integer size) {
        Pageable pageable = new PageRequest(page, size);
        Page<User> users = userService.findAll(pageable);
        model.addAttribute("users", users);
        model.addAttribute("totalCount",users.getTotalElements());
        model.addAttribute("totalPage", users.getTotalElements()/size + 1);
        return "/user/list";
    }


    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "user/userEdit";
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
