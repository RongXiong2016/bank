package com.controller;

import com.dto.TradeDTO;
import com.projection.TradeProjection;
import com.service.TradeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")
public class TradeController {

    @Resource
    TradeService tradeService;

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
       // List<TradeProjection> trades = tradeService.findAllWithUserAndProductWithProList(pageable);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", trades.getContent());
        resultMap.put("count", "1000");
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        return resultMap;
    }
}
