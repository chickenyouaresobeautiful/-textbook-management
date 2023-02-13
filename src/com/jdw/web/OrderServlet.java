package com.jdw.web;

import com.jdw.pojo.*;
import com.jdw.service.OrderService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderService();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        String orderId = null;
        orderId = orderService.createOrder(cart, userId);
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");
    }


    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        User user = (User) req.getSession().getAttribute("user");
        Integer userId = user.getId();
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Order> page = orderService.page(pageNo, pageSize,userId);
        page.setUrl("orderServlet?action=page&userId=" + userId);
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }

    protected void orderInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Order> page = orderService.orderInfo(pageNo, pageSize);
        page.setUrl("orderServlet?action=orderInfo");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req, resp);
    }


    protected void showOrderDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String order_id = req.getParameter("id");
        List<OrderItem> orderItems = orderService.showOrderDetail(order_id);
        req.setAttribute("orderItems", orderItems);
        req.getRequestDispatcher("/pages/order/orderDetails.jsp").forward(req, resp);
    }
}
