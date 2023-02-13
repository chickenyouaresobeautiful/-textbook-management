package com.jdw.dao;

import com.jdw.pojo.Student;
import com.jdw.pojo.Teacher;

import java.sql.SQLException;
import java.util.List;

public class TeacherDAO extends BasicDAO<Teacher> {
    public Teacher queryTeacherByUsername(String username) throws SQLException {
        String sql = "select * from teacher where username = ?";
        return queryForOneList(sql, Teacher.class, username);
    }

    public Teacher queryTeacherByUsernameAndPassword(String username, String password) throws SQLException {
        String sql = "select * from teacher where username = ? and password = ?";
        return queryForOneList(sql, Teacher.class, username, password);
    }

    public int saveTeacher(Teacher teacher) throws SQLException {
        String sql = "insert into teacher values(null,?,?,?,?,?,?,?,?)";
        return update(sql, teacher.getUsername(), teacher.getPassword(), teacher.getName(), teacher.getSex(), teacher.getMajor(), teacher.getGrade(), teacher.getClassId(), teacher.getPhone());
    }

    public int updatePwd(String username, String newPassword) throws SQLException {
        String sql = "update teacher set password = ? where username = ?";
        return update(sql, newPassword, username);
    }

    public List<Teacher> queryTeachers() throws SQLException {
        String sql = "select * from teacher";
        return queryForList(sql, Teacher.class);
    }

    public int deleteTeacher(int id) throws SQLException {
        String sql = "delete from teacher where id = ?";
        return update(sql, id);
    }

    public Integer queryForPageTotalCount() throws SQLException {
        Number number = (Number) queryForOne("select count(*) from student");
        return number.intValue();
    }

    public List<Teacher> queryForItems(int begin, int pageSize) throws SQLException {
        String sql = "select * from teacher limit ? ,?";
        return queryForList(sql, Teacher.class, begin, pageSize);
    }

    public Teacher selectTeacherById(int id) throws SQLException {
        String sql = "select * from teacher where id = ?";
        return queryForOneList(sql, Teacher.class, id);
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        String sql = "update teacher set username = ?,password = ?,name = ?,sex = ?,major = ?,grade = ?,classId = ?,phone = ? where id = ?";
        update(sql, teacher.getUsername(), teacher.getPassword(), teacher.getName(), teacher.getSex(), teacher.getMajor(), teacher.getGrade(), teacher.getClassId(), teacher.getPhone(), teacher.getId());
    }
}
