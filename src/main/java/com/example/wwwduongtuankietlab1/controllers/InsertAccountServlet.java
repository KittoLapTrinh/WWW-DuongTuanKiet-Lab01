package com.example.wwwduongtuankietlab1.controllers;

import com.example.wwwduongtuankietlab1.entities.Account;
import com.example.wwwduongtuankietlab1.entities.GrantAccess;
import com.example.wwwduongtuankietlab1.entities.Role;
import com.example.wwwduongtuankietlab1.repositories.AccountRepository;
import com.example.wwwduongtuankietlab1.repositories.ConnectDB;
import com.example.wwwduongtuankietlab1.repositories.GrantAccessRepository;
import com.example.wwwduongtuankietlab1.repositories.RoleRepository;
import com.example.wwwduongtuankietlab1.services.AccountService;
import com.example.wwwduongtuankietlab1.services.RoleService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(urlPatterns = {"/insert"})
public class InsertAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountId = req.getParameter("id");
        String email = req.getParameter("email");
        String fullName = req.getParameter("name");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        AccountRepository accountRepository = new AccountRepository(ConnectDB.getInstance());
        AccountService accountService = new AccountService(accountRepository);
        RoleRepository roleRepository = new RoleRepository(ConnectDB.getInstance());
        RoleService roleService = new RoleService(roleRepository);
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository(ConnectDB.getInstance());
        RequestDispatcher requestDispatcher = null;
        Account account = new Account();
        account.setAccountId(accountId);
        account.setFullName(fullName);
        account.setPassword(password);
        account.setEmail(email);
        account.setPhone(phone);
        account.setStatus((byte) 1);
        accountService.addAccount(account);
        GrantAccess grantAccess =  new GrantAccess();
        grantAccess.setAccount(account);
        grantAccess.setNote("Create account at: " + LocalDateTime.now().toString());
        grantAccess.setIsGrant((byte) 1);
        Optional<Role> user = roleService.getRoleByRoleId("user");
        Role role =  user.orElse(null);
        grantAccess.setRole(role);
        grantAccessRepository.insertGrantAccess(grantAccess);
        HttpSession session = req.getSession();
        req.setAttribute("status","success");
        requestDispatcher = req.getRequestDispatcher("insert_page.jsp");
        requestDispatcher.forward(req,resp);
    }
}