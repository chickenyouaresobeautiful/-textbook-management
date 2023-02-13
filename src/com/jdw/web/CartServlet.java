package com.jdw.web;

import com.google.gson.Gson;
import com.jdw.pojo.*;
import com.jdw.service.BookService;
import com.jdw.service.CartService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    private BookService bookService = new BookService();
    private CartService cartService = new CartService();

    protected void ajaxAddToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.selectBookById(id);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setName(book.getName());
        cartItem.setCount(1);
        cartItem.setClassId(0);
        cartItem.setPrice(book.getPrice());
        cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());
        cart.addItem(cartItem);
        req.getSession().setAttribute("cart", cart);

        Map<String, Object> map = new HashMap<>();
        Object totalCount = map.put("totalCount", cart.getTotalCount());
        Object lastName = map.put("lastName", cartItem.getName());
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }

//    protected void addToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int id = WebUtils.parseInt(req.getParameter("id"), 0);
//        Book book = bookService.selectBookById(id);
//        Cart cart = (Cart) req.getSession().getAttribute("cart");
//        if (cart == null) {
//            cart = new Cart();
//        }
//        CartItem cartItem = new CartItem();
//        cartItem.setId(id);
//        cartItem.setName(book.getName());
//        cartItem.setCount(1);
//        cartItem.setPrice(book.getPrice());
//        cartItem.setTotalPrice(cartItem.getPrice() * cartItem.getCount());
//        cart.addItem(cartItem);
//        req.getSession().setAttribute("cart", cart);
//        resp.sendRedirect(req.getHeader("Referer"));
//    }

    protected void cartInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<CartItem> page = cartService.page(pageNo, pageSize);
        page.setUrl("cartServlet?action=cartInfo");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/receive_manager.jsp").forward(req, resp);
    }

    protected void deleteToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int count = WebUtils.parseInt(req.getParameter("count"), 0);
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        cart.updateCount(id, count);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void updateClassId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int classId = WebUtils.parseInt(req.getParameter("classId"),0);
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        cart.updateClassId(id, classId);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
