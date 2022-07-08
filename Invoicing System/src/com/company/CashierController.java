package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CashierController {

    public static String addCashier(Cashier cashier) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // insert query for database
        String queryString = "INSERT INTO Cashier_Details (Cashier_ID, Cashier_Name, User_Name, Password, Address, Contact_Number, Email) " +
                "VALUES ('"+ cashier.getCashierID().toUpperCase()+"','"+ cashier.getCashierName().toUpperCase()+"','"+ cashier.getUserName()+"','"+ cashier.getPassword()+"','" + cashier.getAddress() +"','"+ cashier.getContactNumber()+"','"+ cashier.getEmail()+"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Cashier is added successfully");
        } else {
            return ("Cashier is added unsuccessfully");
        }
    }

    // display all details of the products
    public static void selectAllCashiers(Cashier cashier) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Cashier_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        TableViewer tableViewer = new TableViewer();
        cashier.setHeader(tableViewer);

        while (resultSet.next()) {
            setData(cashier,resultSet);
            cashier.addRows(tableViewer);
        }
        tableViewer.print();
    }

    // get a selected product details
    public static boolean selectCashier(Cashier cashier) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Cashier_Details WHERE Cashier_ID = '"+ cashier.getCashierID().toUpperCase()+"' OR User_Name = '"+ cashier.getUserName()+"' OR Contact_Number = '"+ cashier.getContactNumber()+"';";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            setData(cashier,resultSet);
            return true;
        }
        return false;
    }


    public static String updateCashier(Cashier cashier) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // update query for database
        String queryString = "UPDATE Cashier_Details SET Cashier_Name = '"+ cashier.getCashierName().toUpperCase()+"', User_Name = '"+ cashier.getUserName()+"', Password = '"+ cashier.getPassword()+"', Address = '"+ cashier.getAddress() + "', Contact_Number = '"+ cashier.getContactNumber()+"', Email = '"+ cashier.getEmail()+"' " +
                "WHERE Cashier_ID = '" + cashier.getCashierID() + "';";

        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Cashier is updated successfully");
        } else {
            return (" Cashier is updated unsuccessfully");
        }
    }


    public static String deleteCashier(Cashier cashier) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // delete query for database
        String queryString = "DELETE FROM Cashier_Details WHERE Cashier_ID = '" + cashier.getCashierID().toUpperCase() + "' OR User_Name '"+ cashier.getUserName()+"';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Cashier is deleted successfully");
        } else {
            return (" Cashier is deleted unsuccessfully");
        }
    }


    public static boolean selectLastCashier(Cashier cashier) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Cashier_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.last()) {
            setData(cashier,resultSet);
            return true;
        }
        return false;
    }


    private static void setData(Cashier cashier, ResultSet resultSet) throws SQLException {
        cashier.setCashierID(resultSet.getString("Cashier_ID"));
        cashier.setCashierName(resultSet.getString("Cashier_Name"));
        cashier.setUserName(resultSet.getString("User_Name"));
        cashier.setPassword(resultSet.getString("Password"));
        cashier.setAddress(resultSet.getString("Address"));
        cashier.setContactNumber(resultSet.getString("Contact_Number"));
        cashier.setEmail(resultSet.getString("Email"));
    }

}
