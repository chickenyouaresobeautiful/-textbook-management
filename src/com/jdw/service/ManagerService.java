package com.jdw.service;

import com.jdw.dao.ManagerDAO;
import com.jdw.pojo.Manager;

import java.sql.SQLException;

public class ManagerService {
    private ManagerDAO managerDAO = new ManagerDAO();

    public Manager queryAdmin(String adminId, String password) throws SQLException {
        return managerDAO.queryManagerByAdminIdAndPassword(adminId, password);
    }
}
