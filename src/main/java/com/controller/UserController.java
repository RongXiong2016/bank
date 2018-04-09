package com.controller;

import com.dao.UserRepository;
import com.domain.Product;
import com.dto.Login;
import com.domain.User;
import com.dto.Register;
import com.service.UserService;
import org.springframework.beans.BeanUtils;
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
    @Resource
    UserRepository userRepository;

    @RequestMapping("/list")
    public String list() {
        return "/user/list";
    }


    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit,
                             @RequestParam(value = "key[name]", defaultValue = "") String name) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<User> users = userService.findAllByNameLike(name, pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", users.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }


    @RequestMapping("/toEdit")
    public String edit(Model model, Long id) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        return "/user/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody
    Map edit(@ModelAttribute(value = "user") User user) {
        userService.edit(user);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    Map add(@ModelAttribute(value = "user") User user) {
        user.setCreateTime(new Date());
        user.setPassword("123456");
        userService.save(user);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("login", new Login());
        return "/user/login";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public @ResponseBody
    Map userLogin(@ModelAttribute(value = "login") Login login, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (userService.hasUser(login.getName(), login.getPassword())) {
                resultMap.put("code", "0");
                resultMap.put("msg", "");
                resultMap.put("success", true);
                session.setAttribute("user", userRepository.findByName(login.getName()));
            } else {
                resultMap.put("code", "0");
                resultMap.put("msg", "");
                resultMap.put("success", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("code", "0");
            resultMap.put("msg", "");
            resultMap.put("success", false);
        }
        return resultMap;
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new Register());
        return "/user/register";
    }

    @RequestMapping(value = "/doregister", method = RequestMethod.POST)
    public @ResponseBody
    Map userRegister(@ModelAttribute(value = "register") Register register, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        User user = new User();
        BeanUtils.copyProperties(register, user);
        user.setAddress(register.getProvid() + register.getCityid() + register.getAreaid() + register.getBetterAddress());
        userService.save(user);
        resultMap.put("code", "0");
        resultMap.put("success", true);

        session.setAttribute("user", userRepository.findByName(register.getName()));
        return resultMap;
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginOut(HttpSession session) {
        session.invalidate();
        return "redirect:/admin";
    }

    /**
     * 加入购物车
     */
    public Map<String,Object> addCart(User user, List<Product> products){

        return null;
    }


    /**
     *购买
     */
    @RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
    public  @ResponseBody Map<String,Object> buyProduct(Product product,HttpSession session){
        Map<String, Object> resultMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        resultMap = userService.buyProuct(user,product);
        resultMap.put("code", "0");
        resultMap.put("success", true);
        return null;
    }



    /**
     * 查看收益
     */
    public Map<String,Object> showEarning(Product product){
        return null;
    }


}
