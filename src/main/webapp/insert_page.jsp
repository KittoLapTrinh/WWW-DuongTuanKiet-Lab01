<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/23/2024
  Time: 11:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="cdn.jsp" %>
</head>
<body>
<input type="hidden" id="status"
       value="<%= request.getAttribute("status")%>">
<%@include file="navbar.jsp" %>
<div class="container p-4">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card">
                <div class="card-body">
                    <p class="fs-3 text-center">Add Account</p>
                    <form action="insert" method="post">
                        <div class="mb-3">
                            <label class="form-label">Account ID</label> <input
                                type="text" class="form-control" name="id">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label> <input
                                type="email" class="form-control" name="email">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Full Name</label> <input type="text"
                                                                               class="form-control" name="name">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label> <input
                                type="password" class="form-control" name="password">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phone</label> <input type="text"
                                                                           class="form-control" name="phone">
                        </div>
                        <button type="submit" class="btn btn-primary col-md-12">Submit</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
    const status = document.getElementById("status").value;
    if (status === "success") {
        swal({
            title: "Good job!",
            text: "Insert success!",
            icon: "success",
        })
    }
</script>
</body>
</html>
