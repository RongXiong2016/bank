package com.controller;

import com.dto.Login;
import com.github.abel533.echarts.Data;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.json.GsonUtil;
import com.github.abel533.echarts.series.Bar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;

/**
 * @author 范正荣
 * @Date 2018/2/13 0013 13:42.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    /**
     * 后台首页
     *
     * @return
     */
    @RequestMapping("admin")
    public String admin() {
        return "index";
    }

    @RequestMapping()
    public String index() {
        return "/user/index";
    }

    @RequestMapping("/testEchart")
    public String testEchart() {
        return "/test/echartsDemo/demo-3";
    }

    @RequestMapping("getData")
    @ResponseBody
    public String getData(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json");
        System.out.println("=============getData==============");
        Option option = new Option();
        //1.设置标题基本信息
        option.title("Echart前后台交互")
                .tooltip(Trigger.axis)
                .legend("工时统计");
        //2.横轴
        option.xAxis(new CategoryAxis().data("1月", "2月", "3月", "4月", "5月", "6月", "7月"));
        //3.纵轴
        option.yAxis(new ValueAxis());

        //4.柱状数据
        Bar bar = new Bar("工时统计");
        bar.data(100, 200, 300, 400, 500, 300, 200);
        option.series(bar);

        String jsonVal = GsonUtil.format(option);
        System.out.println(jsonVal);
        return jsonVal;
    }
}
