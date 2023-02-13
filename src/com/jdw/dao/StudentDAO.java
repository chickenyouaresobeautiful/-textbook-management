package com.jdw.dao;

import com.jdw.pojo.Book;
import com.jdw.pojo.Student;
import com.jdw.pojo.Teacher;

import java.sql.SQLException;
import java.util.List;

public class StudentDAO extends BasicDAO<Student> {
    public Student queryStudentByUsername(String username) throws SQLException {
        String sql = "select * from student where username = ?";
        return queryForOneList(sql, Student.class, username);
    }

    public Student queryStudentByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from student where username = ? and password = ?";
        return queryForOneList(sql, Student.class, username, password);
    }

    public int saveStudent(Student student) throws SQLException {
        String sql = "insert into student values(null,?,?,?,?,?,?,?,?)";
        return update(sql, student.getUsername(), student.getPassword(), student.getName(), student.getSex(), student.getMajor(), student.getGrade(), student.getClassId(), student.getPhone());
    }

    public int updatePwd(String username, String newPassword) throws SQLException {
        String sql = "update student set password = ? where username = ?";
        return update(sql, newPassword, username);
    }

    public List<Student> queryStudents() throws SQLException {
        String sql = "select * from student";
        return queryForList(sql, Student.class);
    }

    public int deleteStudent(int id) throws SQLException {
        String sql = "delete from student where id = ?";
        return update(sql, id);
    }

    public Integer queryForPageTotalCount() throws SQLException {
        Number number = (Number) queryForOne("select count(*) from student");
        return number.intValue();
    }

    public List<Student> queryForItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from student limit ? ,?";
        return queryForList(sql, Student.class, begin, pageSize);
    }

    public Student selectStudentById(int id) throws SQLException {
        String sql = "select * from student where id = ?";
        return queryForOneList(sql, Student.class, id);
    }

    public void updateStudent(Student student) throws SQLException {
        String sql = "update student set username = ?,password = ?,name = ?,sex = ?,major = ?,grade = ?,classId = ?,phone = ? where id = ?";
        update(sql, student.getUsername(), student.getPassword(), student.getName(), student.getSex(), student.getMajor(), student.getGrade(), student.getClassId(), student.getPhone(), student.getId());
    }
}
