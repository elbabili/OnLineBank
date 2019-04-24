<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login screen</title>
        <link rel="stylesheet" type="text/css" href="styles.css" />
    </head>
    <body>
        <h1>Authentification</h1>
        
        <%
        	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        	response.setDateHeader("Expires", 0); // Proxies.
        %>
    
        <form method="post" action="controller">

            Login: 
            <input name="txtLogin" value="${login}" autofocus />
            <br/>

            Password: 
            <input name="txtPassword" type="password" value="${password}" />
            <br/> <br/>
            
            <input type="submit" value="Connect" />
            <br/><br/>
            
            <div class="errorMessage">${errorMessage}</div>
            
        </form>
    
    </body>
</html>