package com.jdw.pojo;

public class Manager {
    private String adminId;
    private String password;

    public Manager() {
    }

    public Manager(String adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "adminId='" + adminId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
