package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerController {

    // add customer details to the customer details table
    public static String addCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "INSERT INTO Customer_Details (Customer_ID, Customer_Name, Email, Address, Contact_Number, Date_of_Birth, Gender) " +
                "VALUES ('"+ customer.getCustomerID().toUpperCase()+"','"+ customer.getCustomerName().toUpperCase()+"','"+ customer.getEmail()+"','"+ customer.getAddress()+"','"+ customer.getContactNumber()+"','"+ customer.getDob()+"','"+ customer.getGender()+"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Customer is added successfully");
        } else {
            return ("Customer is added unsuccessfully");
        }
    }

    // display all details of the customers
    public static void selectAllCustomers(Customer customer) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Customer_Details;";
        ResultSet resultSet = statement.executeQuery(queryString);

        TableViewer tableViewer = new TableViewer();
        customer.setHeader(tableViewer);

        while (resultSet.next()) {
            setData(customer, resultSet);
            customer.addRows(tableViewer);
        }
        tableViewer.print();
    }

    // get a selected customer details
    public static boolean selectCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Customer_Details WHERE Customer_ID = '"+customer.getCustomerID().toUpperCase()+"' OR Contact_Number ='"+customer.getContactNumber()+"';";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            setData(customer, resultSet);
            return true;
        }
        return false;
    }


    public static String updateCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // update query for database
        String queryString = "UPDATE Customer_Details SET Customer_Name = '"+ customer.getCustomerName().toUpperCase()+"', Email = '"+ customer.getEmail()+"', Address = '"+ customer.getAddress()+"', Contact_Number = '"+ customer.getContactNumber()+"', Date_of_Birth = '"+ customer.getDob()+"' , Gender = '"+ customer.getGender()+"'" +
                "WHERE Customer_ID = '" + customer.getCustomerID().toUpperCase() + "';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Customer is updated successfully");
        } else {
            return (" Customer is updated unsuccessfully");
        }
    }


    public static String deleteCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // delete query for database
        String queryString = "DELETE FROM Customer_Details WHERE Customer_ID = '" + customer.getCustomerID().toUpperCase() + "' OR Contact_Number ='"+customer.getContactNumber()+"';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Customer is deleted successfully");
        } else {
            return (" Customer is deleted unsuccessfully");
        }
    }


    public static boolean selectLastCustomer(Customer customer) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Customer_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.last()) {
            setData(customer, resultSet);
            return true;
        }
        return false;
    }


    private static void setData(Customer customer, ResultSet resultSet) throws SQLException {
        customer.setCustomerID(resultSet.getString("Customer_ID"));
        customer.setCustomerName(resultSet.getString("Customer_Name"));
        customer.setEmail(resultSet.getString("Email"));
        customer.setAddress(resultSet.getString("Address"));
        customer.setContactNumber(resultSet.getString("Contact_Number"));
        customer.setDob(resultSet.getString("Date_of_Birth"));
        customer.setGender(resultSet.getString("Gender"));
    }
}
