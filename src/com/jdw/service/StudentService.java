package com.jdw.service;

import com.jdw.dao.StudentDAO;
import com.jdw.pojo.Book;
import com.jdw.pojo.CartItem;
import com.jdw.pojo.Page;
import com.jdw.pojo.Student;

import java.sql.SQLException;
import java.util.List;

public class StudentService {
    private StudentDAO studentDAO = new StudentDAO();

    public Student queryByUsername(String username, String password) throws SQLException {
        return studentDAO.queryStudentByUsernameAndPassword(username, password);
    }

    public Student selectStudentById(int id) {
        try {
            return studentDAO.selectStudentById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //注册，添加学生
    public int addStudent(Student student) throws SQLException {
        return studentDAO.saveStudent(student);
    }

    //修改密码
    public int updatePwd(String username, String password, String newPassword) throws SQLException {
        Student student = studentDAO.queryStudentByUsernameAndPassword(username, password);
        return studentDAO.updatePwd(student.getUsername(), newPassword);
    }

    //查询所有的学生
    public List<Student> queryStudent() throws SQLException {
        return studentDAO.queryStudents();
    }

    //删除学生
    public int deleteStudentById(int id) throws SQLException {
        return studentDAO.deleteStudent(id);
    }

    public boolean existsUsername(String username) throws SQLException {
        if (studentDAO.queryStudentByUsername(username) == null && username != null) {
            return true;
        } else {
            return false;
        }
    }

    public void updateStudent(Student student) {
        try {
            studentDAO.updateStudent(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Page<Student> page(int pageNo, int pageSize) throws SQLException {
        Page<Student> page = new Page<>();

        page.setPageSize(pageSize);

        Integer pageTotalCount = studentDAO.queryForPageTotalCount();
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
        List<Student> students = studentDAO.queryForItems(begin, pageSize);
        page.setItems(students);
        return page;
    }
}
