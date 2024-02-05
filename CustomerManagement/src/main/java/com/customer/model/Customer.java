package com.customer.model;

public class Customer {

	private String  id,fname,lname,street,address,city,state,email,phone;

	public Customer() {
		super();
	}

	public Customer(String fname, String lname,String street, String address, String city, String state, String email, String phone) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}

	public Customer(String id,String fname, String lname, String street, String address, String city, String state, String email, String phone) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [fname=" + fname + ", lname=" + lname + ", address=" + address + ", city=" + city + ", state="
				+ state + ", email=" + email + ", phone=" + phone + "]";
	}

	
	
}
