package com.example.wwwduongtuankietlab1.controllers;

import com.example.wwwduongtuankietlab1.entities.Log;
import com.example.wwwduongtuankietlab1.repositories.ConnectDB;
import com.example.wwwduongtuankietlab1.repositories.LogRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        requestDispatcher = req.getRequestDispatcher("/login.jsp");
        HttpSession session = req.getSession();
        String accountId = (String) session.getAttribute("id");
        Log log = new Log();
        log.setAccountId(accountId);
        log.setNotes("user logout");
        log.setLogoutTime(LocalDateTime.now());
        LogRepository logRepository = new LogRepository(ConnectDB.getInstance());
        logRepository.insertLogout(log);
        requestDispatcher.forward(req,resp);
    }
}
