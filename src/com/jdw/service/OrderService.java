package com.jdw.service;


import com.jdw.dao.BookDAO;
import com.jdw.dao.CartItemDAO;
import com.jdw.dao.OrderDAO;
import com.jdw.dao.OrderItemDAO;
import com.jdw.pojo.*;

import java.sql.SQLException;
import java.util.*;

@SuppressWarnings({"all"})
public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();

    private CartItemDAO cartItemDAO = new CartItemDAO();

    private BookDAO bookDAO = new BookDAO();

    public String createOrder(Cart cart, Integer user_id) throws Exception {
        for (Integer integer : cart.getItems().keySet()) {
            cartItemDAO.saveCartItem(cart.getItems().get(integer));
        }
        String order_id = UUID.randomUUID().toString();
        orderDAO.saveOrder(new Order(order_id, new Date(), cart.getTotalPrice(), 0, user_id));
        Set<Map.Entry<Integer, CartItem>> entries = cart.getItems().entrySet();
        for (Map.Entry<Integer, CartItem> entry : entries) {
            CartItem cartItem = entry.getValue();
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), order_id);
            orderItemDAO.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDAO.selectBookById(cartItem.getId());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDAO.updateBook(book);
        }
        cart.clear();
        return order_id;
    }

    public List<Order> showAllOrders() throws SQLException {
        return orderDAO.queryOrders();
    }

    public void sendOrder(String order_id) throws SQLException {
        orderDAO.changeOrderStatus(order_id, 1);
    }

    public List<OrderItem> showOrderDetail(String order_id) throws SQLException {
        return orderItemDAO.queryOrderItemsByOrderId(order_id);
    }

    public Order showMyOrders(Integer user_id) throws SQLException {
        return orderDAO.queryOrderByUserId(user_id);
    }

    public void receiverOrder(String order_id) throws SQLException {
        orderDAO.changeOrderStatus(order_id, 2);
    }

    public Page<Order> page(int pageNo, int pageSize,int userId) throws SQLException {
        Page<Order> page = new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount = orderDAO.queryForPageTotalCount(userId);
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
        List<Order> orders = orderDAO.queryForItems(begin, pageSize,userId);
        page.setItems(orders);
        return page;
    }

    public Page<Order> orderInfo(int pageNo, int pageSize) throws SQLException {
        Page<Order> page = new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount = orderDAO.queryForPageTotalCount();
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
        List<Order> orders = orderDAO.queryForItems(begin, pageSize);
        page.setItems(orders);
        return page;
    }
}
