package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Invoice {

    private String invoiceNumber;
    private String currentDate;
    private String checkInTime;
    private String checkOutTime;
    private double subTotal;
    private double totalDiscount;
    private double totalPrice;
    private double cashAmount;
    private double balanceAmount;
    private String paymentMethod;
    private String customerID;
    private String customerName;
    private String customerContactNumber;
    private String productIDList;
    private String productList;
    private String numberOfUnitsList;
    private String unitPriceList;
    private String discountPerUnitList;

    private ArrayList<String> productIDs;
    private ArrayList<String> products;
    private ArrayList<Integer> numberOfUnits;
    private ArrayList<Double> unitPrice;
    private ArrayList<Double> discountPerUnit;

    public Invoice(){

    }

    public Invoice(String invoiceNumber, String currentDate, String checkInTime, String checkOutTime, double subTotal, double totalDiscount, double totalPrice, double cashAmount, double balanceAmount, String paymentMethod, String customerID, String customerName, String customerContactNumber, String productList, String numberOfUnitsList, String unitPriceList, String discountPerUnitList, ArrayList<String> productIDs, ArrayList<String> products, ArrayList<Integer> numberOfUnits, ArrayList<Double> unitPrice, ArrayList<Double> discountPerUnit) {
        this.invoiceNumber = invoiceNumber;
        this.currentDate = currentDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.subTotal = subTotal;
        this.totalDiscount = totalDiscount;
        this.totalPrice = totalPrice;
        this.cashAmount = cashAmount;
        this.balanceAmount = balanceAmount;
        this.paymentMethod = paymentMethod;
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerContactNumber = customerContactNumber;
        this.productList = productList;
        this.numberOfUnitsList = numberOfUnitsList;
        this.unitPriceList = unitPriceList;
        this.discountPerUnitList = discountPerUnitList;
        this.productIDs = productIDs;
        this.products = products;
        this.numberOfUnits = numberOfUnits;
        this.unitPrice = unitPrice;
        this.discountPerUnit = discountPerUnit;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public void setTotalDiscount(double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerContactNumber(String customerContactNumber) {
        this.customerContactNumber = customerContactNumber;
    }

    public void setProductIDList(String productIDList) {
        this.productIDList = productIDList;
    }

    public void setProductList(String productList) {
        this.productList = productList;
    }

    public void setNumberOfUnitsList(String numberOfUnitsList) {
        this.numberOfUnitsList = numberOfUnitsList;
    }

    public void setUnitPriceList(String unitPriceList) {
        this.unitPriceList = unitPriceList;
    }

    public void setDiscountPerUnitList(String discountPerUnitList) {
        this.discountPerUnitList = discountPerUnitList;
    }

    public void setProductIDs(ArrayList<String> productIDs) {
        this.productIDs = productIDs;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public void setNumberOfUnits(ArrayList<Integer> numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public void setUnitPrice(ArrayList<Double> unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setDiscountPerUnit(ArrayList<Double> discountPerUnit) {
        this.discountPerUnit = discountPerUnit;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public double getTotalDiscount() {
        return totalDiscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerContactNumber() {
        return customerContactNumber;
    }

    public String getProductIDList() {
        return productIDList;
    }

    public String getProductList() {
        return productList;
    }

    public String getNumberOfUnitsList() {
        return numberOfUnitsList;
    }

    public String getUnitPriceList() {
        return unitPriceList;
    }

    public String getDiscountPerUnitList() {
        return discountPerUnitList;
    }

    public ArrayList<String> getProductIDs() {
        return productIDs;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public ArrayList<Integer> getNumberOfUnits() {
        return numberOfUnits;
    }

    public ArrayList<Double> getUnitPrice() {
        return unitPrice;
    }

    public ArrayList<Double> getDiscountPerUnit() {
        return discountPerUnit;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public void displayBill(){

        System.out.print("\n\n Product Name \t\t Quantity \t Unit Price(Rs.)\n");
        for (int index = 0; index < products.size(); index++) {
            System.out.print("\n " + products.get(index));
            System.out.print("\n " + productIDs.get(index) + "\t\t    " + numberOfUnits.get(index) + "\t\t    " + unitPrice.get(index));
            System.out.print("\n Discount for product \t\t\t     -" + discountPerUnit.get(index));
        }
        System.out.print("\n\n Sub Total      : Rs." + String.format("%.2f", subTotal));
        System.out.print("\n Total Discount : Rs." + String.format("%.2f", totalDiscount));
        System.out.print("\n\n Total : Rs." + String.format("%.2f", (totalPrice)));
    }

    public void displayAllInvoices(){

        String[] pID = productIDList.split("\n");
        String[] p = productList.split("\n");
        String[] n = numberOfUnitsList.split("\n");
        String[] u = unitPriceList.split("\n");
        String[] d = discountPerUnitList.split("\n");

        System.out.print("\n\n Invoice Number : " + invoiceNumber + "\n Date : " + currentDate);
        System.out.print("\n Check in Time : " + checkInTime + "\n Check out Time : " + checkOutTime);
        System.out.print("\n\n Product Name \t\t Quantity \t Unit Price(Rs.)\n");
        for (int index = 0; index < pID.length; index++) {
            System.out.print("\n "+ p[index]);
            System.out.print("\n "+ pID[index] + "\t\t    " + n[index] + "\t\t    " + u[index]);
            System.out.print("\n Discount for product \t\t\t     -" + d[index]);
        }
        System.out.print("\n\n Sub Total      : Rs." + String.format("%.2f", subTotal));
        System.out.print("\n Total Discount : Rs." + String.format("%.2f", totalDiscount));
        System.out.print("\n\n Total : Rs." + String.format("%.2f", (totalPrice)));
        if(Objects.equals(paymentMethod, "Cash")){
            System.out.print("\n Cash : Rs." + cashAmount);
            System.out.print("\n Change : Rs." + String.format("%.2f", (balanceAmount)));
        }else{
            System.out.print("\n Card");
        }
        System.out.print("\n\n Customer ID : " + customerID);
        System.out.print("\n Customer Name : " + customerName);
        System.out.print("\n Customer Contact Number : " + customerContactNumber);
    }
}
