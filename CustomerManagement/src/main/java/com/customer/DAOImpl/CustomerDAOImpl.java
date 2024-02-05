package com.customer.DAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.customer.DAO.CustomerDAO;
import com.customer.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	static final String INSERT_QUERY = "INSERT INTO `customer_details` ( `id`,`fname`, `lname`,`street`, `address`, `city`, `state`, `email`, `phone`) VALUES (?,?,?,?,?,?,?,?,?)";
	static final String UPDATE_QUERY = "update `customer_details` set `fname`=?, `lname`=?,`street`=?, `address`=?, `city`=?, `state`=?, `email`=?, `phone`=? where (`id`=?)";
	static final String DELETE_QUERY = "DELETE FROM `customer_details` WHERE (`id`=?)";
	static final String SELECT_QUERY = "SELECT * from `customer_details` ";
	static final String SELECT_QUERY1 = "SELECT * from `customer_details` where `fname` Like ? ";
	static final String SELECT_QUERY2 = "SELECT * from `customer_details` where `city`Like ? ";
	static final String SELECT_QUERY3 = "SELECT * from `customer_details` where `email`Like ? ";
	static final String SELECT_QUERY4 = "SELECT * from `customer_details` where `phone` Like ? ";
	static final String SELECT_QUERY5 = "SELECT * from `customer_details` where `id` = ? ";

	static private Connection connection;
	static private PreparedStatement prepareStatement;
	static private Statement statement;
	static private ResultSet res;

	public CustomerDAOImpl() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db_management", "root",
					"root");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int addCustomer(Customer c) {
		try {
			prepareStatement = connection.prepareStatement(INSERT_QUERY);
			prepareStatement.setString(1, c.getId().trim());
			prepareStatement.setString(2, c.getFname().trim());
			prepareStatement.setString(3, c.getLname().trim());
			prepareStatement.setString(4, c.getStreet().trim());
			prepareStatement.setString(5, c.getAddress().trim());
			prepareStatement.setString(6, c.getCity().trim());
			prepareStatement.setString(7, c.getState().trim());
			prepareStatement.setString(8, c.getEmail().trim());
			prepareStatement.setString(9, c.getPhone().trim());
			
			return prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCustomer(Customer c) {

		try {
			prepareStatement = connection.prepareStatement(UPDATE_QUERY);
			prepareStatement.setString(1, c.getFname().trim());
			prepareStatement.setString(2, c.getLname().trim());
			prepareStatement.setString(3, c.getStreet().trim());
			prepareStatement.setString(4, c.getAddress().trim());
			prepareStatement.setString(5, c.getCity().trim());
			prepareStatement.setString(6, c.getState().trim());
			prepareStatement.setString(7, c.getEmail().trim());
			prepareStatement.setString(8, c.getPhone().trim());
			prepareStatement.setString(9, c.getId().trim());
			return prepareStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public Customer getCustomerById(String id) {

		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY5);
			prepareStatement.setString(1, id.trim());
			 ResultSet res = prepareStatement.executeQuery();
			while (res.next()) {
				Customer customer = new Customer(res.getString("id"), res.getString("fname"), res.getString("lname"),
						res.getString("street"),res.getString("address"), res.getString("city"), res.getString("state"), res.getString("email"),
						res.getString("phone"));
				
				return customer;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Customer> getCustomerByFirstName(String fname) {

		List<Customer> list = new ArrayList<Customer>();
		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY1);
			prepareStatement.setString(1, fname.trim()+"%");
			res = prepareStatement.executeQuery();
			while (res.next()) {
				Customer customer = new Customer(res.getString("id"), res.getString("fname"), res.getString("lname"),
						res.getString("street"),res.getString("address"), res.getString("city"), res.getString("state"), res.getString("email"),
						res.getString("phone"));
				
				System.out.println(res.getString("fname"));
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(list.get(0).getFname());
		return list;
	}

	@Override
	public List<Customer> getCustomerByCity(String city) {

		List<Customer> list = new ArrayList<Customer>();
		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY2);
			prepareStatement.setString(1,city.trim()+"%");
			res =  prepareStatement.executeQuery();
			while (res.next()) {
				Customer customer = new Customer(res.getString("id"), res.getString("fname"), res.getString("lname"),
						res.getString("street"),res.getString("address"), res.getString("city"), res.getString("state"), res.getString("email"),
						res.getString("phone"));
				
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Customer> getCustomerByEmail(String email) {

		List<Customer> list = new ArrayList<Customer>();
		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY3);
			prepareStatement.setString(1, email.trim()+"%");
			res = prepareStatement.executeQuery();
			while (res.next()) {
				Customer customer = new Customer(res.getString("id"), res.getString("fname"), res.getString("lname"),
						res.getString("street"),res.getString("address"), res.getString("city"), res.getString("state"), res.getString("email"),
						res.getString("phone"));
				list.add(customer);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	
	}

	@Override
	public List<Customer> getCustomerByPhone(String phone) {

			List<Customer> list = new ArrayList<Customer>();
		try {
			prepareStatement = connection.prepareStatement(SELECT_QUERY4);
			prepareStatement.setString(1, phone.trim()+"%");
			res = prepareStatement.executeQuery();
			while (res.next()) {
				Customer customer = new Customer(res.getString("id"), res.getString("fname"), res.getString("lname"),
						res.getString("street"),res.getString("address"), res.getString("city"), res.getString("state"), res.getString("email"),
						res.getString("phone"));
				
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Customer> getAllCustomer() {

		List<Customer> list = new ArrayList<Customer>();
		try {
			statement = connection.createStatement();
			res = statement.executeQuery(SELECT_QUERY);
			while (res.next()) {
				Customer customer = new Customer(res.getString("id"), res.getString("fname"), res.getString("lname"),
						res.getString("street"),res.getString("address"), res.getString("city"), res.getString("state"), res.getString("email"),
						res.getString("phone"));
				
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteCustomer(String id) {

		try {
			prepareStatement = connection.prepareStatement(DELETE_QUERY);
			prepareStatement.setString(1, id.trim());
			int i = prepareStatement.executeUpdate();
			Statement createStatement = connection.createStatement();
			String reset = "ALTER TABLE `customer_details` AUTO_INCREMENT = 1";
			createStatement.execute(reset);
			return i;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	
	

}
