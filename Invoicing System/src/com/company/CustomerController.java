package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerController {

    private Connection connection;
    private Customer customer;

    public CustomerController(){

    }

    public CustomerController(Customer product) throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        connection = connector.getDBConnection();
        this.customer = product;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    // add customer details to the customer details table
    public String addCustomer() throws SQLException {

        Statement statement = connection.createStatement();
        String queryString = "INSERT INTO Customer_Details (Customer_ID, Customer_Name, Email, Address, Contact_Number, Date_of_Birth, Gender) " +
                "VALUES ('"+ customer.getCustomerID().toUpperCase()+"','"+ customer.getCustomerName().toUpperCase()+"','"+ customer.getEmail()+"','"+ customer.getAddress()+"','"+ customer.getContactNumber()+"','"+ customer.getDob()+"','"+ customer.getGender()+"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Data added successfully");
        } else {
            return ("Data added unsuccessfully");
        }
    }

    // display all details of the customers
    public void selectAllCustomers() throws SQLException{

        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Customer_Details;";
        ResultSet resultSet = statement.executeQuery(queryString);

        while (resultSet.next()) {

            customer.setCustomerID(resultSet.getString("Customer_ID"));
            customer.setCustomerName(resultSet.getString("Customer_Name"));
            customer.setEmail(resultSet.getString("Email"));
            customer.setAddress(resultSet.getString("Address"));
            customer.setContactNumber(resultSet.getString("Contact_Number"));
            customer.setDob(resultSet.getString("Date_of_Birth"));
            customer.setGender(resultSet.getString("Gender"));

            customer.displayAllCustomers();
        }
    }

    // get a selected customer details
    public boolean selectCustomer() throws SQLException{

        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Customer_Details WHERE Customer_ID = '"+customer.getCustomerID().toUpperCase()+"' OR Contact_Number ='"+customer.getContactNumber()+"';";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            customer.setCustomerID(resultSet.getString("Customer_ID"));
            customer.setCustomerName(resultSet.getString("Customer_Name"));
            customer.setEmail(resultSet.getString("Email"));
            customer.setAddress(resultSet.getString("Address"));
            customer.setContactNumber(resultSet.getString("Contact_Number"));
            customer.setDob(resultSet.getString("Date_of_Birth"));
            customer.setGender(resultSet.getString("Gender"));
            return true;
        }
//        customer.displayDetails();
        return false;
    }

    public String updateCustomer()throws SQLException{

        Statement statement = connection.createStatement();
        // update query for database
        String queryString = "UPDATE Customer_Details SET Customer_Name = '"+ customer.getCustomerName().toUpperCase()+"', Email = '"+ customer.getEmail()+"', Address = '"+ customer.getAddress()+"', Contact_Number = '"+ customer.getContactNumber()+"', Date_of_Birth = '"+ customer.getDob()+"' , Gender = '"+ customer.getGender()+"'" +
                "WHERE Customer_ID = '" + customer.getCustomerID().toUpperCase() + "';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Data updated successfully");
        } else {
            return (" Data updated unsuccessfully");
        }
    }

    public String deleteCustomer() throws SQLException{
        Statement statement = connection.createStatement();
        // delete query for database
        String queryString = "DELETE FROM Customer_Details WHERE Customer_ID = '" + customer.getCustomerID().toUpperCase() + "' OR Contact_Number ='"+customer.getContactNumber()+"';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Data deleted successfully");
        } else {
            return (" Data deleted unsuccessfully");
        }
    }

    public boolean selectLastCustomer() throws SQLException{

        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Customer_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        if(resultSet.last()) {
            customer.setCustomerID(resultSet.getString("Customer_ID"));
            customer.setCustomerName(resultSet.getString("Customer_Name"));
            customer.setEmail(resultSet.getString("Email"));
            customer.setAddress(resultSet.getString("Address"));
            customer.setContactNumber(resultSet.getString("Contact_Number"));
            customer.setDob(resultSet.getString("Date_of_Birth"));
            customer.setGender(resultSet.getString("Gender"));
        }else{
            return false;
        }
        return true;
    }
}
