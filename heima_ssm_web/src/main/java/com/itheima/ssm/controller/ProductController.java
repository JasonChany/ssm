package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/2 09:59
 * @Description:
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    /**
     * 查询所有商品信息
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<Product> products = iProductService.findAll();
        mv.addObject("productList",products).setViewName("product-list");
        return mv;
    }

    /**
     * 新增商品
     * @param product
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Product product){
        iProductService.save(product);
        return "redirect:findAll.do";
    }

    /**
     * 根据商品ID查询商品信息（用于商品详情或修改商品时数据回显）
     * @param pid
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id") String pid){
        ModelAndView mv = new ModelAndView();
        Product product=iProductService.findById(pid);
        mv.addObject("product",product).setViewName("product-update");
        return mv;
    }

    /**
     * 修改商品信息
     * @return
     */
    @RequestMapping("/update.do")
    public String update(Product product){
        iProductService.update(product);
        return "redirect:findAll.do";
    }

}
