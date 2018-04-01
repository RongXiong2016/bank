package com.api;

import com.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 范正荣
 * @Date 2018/4/1 0001 22:26.
 */
@Controller
@RequestMapping("/api")
public class ApiController {

    @RequestMapping(value = "/getProvData", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> getProvData() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "0");
        return resultMap;
    }
}
