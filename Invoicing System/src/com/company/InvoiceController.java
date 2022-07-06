package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InvoiceController {

    public static String addInvoice(Invoice invoice) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        // insert query for database
        String queryString = "INSERT INTO Invoice_Details (Invoice_Number, Date, Check_In_Time, Check_Out_Time, ProductIDs, Products, Units_Per_Product, Unit_Price_Per_Product, Total_Price, Discount_Per_Product, Sub_Total, Total_Discount, Total, Payment_Method, Cash_Amount, Balance, Customer_ID, Customer_Name, Contact_Number, Cashier_ID) " +
                "VALUES ('"+ invoice.getInvoiceNumber() +"','"+ invoice.getCurrentDate() +"','"+ invoice.getCheckInTime() +"','"+ invoice.getCheckOutTime() +"','"+ getProductIDList(invoice) +"','"+ getProductList(invoice) +"','"+ getUnitsPerProductList(invoice) +"','"+ getPricePerProductList(invoice) +"','"+ getTotalPricePerProductList(invoice) +"','"+ getDiscountPerProductList(invoice) +"','"+ invoice.getSubTotal() +"','"+ invoice.getTotalDiscount() +"','"+ invoice.getTotalPrice() +"','"+ invoice.getPaymentMethod() +"','"+ invoice.getCashAmount() +"','"+ invoice.getBalanceAmount() +"','"+ invoice.getCustomerID() +"','"+ invoice.getCustomerName() +"','"+ invoice.getCustomerContactNumber() +"','"+ invoice.getCashierID() +"');";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return ("Invoice is added successfully");
        } else {
            return ("Invoice is added unsuccessfully");
        }
    }

    public static void selectAllInvoices(Invoice invoice, int selectOption, String startDate, String endDate) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString;
        if (selectOption == 1)
            queryString = "SELECT * FROM Invoice_Details WHERE DATE BETWEEN '" + startDate + "' AND '" + endDate + "';";
        else if (selectOption == 2)
            queryString = "SELECT * FROM Invoice_Details WHERE Customer_ID = '" + invoice.getCustomerID() + "' OR Contact_Number = '" + invoice.getCustomerContactNumber() + "';";

        else
            queryString = "SELECT * FROM Invoice_Details;";

        ResultSet resultSet = statement.executeQuery(queryString);

        TableViewer tableViewer = new TableViewer();
        invoice.setHeader(tableViewer);

        while (resultSet.next()) {
            setData(invoice, resultSet);
            invoice.addRows(tableViewer);
        }
        tableViewer.print();
    }

    public static boolean selectInvoice(Invoice invoice) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Invoice_Details WHERE Invoice_Number = '"+ invoice.getInvoiceNumber() +"';";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.next()) {
            setData(invoice,resultSet);
            return true;
        }
        return false;
    }

    public static boolean selectLastInvoice(Invoice invoice) throws SQLException, ClassNotFoundException {

        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "SELECT * FROM Invoice_Details;";
        ResultSet resultSet = statement.executeQuery(queryString);

        if (resultSet.last()) {
            setData(invoice, resultSet);
            return true;
        }
        return false;
    }

    public static String deleteInvoice(Invoice invoice) throws SQLException, ClassNotFoundException {
        DBConnector connector = new DBConnector();
        connector.setDBConnection();
        Connection connection;
        connection = connector.getDBConnection();
        Statement statement = connection.createStatement();
        String queryString = "DELETE FROM Invoice_Details WHERE Invoice_Number = '" + invoice.getInvoiceNumber() + "';";
        int i = statement.executeUpdate(queryString);

        if (i != 0) {
            return (" Invoice is deleted successfully");
        } else {
            return (" Invoice is deleted unsuccessfully");
        }
    }

    private static void setData(Invoice invoice, ResultSet resultSet) throws SQLException {
        invoice.setInvoiceNumber(resultSet.getString("Invoice_Number"));
        invoice.setCurrentDate(resultSet.getString("Date"));
        invoice.setCheckInTime(resultSet.getString("Check_In_Time"));
        invoice.setCheckOutTime(resultSet.getString("Check_Out_Time"));
        invoice.setProductIDList(resultSet.getString("ProductIDs"));
        invoice.setProductList(resultSet.getString("Products"));
        invoice.setNumberOfUnitsList(resultSet.getString("Units_Per_Product"));
        invoice.setUnitPriceList(resultSet.getString("Unit_Price_Per_Product"));
        invoice.setTotalPricePerProductList(resultSet.getString("Total_Price"));
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
        invoice.setCashierID(resultSet.getString("Cashier_ID"));
    }


    private static String getProductIDList(Invoice invoice){
        String productIDs = "";

        for (int index = 0; index < invoice.getProductIDs().size(); index++) {
            productIDs += invoice.getProductIDs().get(index);
            if(index < invoice.getProductIDs().size()-1){
                productIDs += "\n";
            }
        }
        return productIDs;
    }

    private static String getProductList(Invoice invoice){
        String products = "";

        for (int index = 0; index < invoice.getProducts().size(); index++) {
            products += invoice.getProducts().get(index);
            if(index < invoice.getProducts().size()-1){
                products += "\n";
            }
        }
        return products;
    }

    private static String getUnitsPerProductList(Invoice invoice){
        String numberOfUnits = "";

        for (int index = 0; index < invoice.getNumberOfUnits().size(); index++) {
            numberOfUnits += invoice.getNumberOfUnits().get(index) ;
            if(index < invoice.getNumberOfUnits().size()-1){
                numberOfUnits += "\n";
            }
        }
        return numberOfUnits;
    }

    private static String getPricePerProductList(Invoice invoice){
        String unitPrice = "";

        for (int index = 0; index < invoice.getUnitPrice().size(); index++) {
            unitPrice += invoice.getUnitPrice().get(index);
            if(index < invoice.getUnitPrice().size()-1){
                unitPrice += "\n";
            }
        }
        return unitPrice;
    }

    private static String getTotalPricePerProductList(Invoice invoice){
        String totalPrice = "";

        for (int index = 0; index < invoice.getTotalPricePerProduct().size(); index++) {
            totalPrice += invoice.getTotalPricePerProduct().get(index);
            if(index < invoice.getTotalPricePerProduct().size()-1){
                totalPrice += "\n";
            }
        }
        return totalPrice;
    }

    private static String getDiscountPerProductList(Invoice invoice){
        String discountPerProduct = "";

        for (int index = 0; index < invoice.getDiscountPerUnit().size(); index++) {
            discountPerProduct += invoice.getDiscountPerUnit().get(index);
            if(index < invoice.getDiscountPerUnit().size()-1){
                discountPerProduct += "\n";
            }
        }
        return discountPerProduct;
    }

}
