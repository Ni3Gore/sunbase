
<%@page import="com.customer.DAOImpl.CustomerDAOImpl"%>
<%@ page import="java.util.List"%>
<%@ page import="com.customer.DAO.CustomerDAO"%>
<%@ page import="com.customer.model.Customer"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Customer Details</title>
<link rel="stylesheet" type="text/css" href="cust.css">
</head>
<body>
	<h2>Customer List</h2>
	<%
	String message = (String)session.getAttribute("message");
	if(message!=null && message.isEmpty()){
		%>
		<h3><%=message %></h3>
		<%
	}
	%>

	<%
	List<Customer> allcustomers = (List<Customer>) session.getAttribute("Allcustomer");
	/*CustomerDAOImpl CustomerDAOImpl = new  CustomerDAOImpl();
	 List<Customer> allcustomers = CustomerDAOImpl.getAllCustomer(); 
	// Fetch all customers
	 List<Customer> customers = customerDAO.getAllCustomer(); */

	// Check if the list is not empty
	String admin = (String) session.getAttribute("Admin");

	if (allcustomers != null) {
		// Pagination parameters
		int currentPage = 1;
		int recordsPerPage = 5;

		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		int startRecord = (currentPage - 1) * recordsPerPage;
		int endRecord = Math.min(startRecord + recordsPerPage, allcustomers.size());

		List<Customer> customers1 = allcustomers.subList(startRecord, endRecord);

		if (admin != null) {
	%>
	<div class="add-customer-btn">
		<a href="Add.jsp"><button id="add-customer-btn">Add
				Customer</button></a> 
				<a href="edit?&action=sync"><input type="submit" value="Sync"></a>
				<a href="logout"><input type="submit" value="logut" class=""></a>
	</div>
	<div class="search-dropdown">
		<form action="edit">
			<input type="hidden" name="action" value="search"> <select
				id="search-dropdown" name="searchby" onchange="showSearchBox()">
				<option value="" >Select Search By</option>
				<option value="First Name">First Name</option>
				<option value="City">City</option>
				<option value="Email">Email</option>
				<option value="Phone">Phone</option>
			</select> <input type="text" name="searchInput" id="search-input"
				placeholder="Enter search term"> 
				<input type="submit" value="Search">
		</form>
	</div>
<script>
		function showSearchBox() {
			var selectedOption = document.getElementById("search-dropdown").value;
			var searchInput = document.getElementById("search-input");
			var button = document.getElementById("search-submit-btn");

			if (selectedOption !== "") {
				searchInput.style.display = "inline-block";
				searchInput.placeholder = "Enter " + selectedOption
						+ " to search";
				button.style.display = "inline-block";
			} else {
				searchInput.style.display = "none";
				button.style.display = "none";
			}

		}
	</script>
	<table>
		<tr>
			
			<th>First Name</th>
			<th>Last Name</th>
			<th>Address</th>
			<th>City</th>
			<th>State</th>
			<th>Email</th>
			<th>Phone</th>
			<th>Action</th>
		</tr>
		<%
		for (Customer customer : customers1) {
		%>
		<tr>
			
			<td><%=customer.getFname()%></td>
			<td><%=customer.getLname()%></td>
			<td><%=customer.getAddress()%></td>
			<td><%=customer.getCity()%></td>
			<td><%=customer.getState()%></td>
			<td><%=customer.getEmail()%></td>
			<td><%=customer.getPhone()%></td>
			<td class="action-buttons"><a
				href="edit?id=<%=customer.getId()%>&action=edit" class="edit-btn">Edit</a>
				<a href="edit?id=<%=customer.getId()%>&action=delete"><input
					type="submit" value="Delete" class="delete-btn"
					onclick="return confirm('Are you sure you want to delete this customer?');"></a></td>

		</tr>
		
		<%
		}
		%>
	</table>
	
	<!-- Pagination links -->
	<!-- Pagination links -->
	<div class="pagination">
		<%-- Previous button --%>
		<%
		if (currentPage > 1) {
		%>
		<a href="home.jsp?page=<%=currentPage - 1%>">Previous</a>
		<%
		}
		%>

		<%-- Page numbers --%>
		<%
		for (int i = 1; i <= Math.ceil((double) allcustomers.size() / recordsPerPage); i++) {
		%>
		<%
		if (i == currentPage) {
		%>
		<span class="current-page"><%=i%></span>
		<%
		} else {
		%>
		<a href="home.jsp?page=<%=i%>"><%=i%></a>
		<%
		}
		%>
		<%
		}
		%>

		<%-- Next button --%>
		<%
		if (currentPage < Math.ceil((double) allcustomers.size() / recordsPerPage)) {
		%>
		<a href="home.jsp?page=<%=currentPage + 1%>">Next</a>
		<%
		}
		%>
	</div>
	<%
	} else if (allcustomers == null && admin == null) {
	%>
	<p>
		Not Authorized, Please <a href="Login.jsp">Login.</a>
	</p>

	<%
	} else {
	%>
	<p>
		No customers found.<a href="Login.jsp">Login</a>
	</p>
	<%
	}
	} else {
	%>
	<p>
		No customers found, please <a href="Login.jsp">Login.</a>
	</p>
	<%
	}
	%>

	
</body>
</html>
