package com.controller;

import com.domain.Admin;
import com.dto.Login;
import com.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 范正荣
 * @Date 2018/2/17 0017 14:39.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;

    @RequestMapping()
    public String admin(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }
   /* @RequestMapping("/login")
    public String login() {
        return "/admin/login";
    }*/

    @RequestMapping("/list")
    public String list() {
        return "/admin/list";
    }

    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Admin> admins = adminService.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", admins.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    @RequestMapping("/toEdit")
    public String edit(Model model, Long id) {
        Admin admin = adminService.findAdminById(id);
        model.addAttribute("admin", admin);
        return "/admin/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody Map edit(@ModelAttribute(value = "admin") Admin admin) {
        adminService.edit(admin);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin/add";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody
    Map add(@ModelAttribute(value = "admin") Admin admin) {
        admin.setPassword("123456");
        adminService.save(admin);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        adminService.delete(id);
        return "redirect:/list";
    }
}
