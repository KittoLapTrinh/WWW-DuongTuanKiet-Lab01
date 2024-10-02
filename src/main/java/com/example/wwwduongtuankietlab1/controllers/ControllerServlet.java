package com.example.wwwduongtuankietlab1.controllers;

import com.example.wwwduongtuankietlab1.entities.Account;
import com.example.wwwduongtuankietlab1.entities.Log;
import com.example.wwwduongtuankietlab1.repositories.AccountRepository;
import com.example.wwwduongtuankietlab1.repositories.ConnectDB;
import com.example.wwwduongtuankietlab1.repositories.LogRepository;
import com.example.wwwduongtuankietlab1.services.AccountService;
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

@WebServlet(urlPatterns = {"/login"})
public class ControllerServlet extends HttpServlet {

    private static final String USER_PAGE = "user_page.jsp";
    private static final String DASHBOARD_PAGE = "dashboard.jsp";
    private static final String LOGIN_PAGE = "login.jsp";

    private void logLogin(String accountId, String notes) {
        Log log = new Log();
        log.setAccountId(accountId);
        log.setLoginTime(LocalDateTime.now());
        log.setNotes(notes);
        LogRepository logRepository = new LogRepository(ConnectDB.getInstance());
        logRepository.insertLogin(log);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AccountRepository accountRepository = new AccountRepository(ConnectDB.getInstance());
            AccountService accountService = new AccountService(accountRepository);

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            RequestDispatcher requestDispatcher = null;

            // Kiểm tra dữ liệu đầu vào
            if (username == null || username.isEmpty()) {
                req.setAttribute("status", "invalidEmail");
                requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
                requestDispatcher.forward(req, resp);
                return;
            }

            if (password == null || password.isEmpty()) {
                req.setAttribute("status", "invalidPassword");
                requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
                requestDispatcher.forward(req, resp);
                return;
            }

            // Xác minh tài khoản
            String role = accountService.verifyAccount(username, password);
            Optional<Account> accountOptional = accountRepository.loginForm(username, password);
            String accountId = accountOptional.map(Account::getAccountId).orElse(null);
            String email = accountOptional.map(Account::getEmail).orElse(null);

            HttpSession httpSession = req.getSession();

            if ("admin".equalsIgnoreCase(role)) {
                logLogin(accountId, "admin login");
                httpSession.setAttribute("id", accountId);
                httpSession.setAttribute("email", email);
                requestDispatcher = req.getRequestDispatcher(DASHBOARD_PAGE);
            } else if ("user".equalsIgnoreCase(role)) {
                logLogin(accountId, "user login");
                httpSession.setAttribute("id", accountId);
                httpSession.setAttribute("email", email);
                requestDispatcher = req.getRequestDispatcher(USER_PAGE);
            } else {
                req.setAttribute("status", "failed");
                requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
            }

            if (requestDispatcher != null) {
                requestDispatcher.forward(req, resp);
            }
        } catch (Exception e) {
            // Ghi lại lỗi hoặc hiển thị một trang lỗi
            e.printStackTrace();
            req.setAttribute("status", "error");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(LOGIN_PAGE);
            requestDispatcher.forward(req, resp);
        }
    }
}
