package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InvoiceController {

    private Connection connection;
    private Invoice invoice;

    public InvoiceController(){

    }

    public InvoiceController(Invoice invoice) throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        connection = connector.getDBConnection();
        this.invoice = invoice;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String addInvoice() throws SQLException {
        Statement statement = connection.createStatement();
        // insert query for database
        String queryString = "INSERT INTO Invoice_Details (Invoice_Number, Date, Check_In_Time, Check_Out_Time, Products, Units_Per_Product, Unit_Price_Per_Product, Discount_Per_Product, Sub_Total, Total_Discount, Total, Payment_Method, Cash_Amount, Balance, Customer_ID, Customer_Name, Contact_Number) " +
                "VALUES ('"+ invoice.getInvoiceNumber() +"','"+ invoice.getCurrentDate() +"','"+ invoice.getCurrentDate() +"','"+ invoice.getCheckInTime() +"','"+ invoice.getCheckOutTime() +"','"+ getProductList() +"','"+ getUnitsPerProductList() +"','"+ getPricePerProductList() +"','"+ getDiscountPerProductList() +"','"+ invoice.getSubTotal() +"','"+ invoice.getTotalDiscount() +"','"+ invoice.getTotalPrice() +"','"+ invoice.getPaymentMethod() +"','"+ invoice.getCashAmount() +"','"+ invoice.getBalanceAmount() +"','"+ invoice.getCustomerID() +"','"+ invoice.getCustomerName() +"','"+ invoice.getCustomerContactNumber() +"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Invoice added successfully");
        } else {
            return ("Invoice added unsuccessfully");
        }
    }

    public void selectAllInvoices() throws SQLException {
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Invoice_Details;";
        ResultSet resultSet = statement.executeQuery(queryString);

        while (resultSet.next()) {
            invoice.setInvoiceNumber(resultSet.getString("Invoice_Number"));
            invoice.setCurrentDate(resultSet.getString("Date"));
            invoice.setCheckInTime(resultSet.getString("Check_In_Time"));
            invoice.setCheckOutTime(resultSet.getString("Check_Out_Time"));
            invoice.setProductList(resultSet.getString("Products"));
            invoice.setNumberOfUnitsList(resultSet.getString("Units_Per_Product"));
            invoice.setUnitPriceList(resultSet.getString("Unit_Price_Per_Product"));
            invoice.setDiscountPerUnitList(resultSet.getString("Discount_Per_Product"));
            invoice.setSubTotal(Double.parseDouble(resultSet.getString("Sub_Total")));
            invoice.setTotalDiscount(Double.parseDouble(resultSet.getString("Total_Discount")));
            invoice.setTotalPrice(Double.parseDouble(resultSet.getString("Total")));
            invoice.setPaymentMethod(resultSet.getString("Payment_Method"));
            invoice.setCashAmount(Double.parseDouble(resultSet.getString("Cash_Amount")));
            invoice.setBalanceAmount(Double.parseDouble(resultSet.getString("Balance")));
            invoice.setCustomerID(resultSet.getString("Customer_ID"));
            invoice.setCustomerName(resultSet.getString("Customer_Name"));
            invoice.setCustomerContactNumber(resultSet.getString("Contact_Number"));

            invoice.displayAllInvoices();
        }
    }

    public boolean selectInvoice() throws SQLException {
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Invoice_Details WHERE Invoice_ID = '"+ invoice.getInvoiceNumber() +"';";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            invoice.setInvoiceNumber(resultSet.getString("Invoice_Number"));
            invoice.setCurrentDate(resultSet.getString("Date"));
            invoice.setCheckInTime(resultSet.getString("Check_In_Time"));
            invoice.setCheckOutTime(resultSet.getString("Check_Out_Time"));
            invoice.setProductList(resultSet.getString("Products"));
            invoice.setNumberOfUnitsList(resultSet.getString("Units_Per_Product"));
            invoice.setUnitPriceList(resultSet.getString("Unit_Price_Per_Product"));
            invoice.setDiscountPerUnitList(resultSet.getString("Discount_Per_Product"));
            invoice.setSubTotal(Double.parseDouble(resultSet.getString("Sub_Total")));
            invoice.setTotalDiscount(Double.parseDouble(resultSet.getString("Total_Discount")));
            invoice.setTotalPrice(Double.parseDouble(resultSet.getString("Total")));
            invoice.setPaymentMethod(resultSet.getString("Payment_Method"));
            invoice.setCashAmount(Double.parseDouble(resultSet.getString("Cash_Amount")));
            invoice.setBalanceAmount(Double.parseDouble(resultSet.getString("Balance")));
            invoice.setCustomerID(resultSet.getString("Customer_ID"));
            invoice.setCustomerName(resultSet.getString("Customer_Name"));
            invoice.setCustomerContactNumber(resultSet.getString("Contact_Number"));

            invoice.displayAllInvoices();
            return true;
        }

        return false;
    }

    public boolean selectLastInvoice() throws SQLException{

        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Customer_Details";
        ResultSet resultSet = statement.executeQuery(queryString);

        if(resultSet.last()) {
            invoice.setInvoiceNumber(resultSet.getString("Invoice_Number"));
            invoice.setCurrentDate(resultSet.getString("Date"));
            invoice.setCheckInTime(resultSet.getString("Check_In_Time"));
            invoice.setCheckOutTime(resultSet.getString("Check_Out_Time"));
            invoice.setProductList(resultSet.getString("Products"));
            invoice.setNumberOfUnitsList(resultSet.getString("Units_Per_Product"));
            invoice.setUnitPriceList(resultSet.getString("Unit_Price_Per_Product"));
            invoice.setDiscountPerUnitList(resultSet.getString("Discount_Per_Product"));
            invoice.setSubTotal(Double.parseDouble(resultSet.getString("Sub_Total")));
            invoice.setTotalDiscount(Double.parseDouble(resultSet.getString("Total_Discount")));
            invoice.setTotalPrice(Double.parseDouble(resultSet.getString("Total")));
            invoice.setPaymentMethod(resultSet.getString("Payment_Method"));
            invoice.setCashAmount(Double.parseDouble(resultSet.getString("Cash_Amount")));
            invoice.setBalanceAmount(Double.parseDouble(resultSet.getString("Balance")));
            invoice.setCustomerID(resultSet.getString("Customer_ID"));
            invoice.setCustomerName(resultSet.getString("Customer_Name"));
            invoice.setCustomerContactNumber(resultSet.getString("Contact_Number"));
        }else{
            return false;
        }
        return true;
    }

    private String getProductList(){
        String products = "";

        for (int index = 0; index < invoice.getProducts().size(); index++) {
            products += invoice.getProducts().get(index) + "\n";
        }
        return products;
    }

    private String getUnitsPerProductList(){
        String numberOfUnits = "";

        for (int index = 0; index < invoice.getNumberOfUnits().size(); index++) {
            numberOfUnits += invoice.getNumberOfUnits().get(index) + "\n";
        }
        return numberOfUnits;
    }

    private String getPricePerProductList(){
        String unitPrice = "";

        for (int index = 0; index < invoice.getUnitPrice().size(); index++) {
            unitPrice += invoice.getUnitPrice().get(index) + "\n";
        }
        return unitPrice;
    }

    private String getDiscountPerProductList(){
        String discountPerProduct = "";

        for (int index = 0; index < invoice.getDiscountPerUnit().size(); index++) {
            discountPerProduct += invoice.getDiscountPerUnit().get(index) + "\n";
        }
        return discountPerProduct;
    }
}
