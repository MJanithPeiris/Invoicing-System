package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductController {


    public static String addProduct(Product product) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "INSERT INTO Product_Details (Product_ID, Product_Name, Description, Purchase_Price, Selling_Price, Quantity) " +
                "VALUES ('"+product.getProductID().toUpperCase()+"','"+product.getProductName().toUpperCase()+"','"+product.getDescription()+"','"+product.getPurchasePrice()+"','"+product.getSellingPrice()+"','"+product.getQty()+"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Product is added successfully");
        } else {
            return ("Product is added unsuccessfully");
        }
    }


    public static void selectAllProducts(Product product) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Product_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        TableViewer tableViewer = new TableViewer();
        product.setHeader(tableViewer);

        while (resultSet.next()) {
            setData(product, resultSet);
            product.addRows(tableViewer);
        }
        tableViewer.print();
    }


    public static boolean selectProduct(Product product) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Product_Details WHERE Product_ID = '"+product.getProductID().toUpperCase()+"' OR Product_Name LIKE '%"+product.getProductName().toUpperCase()+"%';";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            setData(product, resultSet);
            return true;
        }
        return false;
    }


    public static String updateProduct(Product product) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "UPDATE Product_Details SET Product_Name = '"+product.getProductName().toUpperCase()+"', Description = '"+product.getDescription()+"', Purchase_Price = '"+product.getPurchasePrice()+"', Selling_Price = '"+product.getSellingPrice()+"', Quantity = '"+product.getQty()+"' " +
                "WHERE Product_ID = '" + product.getProductID().toUpperCase() + "';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Product is updated successfully");
        } else {
            return (" Product is updated unsuccessfully");
        }
    }


    public static String deleteProduct(Product product) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "DELETE FROM Product_Details WHERE Product_ID = '" + product.getProductID().toUpperCase() + "' OR Product_Name LIKE '%"+product.getProductName().toUpperCase()+"%';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Product is deleted successfully");
        } else {
            return (" Product is deleted unsuccessfully");
        }
    }


    public static boolean selectLastProduct(Product product) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Product_Details;";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.last()) {
            setData(product, resultSet);
            return true;
        }
        return false;
    }


    private static void setData(Product product,ResultSet resultSet) throws SQLException {
        product.setProductID(resultSet.getString("Product_ID"));
        product.setProductName(resultSet.getString("Product_Name"));
        product.setDescription(resultSet.getString("Description"));
        product.setPurchasePrice(Double.parseDouble(resultSet.getString("Purchase_Price")));
        product.setSellingPrice(Double.parseDouble(resultSet.getString("Selling_Price")));
        product.setQty(Integer.parseInt(resultSet.getString("Quantity")));
    }
}
