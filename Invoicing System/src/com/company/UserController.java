package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {

    public static String addUser(User user) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // insert query for database
        String queryString = "INSERT INTO User_Details (User_ID, Name, User_Name, Password, Contact_Number, Email) " +
                "VALUES ('"+user.getUserID().toUpperCase()+"','"+user.getName().toUpperCase()+"','"+user.getUserName()+"','"+user.getPassword()+"','"+ user.getContactNumber()+"','"+user.getEmail()+"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Data added successfully");
        } else {
            return ("Data added unsuccessfully");
        }
    }

    // display all details of the products
    public static void selectAllUsers(User user) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM User_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        while (resultSet.next()) {
            setData(user,resultSet);
            user.displayAllUsers();
        }
    }

    // get a selected product details
    public static boolean selectUser(User user) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM User_Details WHERE User_ID = '"+user.getUserID().toUpperCase()+"' OR User_Name = '"+user.getUserName()+"' OR Contact_Number = '"+user.getContactNumber()+"';";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            setData(user,resultSet);
            return true;
        }
        return false;
    }


    public static String updateUser(User user) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // update query for database
        String queryString = "UPDATE User_Details SET Name = '"+user.getName().toUpperCase()+"', User_Name = '"+user.getUserName()+"', Password = '"+user.getPassword()+"', Contact_Number = '"+user.getContactNumber()+"', Email = '"+user.getEmail()+"' " +
                "WHERE User_ID = '" + user.getUserID().toUpperCase() + "';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Data updated successfully");
        } else {
            return (" Data updated unsuccessfully");
        }
    }


    public static String deleteUser(User user) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // delete query for database
        String queryString = "DELETE FROM User_Details WHERE User_ID = '" + user.getUserID().toUpperCase() + "' OR User_Name '"+user.getUserName()+"';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Data deleted successfully");
        } else {
            return (" Data deleted unsuccessfully");
        }
    }


    public static boolean selectLastUser(User user) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM User_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.last()) {
            setData(user,resultSet);
            return true;
        }
        return false;
    }


    private static void setData(User user, ResultSet resultSet) throws SQLException {
        user.setUserID(resultSet.getString("User_ID"));
        user.setName(resultSet.getString("Name"));
        user.setUserName(resultSet.getString("User_Name"));
        user.setPassword(resultSet.getString("Password"));
        user.setContactNumber(resultSet.getString("Contact_Number"));
        user.setEmail(resultSet.getString("Email"));
    }

}
