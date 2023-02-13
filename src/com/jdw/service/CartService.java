package com.jdw.service;

import com.jdw.dao.CartItemDAO;
import com.jdw.pojo.Book;
import com.jdw.pojo.CartItem;
import com.jdw.pojo.Page;

import java.sql.SQLException;
import java.util.List;

public class CartService {
    private CartItemDAO cartItemDAO = new CartItemDAO();

    public Page<CartItem> page(int pageNo, int pageSize,int classId) throws SQLException {
        Page<CartItem> page = new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount = cartItemDAO.queryForPageTotalCount(classId);
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        if (pageNo <= 1) {
            pageNo = 1;
        } else if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);
        int begin = (pageNo - 1) * pageSize;
        List<CartItem> cartItems = cartItemDAO.queryForItems(begin, pageSize,classId);
        page.setItems(cartItems);
        return page;
    }

    public Page<CartItem> page(int pageNo, int pageSize) throws SQLException {
        Page<CartItem> page = new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount = cartItemDAO.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0) {
            pageTotal += 1;
        }
        page.setPageTotal(pageTotal);
        if (pageNo <= 1) {
            pageNo = 1;
        } else if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);
        int begin = (pageNo - 1) * pageSize;
        List<CartItem> cartItems = cartItemDAO.queryForItems(begin, pageSize);
        page.setItems(cartItems);
        return page;
    }

    public void deleteCartItem(int id) throws SQLException {
        cartItemDAO.deleteCartItemById(id);
    }
}
