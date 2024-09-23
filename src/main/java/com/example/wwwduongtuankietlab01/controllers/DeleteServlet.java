package com.example.wwwduongtuankietlab01.controllers;

import com.example.wwwduongtuankietlab01.repositories.AccountRepository;
import com.example.wwwduongtuankietlab01.repositories.ConnectDB;
import com.example.wwwduongtuankietlab01.services.AccountService;
import jakarta.servlet.annotation.WebServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
