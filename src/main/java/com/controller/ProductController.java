package com.controller;

import com.domain.Product;
import com.domain.User;
import com.service.ProductService;
import com.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 范正荣
 * @Date 2018/4/1 0001 21:32.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    /**
     * 1.产品期限
     * 2.产品风险等级 谨慎型产品(R1)、稳健型产品(R2)、平衡型产品(R3)、进取型产品(R4)、激进型产品(R5)
     * 3.类型
     * 4.产品关键字
     * 5.销售状态
     * 6.分页
     **/
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit, HttpServletRequest request) {

        String name = request.getParameter("name");
        String term = request.getParameter("term");
        String riskLevel = request.getParameter("riskLevel");
        String type = request.getParameter("type");
        String saleStatus = request.getParameter("saleStatus");
        Pageable pageable = new PageRequest(page - 1, limit);

        Page<Product> products = productService.list(name, term, riskLevel, type, saleStatus, pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", products.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("product", new Product());
        return "product/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    Map add(@ModelAttribute(value = "product") Product product) {
        product.setProductCode(CommonUtils.getProductCode());
        productService.save(product);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }


    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit
                             ) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<Product> products = productService.findAll(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", products.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    @RequestMapping("/list-admin")
    public String list(){
        return "/product/list-admin";
    }



}
