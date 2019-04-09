package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/1 21:12
 * @Description:
 *      商品DAO接口
 */
@Repository
public interface IProductDao {
    /**
     * 根据ID查询商品信息
     * @param id
     * @return
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id);
    /**
     * 查询所有商品信息
     * @return
     */
    @Select("select *from product")
    List<Product> findAll();

    /**
     * 添加商品信息
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,DepartureTime,productPrice,productDesc,productStatus)" +
            " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 修改商品信息
     */
    @Select("Update product Set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} Where Id=#{id}")
    void update(Product product);
}
