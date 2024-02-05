<%@page import="com.customer.model.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<link rel="stylesheet" href="editCustomer.css">
<body>
	<div class="container">
		<h2>Customer Details</h2>

		<form action="edit" method="get">
			<input type="text" name="Cid"  placeholder="Uuid">
			<input type="text" name="First Name"  placeholder="First Name">
			<input type="text" name="Last Name"  placeholder="Last Name">
			<input type="text" name="Street" placeholder="Street">
			<input type="text" name="Address"  placeholder="Address"> 
			<input type="text" name="City" placeholder="City"> 
			<input	type="text" name="State" placeholder="State"> 
			<input	type="email" name="Email" placeholder="Email"> 
			<input	type="text" name="Phone"  placeholder="Phone"> 
			<input type="hidden" name ="uid">
			<input	type="submit" value="Add" name="action">
		</form>
	</div>
</body>
</html>