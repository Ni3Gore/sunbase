package com.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.customer.DAOImpl.CustomerDAOImpl;
import com.customer.model.Customer;
import com.customer.util.CustomerList2;

@WebServlet("/edit")
public class Edit extends HttpServlet {

	static private CustomerDAOImpl customerDAOImpl;
	static private List<Customer> allCustomer;

	@Override
	public void init() throws ServletException {
		customerDAOImpl = new CustomerDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		HttpSession session = req.getSession();
		allCustomer = (List<Customer>) session.getAttribute("Allcustomer");
		if (allCustomer == null) {
			allCustomer = customerDAOImpl.getAllCustomer();
			session.setAttribute("Allcustomer", allCustomer);

			req.getRequestDispatcher("home.jsp").include(req, resp);
			return;
		}

		String action = req.getParameter("action");
		if (action != null) {
			if (action.equals("edit")) {
				edit(req, resp);
			} else if (action.equals("Update")) {
				update(req, resp);
			} else if (action.equals("delete")) {
				delete(req, resp);
			} else if (action.equals("Add")) {
				add(req, resp);
			} else if (action.equals("search")) {
				search(req, resp);
			} else if (action.equals("sync")) {
				sync( session);
			}
		}
		session.setAttribute("Allcustomer", allCustomer);
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	private void sync( HttpSession session) {

		String authtoken = (String) session.getAttribute("Admin");
		JSONArray getcustomerList = CustomerList2.getcustomerList(authtoken);
		for (int i = 0; i < getcustomerList.length(); i++) {
			JSONObject jsonObject = getcustomerList.getJSONObject(i);
			String id = jsonObject.getString("uuid");
			String fname = jsonObject.getString("first_name");
			String lname = jsonObject.getString("last_name");
			String street = jsonObject.getString("street");
			String address = jsonObject.getString("address");
			String city = jsonObject.getString("city");
			String state = jsonObject.getString("state");
			String email = jsonObject.getString("email");
			String phone = jsonObject.getString("phone");

			Customer customerById = customerDAOImpl.getCustomerById(id);
			Customer customer = new Customer(id, fname, lname, street, address, city, state, email, phone);
			if (customerById == null) {
				customerDAOImpl.addCustomer(customer);
			} else {
				customerDAOImpl.updateCustomer(customer);
			}
		}
		allCustomer = customerDAOImpl.getAllCustomer();
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) {

		String searchby = req.getParameter("searchby");
		String searchInput = req.getParameter("searchInput");
		System.out.println(searchInput + searchby);
		if (searchby.equals("First Name")) {

			allCustomer = customerDAOImpl.getCustomerByFirstName(searchInput);
		} else if (searchby.equals("City")) {

			allCustomer = customerDAOImpl.getCustomerByCity(searchInput);

		} else if (searchby.equals("Email")) {
			allCustomer = customerDAOImpl.getCustomerByEmail(searchInput);
		} else if (searchby.equals("Phone")) {
			allCustomer = customerDAOImpl.getCustomerByPhone(searchInput);
		}
	}

	private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("Cid");
		String FirstName = req.getParameter("First Name");
		String LastName = req.getParameter("Last Name");
		String Street = req.getParameter("Street");
		String Address = req.getParameter("Address");
		String City = req.getParameter("City");
		String State = req.getParameter("State");
		String Email = req.getParameter("Email");
		String Phone = req.getParameter("Phone");
		Customer customer = new Customer(id, FirstName, LastName, Street, Address, City, State, Email, Phone);
		int i = customerDAOImpl.addCustomer(customer);
		System.out.println(i);
		allCustomer = customerDAOImpl.getAllCustomer();
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");
		customerDAOImpl = new CustomerDAOImpl();
		int i = customerDAOImpl.deleteCustomer(id);
		System.out.println(i);
		allCustomer = customerDAOImpl.getAllCustomer();

//		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String FirstName = req.getParameter("First Name");
		String LastName = req.getParameter("Last Name");
		String Street = req.getParameter("Street");
		String Address = req.getParameter("Address");
		String City = req.getParameter("City");
		String State = req.getParameter("State");
		String Email = req.getParameter("Email");
		String Phone = req.getParameter("Phone");
		String id = req.getParameter("uid");
		Customer customer = new Customer(id, FirstName, LastName, Street, Address, City, State, Email, Phone);
		int i = customerDAOImpl.updateCustomer(customer);
		allCustomer = customerDAOImpl.getAllCustomer();
//		req.getRequestDispatcher("home.jsp").include(req, resp);
	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		System.out.println(id);
		Customer customer = customerDAOImpl.getCustomerById(id);
//	System.out.println(customer);
		req.setAttribute("customer", customer);
		req.getRequestDispatcher("editCustomer.jsp").forward(req, resp);
	}
}
