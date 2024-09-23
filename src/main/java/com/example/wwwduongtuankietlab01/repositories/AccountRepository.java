package com.example.wwwduongtuankietlab01.repositories;

import com.example.wwwduongtuankietlab01.entities.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private final Connection connection;

    public AccountRepository(Connection connection) {
        this.connection = connection;
    }

    public Optional<Account> loginForm(String us, String pass) {
        String sql = "SELECT a.* FROM account a\n" +
                "INNER JOIN grant_access g\n" +
                "ON a.account_id = g.account_id\n" +
                "WHERE a.email = ? AND a.`password` = ?\n" +
                "AND g.role_id = 'admin'";
        Account account = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, us);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getString("account_id"));
                account.setEmail(rs.getString("email"));
                account.setFullName(rs.getString("full_name"));
                account.setPassword(rs.getString("password"));
                account.setStatus(Byte.parseByte(rs.getString("status")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(account);
    }

    public Optional<Account> getAccountWithUser(String username, String pass) {
        String sql = "SELECT a.* FROM account a\n" +
                "INNER JOIN grant_access g\n" +
                "ON a.account_id = g.account_id\n" +
                "WHERE a.email = ? AND a.`password` = ?\n" +
                "AND g.role_id = 'user'";
        Account account = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getString("account_id"));
                account.setEmail(rs.getString("email"));
                account.setFullName(rs.getString("full_name"));
                account.setPassword(rs.getString("password"));
                account.setStatus(Byte.parseByte(rs.getString("status")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(account);
    }

    public List<Account> getAllAccount() {
        String sql = "select * from account where status = 1";
        List<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Account account = new Account();
                account.setAccountId(rs.getString("account_id"));
                account.setEmail(rs.getString("email"));
                account.setFullName(rs.getString("full_name"));
                account.setPassword(rs.getString("password"));
                account.setStatus(Byte.parseByte(rs.getString("status")));
                account.setPhone(rs.getString("phone"));
                accounts.add(account);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return accounts;
    }

    public boolean insertAccount(Account account) {
        boolean flag = false;
        String sql = "INSERT INTO account values(?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getAccountId());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getFullName());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.setString(5, account.getPhone());
            preparedStatement.setInt(6, account.getStatus());
            int n = preparedStatement.executeUpdate();
            if(n > 0) flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean deleteAccount(String accountId) {
        boolean flag = false;
        try {
            String sql = "update account set status = -1 where account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, accountId);
            int n = preparedStatement.executeUpdate();
            if (n > 0) flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public boolean updateAccount(Account account) {
        boolean flag = false;
        try {
            String sql = "update account set email = ?,full_name=?,password=?,phone=?,status= ? where account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getEmail());
            preparedStatement.setString(2, account.getFullName());
            preparedStatement.setString(3, account.getPassword());
            preparedStatement.setString(4, account.getPhone());
            preparedStatement.setInt(5, account.getStatus());
            preparedStatement.setString(6, account.getAccountId());
            int n = preparedStatement.executeUpdate();
            if (n > 0) flag = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public Optional<Account> getAccountById(String accountId) {
        String sql = "select * from account where account_id = ?";
        Account account = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                account = new Account();
                account.setAccountId(rs.getString("account_id"));
                account.setEmail(rs.getString("email"));
                account.setFullName(rs.getString("full_name"));
                account.setPassword(rs.getString("password"));
                account.setPhone(rs.getString("phone"));
                account.setStatus(Byte.parseByte(rs.getString("status")));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(account);
    }

    public List<Account> getAccountsByRole(String roleId){
        List<Account> accounts = new ArrayList<>();
        try {
            String sql = "select a.* from account a join grant_access g on a.account_id = g.account_id " +
                    "where a.status = 1 and g.role_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,roleId);
            ResultSet rs =  preparedStatement.executeQuery();
            while (rs.next()){
                Account account = new Account();
                account.setAccountId(rs.getString("account_id"));
                account.setPhone(rs.getString("phone"));
                account.setEmail(rs.getString("email"));
                account.setStatus(rs.getByte("status"));
                account.setFullName(rs.getString("full_name"));
                account.setPassword(rs.getString("password"));
                accounts.add(account);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return accounts;
    }

}
