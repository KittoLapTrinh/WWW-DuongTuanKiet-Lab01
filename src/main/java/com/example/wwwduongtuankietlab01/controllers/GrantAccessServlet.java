package com.example.wwwduongtuankietlab01.controllers;


import com.example.wwwduongtuankietlab01.repositories.ConnectDB;
import com.example.wwwduongtuankietlab01.repositories.GrantAccessRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/grant")
public class GrantAccessServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        requestDispatcher = req.getRequestDispatcher("dashboard.jsp");
        String accountId = req.getParameter("id");
        GrantAccessRepository grantAccessRepository = new GrantAccessRepository(ConnectDB.getInstance());
        grantAccessRepository.updateGrantAccess(accountId);
        requestDispatcher.forward(req, resp);

    }
}
