package com.example.wwwduongtuankietlab1.repositories;

import com.example.wwwduongtuankietlab1.entities.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogRepository {
    private final Connection connection;

    public LogRepository(Connection connection) {
        this.connection = connection;
    }
    public void insertLogin(Log log) {
        try {
            String sql = "INSERT INTO log (account_id, login_time,notes) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log.getAccountId());
            preparedStatement.setString(2,log.getLoginTime().toString());
            preparedStatement.setString(3, log.getNotes());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void insertLogout(Log log) {
        try {
            String sql = "INSERT INTO log (account_id, logout_time,notes) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, log.getAccountId());
            preparedStatement.setString(2,log.getLogoutTime().toString());
            preparedStatement.setString(3, log.getNotes());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
