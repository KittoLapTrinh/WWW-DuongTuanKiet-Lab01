<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/23/2024
  Time: 11:45 PM
  To change this template use File | Settings | File Templates.
--%>
<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Homework week 1</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="dashboard.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="insert_page.jsp">Add account</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" href="list_account_by_role.jsp">List of accounts by role</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <%=session.getAttribute("email")%>
                    </a>
                    <ul class="dropdown-menu">

                        <li><a class="dropdown-item" href="logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
            <%--            <input type="hidden"><%=session.getAttribute("id")%>--%>
        </div>
    </div>
</nav>
