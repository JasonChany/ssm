package com.itheima.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/2 18:56
 * @Description:
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService iOrdersService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,@RequestParam(name="pageSize",required = true,defaultValue = "4") Integer pageSize){
        ModelAndView mv = new ModelAndView();
        List<Orders> ordersList = iOrdersService.findAll(page,pageSize);
        PageInfo pageInfo = new PageInfo<Orders>(ordersList);
        mv.addObject("pageInfo",pageInfo).setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId){
        ModelAndView mv = new ModelAndView();
        Orders orders=iOrdersService.findById(ordersId);
        mv.addObject("orders",orders).setViewName("orders-show");
        return mv;
    }
}
