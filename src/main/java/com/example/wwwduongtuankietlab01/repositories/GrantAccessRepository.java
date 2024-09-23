package com.example.wwwduongtuankietlab01.repositories;

import com.example.wwwduongtuankietlab01.entities.GrantAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GrantAccessRepository {
    private final Connection con;

    public GrantAccessRepository(Connection con) {
        this.con = con;
    }
    public boolean insertGrantAccess(GrantAccess grantAccess){
        boolean flag = false;
        try {
            String sql = "insert into grant_access values(?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setByte(1,grantAccess.getIsGrant());
            preparedStatement.setString(2,grantAccess.getNote());
            preparedStatement.setString(3,grantAccess.getAccount().getAccountId());
            preparedStatement.setString(4,grantAccess.getRole().getRoleId());
            int n = preparedStatement.executeUpdate();
            if(n >  0) flag = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean updateGrantAccess(String accountId){
        boolean flag = false;
        try {
            String sql = "update grant_access set role_id = 'admin' " +
                    "where account_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,accountId);
            int n = preparedStatement.executeUpdate();
            if(n > 0) flag = true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
