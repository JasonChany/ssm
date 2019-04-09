package com.itheima.ssm.service;

import com.itheima.ssm.domain.Orders;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/2 18:58
 * @Description:
 *  订单管理Service
 */
public interface IOrdersService {
    /**
     * 查询所所有订单信息
     * @return
     * @param page
     * @param pageSize
     */
    List<Orders> findAll(int page, int pageSize);

    /**
     * 查询订单详情信息
     * @param ordersId
     * @return
     */
    Orders findById(String ordersId);
}
