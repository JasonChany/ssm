package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2019/4/3 17:12
 * @Description:
 */
@Repository
public interface ITravellerDao {
    /**
     * 根据订单ID获取旅客信息
     * @param ordersId
     * @return
     */
    @Select("Select *\n" +
            "  From Traveller\n" +
            " Where Id In (Select Travellerid\n" +
            "                From Order_Traveller\n" +
            "               Where Orderid = #{ordersId})")
    List<Traveller> findByOrdersId(String ordersId);
}
