package com.example.wwwduongtuankietlab1.controllers;

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

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        AccountRepository accountRepository = new AccountRepository(ConnectDB.getInstance());
        AccountService accountService = new AccountService(accountRepository);
        final var b = accountService.deleteAccount(id);
        RequestDispatcher requestDispatcher = null;
        HttpSession session = req.getSession();
        if(b){
            session.setAttribute("success", " delete success");
        }else{
            session.setAttribute("error", " delete errer");
        }
        requestDispatcher = req.getRequestDispatcher("dashboard.jsp");
        requestDispatcher.forward(req, resp);
    }
}
