package com.jdw.web;

import com.jdw.pojo.*;
import com.jdw.service.BookService;
import com.jdw.service.TeacherService;
import com.jdw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class TeacherServlet extends BaseServlet{
    private TeacherService teacherService = new TeacherService();
    private BookService bookService = new BookService();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo, pageSize);
        page.setUrl("teacherServlet?action=page");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/order/order_menu.jsp").forward(req, resp);
    }

    protected void teacherInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Teacher> page = teacherService.page(pageNo, pageSize);
        User user = (User) req.getSession().getAttribute("user");
        page.setUrl("teacherServlet?action=teacherInfo");
        req.setAttribute("page", page);
        req.getRequestDispatcher("/pages/manager/teacher_manager.jsp").forward(req, resp);
    }

    protected void getTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Teacher teacher = teacherService.selectTeacherById(id);
        req.setAttribute("user", teacher);
        req.getRequestDispatcher("/pages/manager/user_edit.jsp").forward(req, resp);
    }

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        req.setCharacterEncoding("utf-8");
        Teacher teacher = WebUtils.copyParameterToBean(req.getParameterMap(), new Teacher());
        teacherService.addTeacher(teacher);
        resp.sendRedirect(req.getContextPath() + "/teacherServlet?action=teacherInfo&pageNo=" + pageNo);
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher = WebUtils.copyParameterToBean(req.getParameterMap(), new Teacher());
        teacher.setId(WebUtils.parseInt(req.getParameter("id"), 0));
        teacherService.updateTeacher(teacher);
        resp.sendRedirect(req.getContextPath() + "/teacherServlet?action=teacherInfo&pageNo=" + req.getParameter("pageNo"));
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        teacherService.deleteTeacherById(id);
        resp.sendRedirect(req.getContextPath() + "/teacherServlet?action=teacherInfo&pageNo=" + req.getParameter("pageNo"));
    }

}
