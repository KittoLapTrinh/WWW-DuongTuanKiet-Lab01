<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Form</title>

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        form {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        h3 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        label {
            margin-top: 20px;
            font-weight: 500;
            color: #666;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }

        input:focus {
            border-color: #007bff;
            outline: none;
        }

        button {
            margin-top: 20px;
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        .social {
            margin-top: 20px;
            display: flex;
            justify-content: space-between;
        }

        .social div {
            display: flex;
            align-items: center;
            justify-content: center;
            width: 48%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .social .go:hover {
            background-color: #f1f1f1;
        }

        .social .fb:hover {
            background-color: #f1f1f1;
        }

        .social i {
            margin-right: 8px;
        }
    </style>
</head>
<body>
<input type="hidden" id="status"
       value="<%=request.getAttribute("status")%>">
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<form action="login" method="post">
    <h3>Login Here</h3>

    <label for="username"></label></br>
    <input type="text" placeholder="Email or Phone" id="username" name="username"></br>

    <label for="password"></label></br>
    <input type="password" placeholder="Password" id="password" name="password"></br>

    <button type="submit">Login</button>
    <div class="social">
        <div class="go"><i class="fab fa-google"></i> Google</div>
        <div class="fb"><i class="fab fa-facebook"></i> Facebook</div>
    </div>
</form>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript">
    const status = document.getElementById("status").value;
    if(status === "failed"){
        swal("Sorry","Wrong username or password","error")
    }
    if(status === "invalidEmail"){
        swal("Sorry","Please Enter Username","error")
    }
    if(status === "invalidPassword"){
        swal("Sorry","Please Enter Password","error")
    }

</script>
</body>
</html>