<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>
</head>
<body>
	<form action="LoginServlet" method="post">  
		Username:<input type="text" name="username"/><br/><br/>  
		Password:<input type="password" name="password"/><br/><br/>  
		<input type="submit" value="login"/>
		<a href="register.jsp">register</a>
	</form>
	<p>Message: ${message}</p>
</body>
</html>