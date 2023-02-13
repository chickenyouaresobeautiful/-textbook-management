package com.jdw.web;

import com.jdw.pojo.*;
import com.jdw.service.BookService;
import com.jdw.service.CartService;
import com.jdw.service.StudentService;
import com.jdw.service.TeacherService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class StudentServlet extends BaseServlet {
    private StudentService studentService = new StudentService();
    private BookService bookService = new BookService();
    private CartService cartService = new CartService();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int classId = WebUtils.parseInt(req.getParameter("classId"), 0);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<CartItem> page = cartService.page(pageNo, pageSize,classId);
        User user = (User) req.getSession().getAttribute("user");
        page.setUrl("studentServlet?action=page&classId=" + user.getClassId());
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/cart/receive.jsp").forward(req, resp);
    }

    protected void studentInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Student> page = studentService.page(pageNo, pageSize);
        User user = (User) req.getSession().getAttribute("user");
        page.setUrl("studentServlet?action=studentInfo");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/student_manager.jsp").forward(req, resp);
    }


    protected void deleteToCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        cartService.deleteCartItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    protected void getStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Student student = studentService.selectStudentById(id);
        req.setAttribute("user", student);
        req.getRequestDispatcher("/pages/manager/user_edit.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        req.setCharacterEncoding("utf-8");
        Student student = WebUtils.copyParameterToBean(req.getParameterMap(), new Student());
        studentService.addStudent(student);
        resp.sendRedirect(req.getContextPath() + "/studentServlet?action=studentInfo&pageNo=" + pageNo);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Student student = WebUtils.copyParameterToBean(req.getParameterMap(), new Student());
        student.setId(WebUtils.parseInt(req.getParameter("id"), 0));
        studentService.updateStudent(student);
        resp.sendRedirect(req.getContextPath() + "/studentServlet?action=studentInfo&pageNo=" + req.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        studentService.deleteStudentById(id);
        resp.sendRedirect(req.getContextPath() + "/studentServlet?action=studentInfo&pageNo=" + req.getParameter("pageNo"));
    }
}
