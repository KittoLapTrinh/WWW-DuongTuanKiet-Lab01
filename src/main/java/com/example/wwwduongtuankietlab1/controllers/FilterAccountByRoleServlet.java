package com.example.wwwduongtuankietlab1.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/filter-account")
public class FilterAccountByRoleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = req.getParameter("role");
        session.setAttribute("role",role);
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher("list_account_by_role.jsp");
        requestDispatcher.forward(req,resp);

    }
}
