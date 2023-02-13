package com.jdw.dao;

import com.jdw.pojo.Manager;

import java.sql.SQLException;

public class ManagerDAO extends BasicDAO<Manager>{
    public Manager queryManagerByAdminIdAndPassword(String adminId, String password) throws SQLException {
        String sql = "select * from manager where adminId = ? and password = ?";
        return queryForOneList(sql, Manager.class, adminId, password);
    }
}
