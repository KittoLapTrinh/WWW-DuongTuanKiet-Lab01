package com.example.wwwduongtuankietlab01.controllers;

import com.example.wwwduongtuankietlab01.entities.Account;
import com.example.wwwduongtuankietlab01.entities.Log;
import com.example.wwwduongtuankietlab01.repositories.AccountRepository;
import com.example.wwwduongtuankietlab01.repositories.ConnectDB;
import com.example.wwwduongtuankietlab01.repositories.LogRepository;
import com.example.wwwduongtuankietlab01.services.AccountService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.Optional;

@WebServlet(urlPatterns = {"/login"})
public class ControllerServlet extends HttpServlet {

    private static final String USER_PAGE = "user_page.jsp";
    private static final String DASHBOARD_PAGE = "dashboard.jsp";

    private void logLogin(String accountId, String notes) {
        Log log = new Log();
        log.setAccountId(accountId);
        log.setLoginTime(LocalDateTime.now());
        log.setNotes(notes);
        LogRepository logRepository = new LogRepository(ConnectDB.getInstance());
        logRepository.insertLogin(log);
    }
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AccountRepository accountRepository = new AccountRepository(ConnectDB.getInstance());
            AccountService accountService = new AccountService(accountRepository);
            String username = req.getParameter("username");
            System.out.println(username);
            String password = req.getParameter("password");
            System.out.println(password);
            String role = accountService.verifyAccount(username, password);

            HttpSession httpSession = req.getSession();
            RequestDispatcher requestDispatcher = null;
            if(username.isEmpty()) {
                req.setAttribute("status", "invalidEmail");
                requestDispatcher = req.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(req, resp);
            }
            if(password.isEmpty()) {
                req.setAttribute("status", "invalidPassword");
                requestDispatcher = req.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(req, resp);
            }
            if ("admin".equalsIgnoreCase(role)) {
                System.out.println("Login Success with Admin");
                Optional<Account> account = accountRepository.loginForm(username, password);
                String accountId = account.map(Account::getAccountId).orElse(null);
                String email = account.map(Account::getEmail).orElse(null);

                logLogin(accountId, "admin login");

                httpSession.setAttribute("id", accountId);
                httpSession.setAttribute("email", email);
                requestDispatcher = req.getRequestDispatcher(DASHBOARD_PAGE);
            } else if ("user".equalsIgnoreCase(role)) {
                System.out.println("Login Success with User");
                Optional<Account> account = accountRepository.getAccountWithUser(username, password);
                String accountId = account.map(Account::getAccountId).orElse(null);
                String email = account.map(Account::getEmail).orElse(null);

                logLogin(accountId, "user login");
                httpSession.setAttribute("id", accountId);
                httpSession.setAttribute("email", email);
                requestDispatcher = req.getRequestDispatcher(USER_PAGE);
            }else{
                req.setAttribute("status", "failed");
                requestDispatcher = req.getRequestDispatcher("index.jsp");
            }
            if (requestDispatcher != null) {
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log or show an error page)
            System.out.println(e.getMessage());
        }
    }
}
