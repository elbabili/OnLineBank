<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "co.simplon.entities.User"%>

<%
		session = request.getSession();		
%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log out screen</title>
</head>
<body>

<body>
        <h1>LOG OUT PAGE</h1>
        
        <%
        	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        	response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        	response.setDateHeader("Expires", 0); // Proxies.
        	
        	User user = (User)session.getAttribute("ConnectedUser");
        	String str = "log out "; 
        	//if(user.getLogin() != null)	str += user.getLogin();        	
        	
        	if(session.getAttribute("ConnectedUser") != null) {    	
        		session.removeAttribute("ConnectedUser");
        		request.getSession(false);
        		session.setAttribute("ConnectedUser",null);
        		session.invalidate();	
    			response.sendRedirect("login.jsp");
        		//request.logout();  
    			str = "log out ok";
    		}
        	else {
        		str += session.getAttribute("ConnectedUser");
        	}
        %>
        
        <%= str  %>
           
    </body>
</html>