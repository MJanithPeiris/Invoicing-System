package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductController {
    private Connection connection;
    private Product product;

    public ProductController(){

    }

    public ProductController(Product product) throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        connection = connector.getDBConnection();
        this.product = product;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    // add details to the product details table
    public String addProduct() throws SQLException {

        Statement statement = connection.createStatement();
        // insert query for database
        String queryString = "INSERT INTO Product_Details (Product_ID, Product_Name, Description, Purchase_Price, Selling_Price, Quantity) " +
                "VALUES ('"+product.getProductID().toUpperCase()+"','"+product.getProductName().toUpperCase()+"','"+product.getDescription()+"','"+product.getPurchasePrice()+"','"+product.getSellingPrice()+"','"+product.getQty()+"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Data added successfully");
        } else {
            return ("Data added unsuccessfully");
        }
    }

    // display all details of the products
    public void selectAllProducts() throws SQLException{

        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Product_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        while (resultSet.next()) {

            product.setProductID(resultSet.getString("Product_ID"));
            product.setProductName(resultSet.getString("Product_Name"));
            product.setDescription(resultSet.getString("Description"));
            product.setPurchasePrice(Double.parseDouble(resultSet.getString("Purchase_Price")));
            product.setSellingPrice(Double.parseDouble(resultSet.getString("Selling_Price")));
            product.setQty(Integer.parseInt(resultSet.getString("Quantity")));

            product.displayAllProducts();
        }
    }

    // get a selected product details
    public boolean selectProduct() throws SQLException{

        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Product_Details WHERE Product_ID = '"+product.getProductID().toUpperCase()+"' OR Product_Name LIKE '%"+product.getProductName().toUpperCase()+"%'";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            product.setProductID(resultSet.getString("Product_ID"));
            product.setProductName(resultSet.getString("Product_Name"));
            product.setDescription(resultSet.getString("Description"));
            product.setPurchasePrice(Double.parseDouble(resultSet.getString("Purchase_Price")));
            product.setSellingPrice(Double.parseDouble(resultSet.getString("Selling_Price")));
            product.setQty(Integer.parseInt(resultSet.getString("Quantity")));
            return true;
        }
//        product.displayDetails();
        return false;
    }


    public String updateProduct()throws SQLException{

        Statement statement = connection.createStatement();
        // update query for database
        String queryString = "UPDATE Product_Details SET Product_Name = '"+product.getProductName().toUpperCase()+"', Description = '"+product.getDescription()+"', Purchase_Price = '"+product.getPurchasePrice()+"', Selling_Price = '"+product.getSellingPrice()+"', Quantity = '"+product.getQty()+"' " +
                "WHERE Product_ID = '" + product.getProductID().toUpperCase() + "';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Data updated successfully");
        } else {
            return (" Data updated unsuccessfully");
        }
    }


    public String deleteProduct() throws SQLException{
        Statement statement = connection.createStatement();
        // delete query for database
        String queryString = "DELETE FROM Product_Details WHERE Product_ID = '" + product.getProductID().toUpperCase() + "' OR Product_Name LIKE '%"+product.getProductName().toUpperCase()+"%';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Data deleted successfully");
        } else {
            return (" Data deleted unsuccessfully");
        }
    }


    public boolean selectLastProduct() throws SQLException{

        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Product_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        if(resultSet.last()) {
            product.setProductID(resultSet.getString("Product_ID"));
            product.setProductName(resultSet.getString("Product_Name"));
            product.setDescription(resultSet.getString("Description"));
            product.setPurchasePrice(Double.parseDouble(resultSet.getString("Purchase_Price")));
            product.setSellingPrice(Double.parseDouble(resultSet.getString("Selling_Price")));
            product.setQty(Integer.parseInt(resultSet.getString("Quantity")));
        }else{
            return false;
        }
        return true;
    }
}
