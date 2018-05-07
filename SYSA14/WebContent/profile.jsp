<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Assemblage.UserBean, Assemblage.CarBean, java.util.ArrayList" %>

<!-- Main content -->
<main>

<% UserBean viewingUser = (UserBean) session.getAttribute("viewingUser"); %>
<% ArrayList<CarBean> cars = (ArrayList<CarBean>) session.getAttribute("viewingUserCars"); %>

<% boolean yourProfile = false; %>
<% if (viewingUser.getUsername().equals(currentUser.getUsername())) { %>
	<% yourProfile = true; //Viewing your own profile %>
<% } %>

	<div class="container">
	<% if (yourProfile) { %>
		 <h1 class="jumbotron-heading">Your details</h1>
	<% } else { %>
		<h1 class="jumbotron-heading">Details for <%= viewingUser.getUsername() %></h1>
	<% } %>
	
	
		<dl class="row">
	  		<dt class="col-sm-3">Username</dt>
	  		<dd class="col-sm-9"><%= viewingUser.getUsername() %></dd>
	  		<dt class="col-sm-3">Firstname</dt>
	  		<dd class="col-sm-9"><%= viewingUser.getFirstName() %></dd>
	  		<dt class="col-sm-3">Lastname</dt>
	  		<dd class="col-sm-9"><%= viewingUser.getLastName() %></dd>
	  		<dt class="col-sm-3">Number of cars owned</dt>
	  		<dd class="col-sm-9"><%= cars.size() %></dd>
  		</dl>
	</div>

	<div class="container">
	<% if (yourProfile) { %>
		 <h1 class="jumbotron-heading">Cars owned by you</h1>
	<% } else { %>
		<h1 class="jumbotron-heading">Cars owned by <%= viewingUser.getUsername() %></h1>
	<% } %>
	</div>

	<!-- Car album -->
	<div class="album py-5 bg-light">
		<div class="container">
			<div class="row">
	<% for(CarBean car : cars){ %>
				<!-- Car card -->
				<div class="col-md-4">
					<div class="card mb-4 box-shadow">
					<!-- <img class="card-img-top" data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail" alt="Card image"> -->
                		<div class="card-body">
							<h5 class="card-title"><%= car.getBrand() %></h5>
                    		<h6 class="card-subtitle mb-2 text-muted"><%= car.getYear() %></h6>
                    		<h6 class="card-subtitle mb-2 text-muted"><%= car.getPrice() %> SEK</h6>
                  			<p class="card-text">This is a wider card with supporting text below as a natural lead-in to additional content. This content is a little bit longer.</p>
                    
                  			<div class="d-flex justify-content-between align-items-center">
                    			 <% if (car.isForSale() && yourProfile) { %>
                    				<a href="cars?action=stopSelling&carId=<%= car.getId() %>" class="btn btn-warning my-2">Stop selling</a>
                    			<% } %>
                    			<% if (!car.isForSale() && yourProfile) { %>
                    				<a href="cars?action=sellCar&carId=<%= car.getId() %>" class="btn btn-success my-2">Sell</a>
                    			<% } %>
                    			<% if (!car.isForSale() && !yourProfile) { %>
                    				<h6 class="card-subtitle mb-2 text-muted">Not for sale</h6>
                    			<% } %>
                  				<% if (car.isForSale() && !yourProfile) { %>
                    				<a href="cars?action=buyCar&carId=<%= car.getId() %>" class="btn btn-primary my-2">Buy</a>
                    			<% } %>
                    			
                      			<a href="#" class="card-link text-muted"><%= car.getOwnerUsername() %></a>
                  			</div>
                		</div>
              		</div>
            	</div>
    			<!-- End Car card -->
	<% } %>
			</div>
        </div>
    </div>
	<!-- End Car album -->
	
</main>
<!-- End Main content -->

<%@ include file="footer.jsp" %>