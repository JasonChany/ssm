package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/1 21:21
 * @Description:
 *      商品信息Service接口
 */

public interface IProductService {
    /**
     * 查询所有商品信息
     * @return
     */
    List<Product> findAll();

    /**
     * 添加商品信息
     * @param product
     */
    void save(Product product);


    /**
     * 根据商品ID获取商品信息
     * @param pid
     * @return
     */
    Product findById(String pid);

    /**
     * 修改商品信息
     */
    void update(Product product);
}
