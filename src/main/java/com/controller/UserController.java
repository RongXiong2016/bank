package com.controller;

import com.dao.UserRepository;
import com.domain.Product;
import com.domain.Trade;
import com.dto.Login;
import com.domain.User;
import com.dto.Register;
import com.service.UserService;
import com.util.ExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @RequestMapping(value = "/loginout1", method = RequestMethod.GET)
    public String loginOut1(HttpSession session) {
        session.invalidate();
        return "redirect:/index/home";
    }

    /**
     * 加入购物车
     */
    public Map<String, Object> addCart(User user, List<Product> products) {

        return null;
    }


    /**
     * 购买
     */
    @RequestMapping(value = "/buyProduct", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> buyProduct(Product product, HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        User user = (User) session.getAttribute("user");
        resultMap = userService.buyProuct(user, product, new Date());
        resultMap.put("code", "0");
        resultMap.put("success", true);
        return null;
    }


    /**
     * 查看收益
     */
    public Map<String, Object> showEarning(Product product) {
        return null;
    }


    /**
     * 导出excel用户信息
     */
    @RequestMapping("/export")
    public String getUser(HttpServletResponse response) throws Exception{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("统计表");
        ExcelUtils.createUserTitle(workbook,sheet);
        List<User> rows = userService.findAll();

        //设置日期格式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //新增数据行，并且设置单元格数据
        int rowNum=1;
        for(User user:rows){
            HSSFRow row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getIdCard());
            row.createCell(3).setCellValue(user.getAccount());
            row.createCell(4).setCellValue(user.getEmail());
            row.createCell(5).setCellValue(user.getPhone());
            row.createCell(6).setCellValue(user.getAddress());

            HSSFCell cell = row.createCell(7);
            cell.setCellValue(user.getCreateTime()==null?new Date():user.getCreateTime());
            cell.setCellStyle(style);
            rowNum++;
        }

        String fileName = "用户信息.xls";

        //生成excel文件
        ExcelUtils.buildExcelFile(fileName, workbook);

        //浏览器下载excel
        ExcelUtils.buildExcelDocument(fileName,workbook,response);

        return "download excel";
    }

    /**
     * 用户信息审核
     * @return
     */
    @RequestMapping("/review")
    public String review() {
        return "/user/review";
    }

    @RequestMapping(value = "/review1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> review1(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<User> users = userService.findAll(pageable);
        System.out.println(users.getContent().size());
        List<User> users1 = users.getContent().stream()
                            .filter(user ->"N".equals(user.getReviewSatatus()))
                            .collect(Collectors.toList());
        System.out.println(users1.size());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", users1);
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    @RequestMapping(value ="/review2",method = RequestMethod.GET)
    public String review2(long id){
        userService.review(id);
        return "redirect:/review1";
    }

    @RequestMapping(value = "/riskQuestion")
    public String riskQuestion(){
        return "/user/question";
    }

    @RequestMapping(value = "/doRiskTest")
    @ResponseBody
    public Map<String,Object> doRiskTest(@RequestParam(value = "value")String[] value,HttpSession session){
        Map<String,Object> resMap = new HashMap<String,Object>();
        User user = (User)session.getAttribute("user");
        resMap = userService.doRiskTest(user,value);
        resMap.put("code",0);

        return resMap;
    }

    @RequestMapping(value = "/detail")
    public String detail(){
        return "/user/detail";
    }

    @RequestMapping(value = "/myInfo")
    public String myInfo(){
        return "/user/myInfo";
    }

    @RequestMapping(value = "/myTrade")
    public String myTrade(){
        return "/user/myTrade";
    }

    @RequestMapping(value = "/getMyTrade")
    @ResponseBody
    public Map<String,Object> getMyTrade(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Trade> trades = userService.getMyTrade(user);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("data",trades);
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

}
