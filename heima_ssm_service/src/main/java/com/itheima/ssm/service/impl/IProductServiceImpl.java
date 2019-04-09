package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.IProductDao;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/2 09:24
 * @Description:
 */
@Service("productService")
@Transactional
public class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao iProductDao;

    @Override
    public List<Product> findAll() {
        return iProductDao.findAll();
    }

    @Override
    public void save(Product product) {
        iProductDao.save(product);
    }

    @Override
    public Product findById(String pid) {
        return iProductDao.findById(pid);
    }

    @Override
    public void update(Product product) {
        iProductDao.update(product);
    }
}
