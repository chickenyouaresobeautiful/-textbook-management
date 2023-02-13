package com.jdw.dao;

import com.jdw.pojo.Book;
import com.jdw.pojo.Order;
import com.mysql.cj.PreparedQuery;

import java.sql.SQLException;
import java.util.List;

public class OrderDAO extends BasicDAO<Order> {
    public int saveOrder(Order order) throws SQLException {
        String sql = "insert into `order` values(?,?,?,?,?)";
        return update(sql, order.getOrder_id(), order.getCreate_time(), order.getPrice(), order.getStatus(), order.getUser_id());
    }

    public List<Order> queryOrders() throws SQLException {
        String sql = "select * from `order`";
        return queryForList(sql, Order.class);
    }

    public int changeOrderStatus(String order_id, Integer status) throws SQLException {
        String sql = "update `order` set status = ? where order_id = ?";
        return update(sql, status, order_id);
    }

    public Order queryOrderByUserId(Integer user_id) throws SQLException {
        String sql = "select * from `order` where user_id = ?";
        return queryForOneList(sql, Order.class, user_id);
    }

    public Integer queryForPageTotalCount(int userId) throws SQLException {
        String sql = "select count(*) from `order` where user_id = ?";
        Number number = (Number) queryForOne(sql, userId);
        return number.intValue();
    }
    public Integer queryForPageTotalCount() throws SQLException {
        String sql = "select count(*) from `order`";
        Number number = (Number) queryForOne(sql);
        return number.intValue();
    }

    public List<Order> queryForItems(int begin, int pageSize, int userId) throws SQLException {
        String sql = "select * from `order` where user_id = ? limit ? ,?";
        return queryForList(sql, Order.class, userId, begin, pageSize);
    }

    public List<Order> queryForItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from `order` limit ? ,?";
        return queryForList(sql, Order.class, begin, pageSize);
    }
}
