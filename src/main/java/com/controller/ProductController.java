package com.controller;

import com.domain.Product;
import com.domain.User;
import com.service.ProductService;
import com.util.CommonUtils;
import com.vo.ProductVO;
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
import java.util.List;
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


    @RequestMapping(value = "show")
    public String show(Model model){
        model.addAttribute("product", new ProductVO());
        return "/product/list";
    }


    /**
     * 1.产品期限
     * 2.产品风险等级 谨慎型产品(R1)、稳健型产品(R2)、平衡型产品(R3)、进取型产品(R4)、激进型产品(R5)
     * 3.类型
     * 4.产品关键字
     * 5.销售状态 在售 即将发售
     * 6.分页
     **/
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit, HttpServletRequest request) {

        String name = request.getParameter("name");
        String term = request.getParameter("term");
        String riskLevel = request.getParameter("riskLevel");
        String type = request.getParameter("type");
        String saleStatus = request.getParameter("saleStatus");
        Pageable pageable = new PageRequest(page - 1, limit);

        //Page<Product> products = productService.list(name, term, riskLevel, type, saleStatus, pageable);
        Page<Product> products = productService.findAll(pageable);
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
        product.setTerm(CommonUtils.getDays(product.getEnd_sale_time(),product.getRan_out_time()));
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


    @RequestMapping("/delete")
    public String delete(Long id) {
        productService.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String edit(Model model, Long id) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);
        return "/product/edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody
    Map edit(@ModelAttribute(value = "product") Product product) {

        productService.edit(product);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }

    @RequestMapping(value="/detail", method = RequestMethod.GET)
    public String detail(Model model,HttpServletRequest request){
        String productCode = request.getParameter("productCode");
        Product product = productService.findByProductByProductCode(productCode);
        model.addAttribute("product", product);
        model.addAttribute("code","0");
        return "/product/details";
    }




}
