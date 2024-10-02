<%@ page import="com.example.wwwduongtuankietlab1.repositories.AccountRepository" %>
<%@ page import="com.example.wwwduongtuankietlab1.repositories.ConnectDB" %>
<%@ page import="com.example.wwwduongtuankietlab1.services.AccountService" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.example.wwwduongtuankietlab1.entities.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
    <%@include file="cdn.jsp" %>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-light row">
    <div class="col-2">
        <div class="collapse navbar-collapse" id="navbarNavDropdown">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <%=session.getAttribute("email")%>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container p-5">
    <div class="card">
        <table class="table">
            <thead>
            <tr>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
            </tr>
            </thead>
            <tbody>
            <%
                AccountRepository accountRepository = new AccountRepository(ConnectDB.getInstance());
                AccountService accountService = new AccountService(accountRepository);

                String id = session.getAttribute("account_id") != null ? session.getAttribute("account_id").toString() : null;

                if (id != null) {
                    Optional<Account> op = accountService.getAccountById(id);

                    // In ra thông tin của Optional op để kiểm tra
                    System.out.println("Result of getAccountById: " + op);

                    Account account = op.orElse(null);
                    if (account != null) {
            %>
            <tr>
                <td><%=account.getFullName()%></td>
                <td><%= account.getEmail()%></td>
                <td><%= account.getPhone()%></td>
            </tr>
            <%
            } else {
                System.out.println("Account not found for ID: " + id);
            %>
            <tr>
                <td colspan="3">Account not found.</td>
            </tr>
            <%
                }
            } else {
                System.out.println("No account ID found in session.");
            %>
            <tr>
                <td colspan="3">No account ID found in session.</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
