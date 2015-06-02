<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Billing Console</title>
</head>
<body>
<%
String user = null;
if(session.getAttribute("user")==null){
	response.sendRedirect("login.jsp");
}
else
	user = (String) session.getAttribute("user");
	String userName = null;
	String sessionId = null;
	
	Cookie[] cookies = request.getCookies();
	if(cookies!=null){
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")) userName = cookie.getValue();
			if(cookie.getName().equals("JSESSIONID")) sessionId = cookie.getValue();
		}
	}
		

%>
<h2>Welcome, <%=user%></h2>
<h3>Session ID is, <%= sessionId %></h3>
</body>
</html>