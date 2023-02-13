package com.jdw.web;

import com.jdw.pojo.Manager;
import com.jdw.pojo.Student;
import com.jdw.pojo.Teacher;
import com.jdw.service.ManagerService;
import com.jdw.service.StudentService;
import com.jdw.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends BaseServlet {
    private ManagerService managerService = new ManagerService();
    private TeacherService teacherService = new TeacherService();
    private StudentService studentService = new StudentService();

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String identity = req.getParameter("identity");
        if (identity == null){
            req.setAttribute("msg", "请选择身份");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
        if (identity.equals("teacher")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Teacher teacher = teacherService.queryByUsername(username, password);
            if (teacher == null) {
                req.setAttribute("msg", "用户名或密码错误");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("user", teacher);
                req.getRequestDispatcher("/pages/user/teacher_login_success.jsp").forward(req, resp);
            }
        }else if (identity.equals("student")){
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            Student student = studentService.queryByUsername(username, password);
            if (student == null) {
                req.setAttribute("msg", "用户名或密码错误");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("user", student);
                req.getRequestDispatcher("/pages/user/student_login_success.jsp").forward(req, resp);
            }
        }
        else {
            String adminId = req.getParameter("username");
            String password = req.getParameter("password");
            Manager manager = managerService.queryAdmin(adminId, password);
            if (manager == null) {
                req.setAttribute("msg", "用户名或密码错误");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                req.getSession().setAttribute("manager", manager);
                req.getRequestDispatcher("/pages/manager/manager.jsp").forward(req, resp);
            }
        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        req.getSession().invalidate();
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
