<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%
// Header


response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");// HTTP 1.1

response.setHeader("Pragma","no-cache");// HTTP 1.0

response.setHeader("Expires","0");// Proxies


if(session.getAttribute("username")==null)// It is set based on the browser so everytime you login through new browser it will create a new session.
{
	response.sendRedirect("login.jsp");
}

%>


<div align="right">


<div align="center">
<h3>Welcome to the Home Page</h3>
<a href="videos.jsp">Videos</a>
</div>

Welcome ${username}  <!-- JS TL -->
<form action="Logout">
<input type="submit" value="Logout">

</form>
</div>



</body>
</html>