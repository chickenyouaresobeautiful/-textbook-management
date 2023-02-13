package com.jdw.service;

import com.jdw.dao.TeacherDAO;
import com.jdw.pojo.Page;
import com.jdw.pojo.Student;
import com.jdw.pojo.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherService {
    private TeacherDAO teacherDAO = new TeacherDAO();

    public Teacher queryByUsername(String username, String password) throws SQLException {
        return teacherDAO.queryTeacherByUsernameAndPassword(username, password);
    }

    //注册，添加学生
    public int addTeacher(Teacher teacher) throws SQLException {
        return teacherDAO.saveTeacher(teacher);
    }

    //修改密码
    public int updatePwd(String username, String password, String newPassword) throws SQLException {
        Teacher teacher = teacherDAO.queryTeacherByUsernameAndPassword(username, password);
        return teacherDAO.updatePwd(teacher.getUsername(), newPassword);
    }

    //查询所有的学生
    public List<Teacher> queryTeacher() throws SQLException {
        return teacherDAO.queryTeachers();
    }

    //删除学生
    public int deleteTeacherById(int id) throws SQLException {
        return teacherDAO.deleteTeacher(id);
    }

    public boolean existsUsername(String username) throws SQLException {
        if (teacherDAO.queryTeacherByUsername(username) == null && username != null) {
            return true;
        } else {
            return false;
        }
    }

    public Page<Teacher> page(int pageNo, int pageSize) throws SQLException {
        Page<Teacher> page = new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount = teacherDAO.queryForPageTotalCount();
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
        List<Teacher> teachers = teacherDAO.queryForItems(begin, pageSize);
        page.setItems(teachers);
        return page;
    }

    public Teacher selectTeacherById(int id) {
        try {
            return teacherDAO.selectTeacherById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateTeacher(Teacher teacher) {
        try {
            teacherDAO.updateTeacher(teacher);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
