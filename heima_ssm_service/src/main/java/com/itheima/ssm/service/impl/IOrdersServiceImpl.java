package com.itheima.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.ssm.dao.IOrdersDao;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/2 19:00
 * @Description:
 */
@Service
@Transactional
public class IOrdersServiceImpl implements IOrdersService {
    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public List<Orders> findAll(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        return iOrdersDao.findAll();
    }

    @Override
    public Orders findById(String ordersId) {
        return iOrdersDao.findById(ordersId);
    }
}
