package com.jdw.dao;

import com.jdw.pojo.Book;
import com.jdw.pojo.CartItem;

import java.sql.SQLException;
import java.util.List;

public class CartItemDAO extends BasicDAO<CartItem> {
    public List<CartItem> queryCartItemsByClassId(Integer classId) throws SQLException {
        String sql = "select * from cart_item where classId = ?";
        return queryForList(sql, CartItem.class, classId);
    }

    public int saveCartItem(CartItem cartItem) throws SQLException {
        String sql = "insert into cart_item values(null,?,?,?,?,?)";
        return update(sql, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), cartItem.getClassId());
    }

    public int deleteCartItemById(int id) throws SQLException {
        String sql = "delete from cart_item where id = ?";
        return update(sql, id);
    }

    public int updateCartItem(CartItem cartItem) throws SQLException {
        String sql = "update cart_item set name = ?,count = ?,price = ?,totalPrice = ?,classId = ?";
        return update(sql, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), cartItem.getClassId());
    }

    public Integer queryForPageTotalCount(int classId) throws SQLException {
        String sql = "select count(*) from cart_item where classId = ?";
        Number number = (Number) queryForOne(sql, classId);
        return number.intValue();
    }
    public Integer queryForPageTotalCount() throws SQLException {
        String sql = "select count(*) from cart_item";
        Number number = (Number) queryForOne(sql);
        return number.intValue();
    }

    public List<CartItem> queryForItems(int begin, int pageSize, int classId) throws SQLException {
        String sql = "select * from cart_item where classId = ? limit ? ,?";
        return queryForList(sql, CartItem.class, classId, begin, pageSize);
    }

    public List<CartItem> queryForItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from cart_item limit ? ,?";
        return queryForList(sql, CartItem.class, begin, pageSize);
    }
}
