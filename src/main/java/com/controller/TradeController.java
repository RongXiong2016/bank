package com.controller;

import com.dao.ProductRepository;
import com.dao.UserRepository;
import com.domain.Product;
import com.domain.Trade;
import com.domain.User;
import com.projection.TradeProjection;
import com.service.TradeService;
import com.service.UserService;
import com.util.CommonUtils;
import com.vo.TradeVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/trade")
public class TradeController {

    @Resource
    TradeService tradeService;
    @Resource
    ProductRepository productRepository;
    @Resource
    UserRepository userRepository;
    @Resource
    UserService userService;

    @RequestMapping("/list")
    public String list() {
        return "/trade/list";
    }


    @RequestMapping(value = "/list1", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> list(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer limit,
                             @RequestParam(value = "key[name]", defaultValue = "") String name) {
        Pageable pageable = new PageRequest(page - 1, limit);
        Page<TradeProjection> trades = tradeService.findAllWithUserAndProductWithPro(pageable);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", trades.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }

    @RequestMapping("/toAdd")
    public String toAdd(Model model) {
        model.addAttribute("trade", new TradeVO());
        return "trade/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody
    Map add(@ModelAttribute(value = "trade") TradeVO tradeVo) throws ParseException {
        User user =  userRepository.findByName(tradeVo.getUser_name());
        Product product = productRepository.findByName(tradeVo.getProduct_name());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        userService.buyProuct(user,product,sdf.parse(tradeVo.getTrade_time()));
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }
}
