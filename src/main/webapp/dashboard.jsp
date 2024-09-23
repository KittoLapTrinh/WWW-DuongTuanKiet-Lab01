<%@ page import="com.example.wwwduongtuankietlab01.services.AccountService" %>
<%@ page import="com.example.wwwduongtuankietlab01.repositories.AccountRepository" %>
<%@ page import="com.example.wwwduongtuankietlab01.repositories.ConnectDB" %>
<%@ page import="com.example.wwwduongtuankietlab01.services.RoleService" %>
<%@ page import="com.example.wwwduongtuankietlab01.repositories.RoleRepository" %>
<%@ page import="com.example.wwwduongtuankietlab01.entities.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page import="com.example.wwwduongtuankietlab01.entities.Role" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/23/2024
  Time: 1:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="cdn.jsp" %>

<html>
<head>
    <title>Dashboard of Admin</title>
</head>
<body>
    <div class="container p-3">
        <h1 class="text-center">Dashboard Admin</h1>
        <div class="card">
            <table class="table">
                <thead>
                <tr>
                    <th>Account ID</th>
                    <th>Email</th>
                    <th>Full Name</th>
                    <th>Phone</th>
                    <th>Status</th>
                    <th>Role Name</th>
                </tr>
                </thead>
                <tbody>
                <%
                    AccountService accountService = new AccountService(new AccountRepository(ConnectDB.getInstance()));
                    RoleService roleService = new RoleService(new RoleRepository(ConnectDB.getInstance()));
                    List<Account> rs = accountService.getAccounts();

                    for(Account a : rs) {
                %>
                <tr>
                    <td><%=a.getAccountId() %></td>
                    <td scope="row"><%= a.getEmail()%></td>
                    <td><%=a.getFullName()%></td>
                    <td><%=a.getPhone()%></td>
                    <td>
                        <%
                            String active = "";
                            if(a.getStatus() == (byte) 1){
                                active = "ACTIVE";
                            }else if(a.getStatus() == 0){
                                active = "Deactivate";
                            }else{
                                active = "Delete";
                            }
                        %>
                        <%=active%>
                    </td>
                    <%
                        Optional<Role> roleOptional = roleService.getRoleByAccountId(a.getAccountId());
                        String roleName = roleOptional.map(Role::getRoleName).orElse("user");
                    %>
                    <td><%=roleName%></td>
                    <td>
                        <a href="update_page.jsp?id=<%=a.getAccountId()%>" class="btn btn-sm btn-primary">Edit</a>
                        <a href="delete?id=<%=a.getAccountId()%>" class="btn btn-sm btn-danger">Delete</a>
                        <a href="grant?id=<%=a.getAccountId()%>" class="btn btn-sm btn-warning">Grant Account</a>
                    </td>
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
