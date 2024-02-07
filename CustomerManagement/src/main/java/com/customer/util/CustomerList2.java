package com.customer.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class CustomerList2 {

	public static void main(String[] args) {
		// Specify the bearer token received from the authentication API call
    	String bearerToken="";
		getcustomerList(bearerToken);
	}

	public static JSONArray getcustomerList(String bearerToken) {		
		
        // Specify the customer list endpoint URL
        String customerListUrl = "https://qa.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";

        try {
        	
            // Create a URL object from the customer list endpoint URL
            URL url = new URL(customerListUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Set the authorization header with the bearer token
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);

            // Get the response code from the connection
            int responseCode = connection.getResponseCode();

            // Check if the response code indicates success (200 OK)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response from the connection input stream
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Parse JSON response
                JSONArray jsonArray = new JSONArray(response.toString());

                // Print user details
                System.out.println("Customer details:");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println("Uuid: " + jsonObject.getString("uuid"));
                    System.out.println("First Name: " + jsonObject.getString("first_name"));
                    System.out.println("Last Name: " + jsonObject.getString("last_name"));
                    System.out.println("Street: " + jsonObject.getString("street"));
                    System.out.println("Address: " + jsonObject.getString("address"));
                    System.out.println("City: " + jsonObject.getString("city"));
                    System.out.println("State: " + jsonObject.getString("state"));
                    System.out.println("Email: " + jsonObject.getString("email"));
                    System.out.println("Phone: " + jsonObject.getString("phone"));
                    System.out.println();
                    
                }
                return jsonArray;
            } else {
                // Print an error message if fetching the customer list fails
                System.out.println("Failed to fetch customer list. Response code: " + responseCode);
            }
        } catch (Exception e) {
            // Print any exceptions that occur during the process
            e.printStackTrace();
        }
		return null;
    
	}

}
