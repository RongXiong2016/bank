package com.controller;

import com.domain.Project;
import com.domain.User;
import com.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 范正荣
 * @Date 2018/2/18 0018 16:27.
 */
@Controller()
@RequestMapping("/project")
public class ProjectController {

    @Resource
    ProjectService projectService;

    @RequestMapping("/list")
    public String list(){
        return "/project/list";
    }

    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Project> projects = projectService.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", projects.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    @RequestMapping("/toEdit")
    public String edit(Model model, Long id) {
        Project project = projectService.findOne(id);
        model.addAttribute("project", project);
        return "/project/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody Map edit(@ModelAttribute(value = "project") Project project,HttpSession session) {
        User user = (User) session.getAttribute("user");
        project.setCreate_user_name(user.getName());
        projectService.edit(project);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("project", new Project());
        return "/project/add";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public @ResponseBody Map add(@ModelAttribute(value = "project") Project project,HttpSession session) {
        User user = (User) session.getAttribute("user");
        project.setCreate_user_name(user.getName());
        project.setCreateTime(new Date());
        projectService.save(project);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        projectService.delete(id);
        return "redirect:/list";
    }


}
