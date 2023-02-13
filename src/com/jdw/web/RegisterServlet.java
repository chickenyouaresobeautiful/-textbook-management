package com.jdw.web;

import com.jdw.pojo.Student;
import com.jdw.pojo.Teacher;
import com.jdw.service.StudentService;
import com.jdw.service.TeacherService;
import com.jdw.utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class RegisterServlet extends BaseServlet{
    private StudentService studentService = new StudentService();
    private TeacherService teacherService = new TeacherService();

    protected void register(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String identity = req.getParameter("identity");
        if (identity == null){
            req.setAttribute("msg", "请选择身份");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
        if (identity.equals("student")){
            Student student = WebUtils.copyParameterToBean(req.getParameterMap(), new Student());

            String s = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

            if (s.equalsIgnoreCase(req.getParameter("code"))) {
                try {
                    if (studentService.existsUsername(student.getUsername())) {
                        studentService.addStudent(student);
                        req.getSession().setAttribute("user",student);
                        req.getRequestDispatcher("/pages/user/student_login_success.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("msg", "用户名已存在");
                        req.setAttribute("username", student.getUsername());
                        req.setAttribute("name", student.getName());
                        req.setAttribute("major", student.getMajor());
                        req.setAttribute("grade", student.getGrade());
                        req.setAttribute("classId", student.getClassId());
                        req.setAttribute("phone", student.getPhone());
                        req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                req.setAttribute("msg", "验证码错误");
                req.setAttribute("username", student.getUsername());
                req.setAttribute("name", student.getName());
                req.setAttribute("major", student.getMajor());
                req.setAttribute("grade", student.getGrade());
                req.setAttribute("classId", student.getClassId());
                req.setAttribute("phone", student.getPhone());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
        }else {
            Teacher teacher = WebUtils.copyParameterToBean(req.getParameterMap(), new Teacher());

            String s = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
            req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

            if (s.equalsIgnoreCase(req.getParameter("code"))) {
                try {
                    if (teacherService.existsUsername(teacher.getUsername())) {
                        teacherService.addTeacher(teacher);
                        Teacher teacher1 = teacherService.queryByUsername(teacher.getUsername(), teacher.getPassword());
                        req.getSession().setAttribute("user",teacher1);
                        req.getRequestDispatcher("/pages/user/teacher_login_success.jsp").forward(req, resp);
                    } else {
                        req.setAttribute("msg", "用户名已存在");
                        req.setAttribute("username", teacher.getUsername());
                        req.setAttribute("name", teacher.getName());
                        req.setAttribute("major", teacher.getMajor());
                        req.setAttribute("grade", teacher.getGrade());
                        req.setAttribute("classId", teacher.getClassId());
                        req.setAttribute("phone", teacher.getPhone());
                        req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                req.setAttribute("msg", "验证码错误");
                req.setAttribute("username", teacher.getUsername());
                req.setAttribute("name", teacher.getName());
                req.setAttribute("major", teacher.getMajor());
                req.setAttribute("grade", teacher.getGrade());
                req.setAttribute("classId", teacher.getClassId());
                req.setAttribute("phone", teacher.getPhone());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
        }
    }
}
