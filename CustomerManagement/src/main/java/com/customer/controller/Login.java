package com.customer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.customer.util.J2EEHttpPost;

@WebServlet("/login")
public class Login extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String authtoken = J2EEHttpPost.sendPostRequest(username, password);
//		AdminDAOImpl admin = new AdminDAOImpl();
//		Admin getdata = admin.getdata();
//		if(username.equals(getdata.getUsername()) && password.equals(getdata.getPassword())) {
//			HttpSession session = req.getSession();
//			
//			session.setAttribute("Admin", getdata);
//		}
		HttpSession session = req.getSession();
		System.out.println(authtoken);
		if (authtoken != null) {


			session.setAttribute("Admin", authtoken);
			RequestDispatcher rd = req.getRequestDispatcher("edit");
			rd.include(req, resp);
		}
		else {
			
			session.setAttribute("error message", "Entered Credentials are Invalid");
			System.out.println("error message");
			req.getRequestDispatcher("Login.jsp").forward(req, resp);
		}
	}

}
