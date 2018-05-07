<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- Main content -->
<main>
		<form action="cars?action=registerCar" method="post">
		Brand:<input type="text" name="brand"/><br/><br/>
		Year:<input type="text" name="year"/><br/><br/>
		Price:<input type="text" name="price"/><br/><br/>
		
		<input type="submit" value="register"/>
		<p>Message: ${message}</p>
	</form> 
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>