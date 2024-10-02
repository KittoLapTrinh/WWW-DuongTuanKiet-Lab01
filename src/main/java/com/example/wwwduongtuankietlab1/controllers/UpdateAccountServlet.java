package com.example.wwwduongtuankietlab1.controllers;

import com.example.wwwduongtuankietlab1.entities.Account;
import com.example.wwwduongtuankietlab1.repositories.AccountRepository;
import com.example.wwwduongtuankietlab1.repositories.ConnectDB;
import com.example.wwwduongtuankietlab1.services.AccountService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/update")
public class UpdateAccountServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String email = req.getParameter("email");
        String fullName = req.getParameter("fullName");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        Account account = new Account(id, fullName, password, email, phone,(byte) 1);
        AccountRepository accountRepository = new AccountRepository(ConnectDB.getInstance());
        AccountService accountService = new AccountService(accountRepository);
        boolean rs = accountService.updateAccount(account);
        HttpSession session = req.getSession();
        RequestDispatcher requestDispatcher = null;
        if(rs){
            req.setAttribute("update", "complete");
        }else{
            req.setAttribute("update", "upcompleted");
        }

        requestDispatcher = req.getRequestDispatcher("update_page.jsp");
        requestDispatcher.forward(req, resp);

    }
}
