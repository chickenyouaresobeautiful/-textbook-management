package com.jdw.dao;

import com.jdw.pojo.OrderItem;

import java.sql.SQLException;
import java.util.List;

public class OrderItemDAO extends BasicDAO<OrderItem>{
    public int saveOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "insert into order_item values(?,?,?,?,?,?)";
        return update(sql, orderItem.getId(),orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotal_price(),orderItem.getOrder_id());
    }

    public List<OrderItem> queryOrderItemsByOrderId(String orderId) throws SQLException {
        String sql = "select * from order_item where order_id = ?";
        return queryForList(sql,OrderItem.class,orderId);
    }
}
