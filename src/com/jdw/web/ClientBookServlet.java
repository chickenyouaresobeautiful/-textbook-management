package com.jdw.web;

import com.jdw.pojo.Book;
import com.jdw.pojo.Page;
import com.jdw.service.BookService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookService();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("clientBookServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/order/order_menu.jsp").forward(req, resp);
    }

}
