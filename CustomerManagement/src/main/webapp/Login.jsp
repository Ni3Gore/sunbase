<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<link rel="stylesheet" href="style.css">
<body>
    <div class="wrapper">
    

        <div class="login-box">
          <form action="login" method="get">
            <h2>Login</h2>
      
            <div class="input-box">
              <span class="icon">
                <ion-icon name="mail"></ion-icon>
              </span>
              <input type="text" required name="username">
              <label>Username</label>
            </div>
      
            <div class="input-box">
              <span class="icon">
                <ion-icon name="lock-closed"></ion-icon>
              </span>
              <input type="password" required name="password">
              <label>Password</label>
            </div>
      
            
      
            <button type="submit">Login</button>
      
          <%
          String errormsg = (String)session.getAttribute("error message");
          if(errormsg!=null){
        	  
        	%>
        	<h3 style="color: red; margin-top: 50px"><%=errormsg %> </h3>
        	<%  
        	session.setAttribute("error message", null);
          }
          %>  
          </form>
        </div>
      
      </div>
</body>
</html>
