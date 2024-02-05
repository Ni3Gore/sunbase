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
<%
	Customer customer = (Customer) request.getAttribute("customer");
%>
		<form action="edit" method="get">
			<input type="text" name="First Name" value="<%=customer.getFname() %>" placeholder="First Name">
			<input type="text" name="Last Name" value="<%=customer.getLname() %>" placeholder="Last Name">
			<input type="text" name="Street" value="<%=customer.getStreet() %>" placeholder="Street">
			<input type="text" name="Address" value="<%=customer.getAddress() %>" placeholder="Address"> 
			<input type="text" name="City" value="<%=customer.getCity() %>" placeholder="City"> 
			<input	type="text" name="State" value="<%=customer.getState() %>" placeholder="State"> 
			<input	type="email" name="Email" value="<%=customer.getEmail() %>" placeholder="Email"> 
			<input	type="text" name="Phone" value="<%=customer.getPhone() %>" placeholder="Phone"> 
			<input type="hidden" name ="uid" value="<%=customer.getId() %>">
			<input	type="submit" value="Update" name="action">
		</form>
	</div>
</body>
</html>