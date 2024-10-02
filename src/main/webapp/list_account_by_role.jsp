<%@ page import="com.example.wwwduongtuankietlab1.services.AccountService" %>
<%@ page import="com.example.wwwduongtuankietlab1.services.RoleService" %>
<%@ page import="com.example.wwwduongtuankietlab1.repositories.RoleRepository" %>
<%@ page import="com.example.wwwduongtuankietlab1.repositories.ConnectDB" %>
<%@ page import="com.example.wwwduongtuankietlab1.repositories.AccountRepository" %>
<%@ page import="com.example.wwwduongtuankietlab1.entities.Account" %>
<%@ page import="com.example.wwwduongtuankietlab1.entities.Role" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/23/2024
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List accounts by role</title>
    <%@include file="cdn.jsp" %>
</head>
<body>
<%@include file="navbar.jsp" %>
<div class="container p-3">
    <h1 class="text-center">List accounts by role </h1>
    <form class="row g-3" method="post" action="filter-account">
        <div class="col-5">
            <label>
                <select class="form-select" name="role">
                    <option value="user" name="user">User</option>
                    <option value="admin" name="pass">Admin</option>
                </select>
            </label>
        </div>
        <div class="col-auto">
            <button class="btn btn-success" type="submit" value="Filter">Filter</button>
        </div>
    </form>
    <div class="card">
        <table class="table">
            <thead>
            <tr>
                <th>Account ID</th>
                <th>Email</th>
                <th>Full Name</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Role name</th>
            </tr>
            </thead>
            <tbody>
            <%
                AccountService accountService = new AccountService(new AccountRepository(ConnectDB.getInstance()));
                RoleService roleService = new RoleService(new RoleRepository(ConnectDB.getInstance()));
//                String roleId = "";
//                if(session.getAttribute("role").toString() == null){
//                    roleId = "user";
//                }else {
//                    roleId = session.getAttribute("role").toString();
//                }
                List<Account> rs;
                Object roleAttribute = session.getAttribute("role");

                if (roleAttribute != null) {
                    rs = accountService.getAccountsByRole(roleAttribute.toString());
                } else {
                    // Handle the case where the 'role' attribute is not set in the session
                    rs = accountService.getAccountsByRole("user"); // Provide a default role or handle it accordingly
                }
                for (Account a : rs) {
            %>
            <tr>
                <td><%= a.getAccountId() %>
                </td>
                <td scope="row"><%=a.getEmail()%>
                </td>

                <td><%=a.getFullName()%>
                </td>
                <td><%=a.getPhone()%>
                </td>
                <td>
                    <%
                        String active = "";
                        if (a.getStatus() == (byte) 1) {
                            active = "ACTIVE";
                        } else if (a.getStatus() == 0) {
                            active = "Deactivate";
                        } else {
                            active = "Deleted";
                        }
                    %>
                    <%=active%>
                </td>
                <%
                    Optional<Role> roleOptional = roleService.getRoleByAccountId(a.getAccountId());
                    String roleName = roleOptional.map(Role::getRoleName).orElse("user");
                %>
                <td><%=roleName%>
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
