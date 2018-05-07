<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Main content -->
<main>
	<form action="users?action=registerUser" method="post">
		Firstname:<input type="text" name="firstname"/><br/><br/>
		Lastname:<input type="text" name="lastname"/><br/><br/> 
		Username:<input type="text" name="username"/><br/><br/>  
		Password:<input type="password" name="password"/><br/><br/>
		Re-type Password:<input type="password" name="passwordRetyped"/><br/><br/>
		
		<input type="submit" value="register"/>
		<p>Message: ${message}</p>
	</form>
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>