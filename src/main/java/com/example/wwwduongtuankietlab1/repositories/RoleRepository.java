package com.example.wwwduongtuankietlab1.repositories;

import com.example.wwwduongtuankietlab1.entities.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public class RoleRepository {
    private final Connection connection;

    public RoleRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean insertRole(Role role) {
        boolean flag = false;
        try {
            String sql = "insert into role values(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getRoleId());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setString(3, role.getRoleName());
            preparedStatement.setInt(4, role.getStatus());
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                flag = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean deleteRole(String roleId) {
        boolean flag = false;
        try {
            String sql = "update role set status = -1 where role_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roleId);
            int n = preparedStatement.executeUpdate();
            if (n > 0) flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean updateRole(Role role) {
        boolean flag = false;
        try {
            String sql = "update role set description=?,role_name=?,status=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, role.getDescription());
            preparedStatement.setString(2, role.getRoleName());
            preparedStatement.setInt(3, role.getStatus());
            int n = preparedStatement.executeUpdate();
            if (n > 0) flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public Optional<Role> getRoleByAccountId(String accountId) {
        String sql = "SELECT r.* FROM account a INNER JOIN grant_access g\n" +
                "ON a.account_id = g.account_id\n" +
                "INNER JOIN role r ON r.role_id = g.role_id\n" +
                "WHERE a.account_id = ?";
        Role role = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                role = new Role();
                role.setRoleId(rs.getString("role_id"));
                role.setDescription(rs.getString("description"));
                role.setRoleName(rs.getString("role_name"));
                role.setStatus(rs.getByte("status"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(role);
    }


    public Optional<Role> getRoleByRoleId(String roleId) {
        Role role = null;
        try {
            String sql = "select * from role where role_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, roleId);
            var rs = preparedStatement.executeQuery();
            while (rs.next()){
                role = new Role();
                role.setStatus(rs.getByte("status"));
                role.setRoleName(rs.getString("role_name"));
                role.setDescription(rs.getString("description"));
                role.setRoleId(roleId);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(role);
    }
}
