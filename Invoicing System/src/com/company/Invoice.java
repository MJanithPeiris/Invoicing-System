package com.company;

import java.util.ArrayList;

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
    private String totalPricePerProductList;
    private String cashierID;

    private ArrayList<String> productIDs;
    private ArrayList<String> products;
    private ArrayList<Integer> numberOfUnits;
    private ArrayList<Double> unitPrice;
    private ArrayList<Double> discountPerUnit;
    private ArrayList<Double> totalPricePerProduct;

    public Invoice(){
        this.cashAmount = 0.0;
        this.balanceAmount = 0.0;
        this.customerID = " ";
        this.customerName = " ";
        this.customerContactNumber = " ";
    }

    public Invoice(String invoiceNumber, String currentDate, String checkInTime, String checkOutTime, double subTotal, double totalDiscount, double totalPrice, double cashAmount, double balanceAmount, String paymentMethod, String customerID, String customerName, String customerContactNumber, String productIDList, String productList, String numberOfUnitsList, String unitPriceList, String discountPerUnitList, String totalPricePerProductList, ArrayList<String> productIDs, ArrayList<String> products, ArrayList<Integer> numberOfUnits, ArrayList<Double> unitPrice, ArrayList<Double> discountPerUnit, ArrayList<Double> totalPricePerProduct) {
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
        this.productIDList = productIDList;
        this.productList = productList;
        this.numberOfUnitsList = numberOfUnitsList;
        this.unitPriceList = unitPriceList;
        this.discountPerUnitList = discountPerUnitList;
        this.totalPricePerProductList = totalPricePerProductList;
        this.productIDs = productIDs;
        this.products = products;
        this.numberOfUnits = numberOfUnits;
        this.unitPrice = unitPrice;
        this.discountPerUnit = discountPerUnit;
        this.totalPricePerProduct = totalPricePerProduct;
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

    public void setTotalPricePerProductList(String totalPricePerProductList) {
        this.totalPricePerProductList = totalPricePerProductList;
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

    public void setTotalPricePerProduct(ArrayList<Double> totalPricePerProduct) {
        this.totalPricePerProduct = totalPricePerProduct;
    }

    public void setCashAmount(double cashAmount) {
        this.cashAmount = cashAmount;
    }

    public void setBalanceAmount(double balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
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

    public String getTotalPricePerProductList() {
        return totalPricePerProductList;
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

    public ArrayList<Double> getTotalPricePerProduct() {
        return totalPricePerProduct;
    }

    public double getCashAmount() {
        return cashAmount;
    }

    public double getBalanceAmount() {
        return balanceAmount;
    }

    public String getCashierID() {
        return cashierID;
    }

    public void displayBill(){

        System.out.print("\n\n Product Name \t\t Quantity \t Unit Price(Rs.) \t Total Price(Rs.)\n");
        for (int index = 0; index < products.size(); index++) {
            System.out.print("\n " + products.get(index));
            System.out.print("\n " + productIDs.get(index) + "\t\t        " + numberOfUnits.get(index) + "\t\t   " + unitPrice.get(index) + "\t\t\t\t " + totalPricePerProduct.get(index));
            System.out.print("\n Discount per product \t\t\t\t\t\t\t\t      -" + discountPerUnit.get(index));
        }
        System.out.print("\n\n Sub Total      : Rs." + String.format("%.2f", subTotal));
        System.out.print("\n Total Discount : Rs." + String.format("%.2f", totalDiscount));
        System.out.println("\n\n Total : Rs." + String.format("%.2f", (totalPrice)));
    }

    public void displayAllInvoices(){

        String[] pID = productIDList.split("\n");
        String[] p = productList.split("\n");
        String[] n = numberOfUnitsList.split("\n");
        String[] u = unitPriceList.split("\n");
        String[] d = discountPerUnitList.split("\n");
        String[] t = totalPricePerProductList.split("\n");

        String pIDs = "";
        for (int i = 0; i < pID.length; i++) {
            pIDs += pID[i];
            if(i < pID.length-1){
                pIDs+= ",";
            }
        }

        String ps = "";
        for (int i = 0; i < p.length; i++) {
            ps += p[i];
            if(i < p.length-1){
                ps+= ",";
            }
        }

        String ns = "";
        for (int i = 0; i < n.length; i++) {
            ns += n[i];
            if(i < n.length-1){
                ns+= ",";
            }
        }

        String us = "";
        for (int i = 0; i < u.length; i++) {
            us += u[i];
            if(i < u.length-1){
                us+= ",";
            }
        }

        String ds = "";
        for (int i = 0; i < d.length; i++) {
            ds += d[i];
            if(i < d.length-1){
                ds+= ",";
            }
        }

        String ts = "";
        for (int i = 0; i < t.length; i++) {
            ts += t[i];
            if(i < t.length-1){
                ts+= ",";
            }
        }

//
//        System.out.print("\n\n Invoice Number : " + invoiceNumber + "\n Date : " + currentDate);
//        System.out.print("\n Check in Time : " + checkInTime + "\n Check out Time : " + checkOutTime);
//        System.out.print("\n Cashier ID : " + cashierID);
//        System.out.print("\n\n Product Name \t\t Quantity \t Unit Price(Rs.)\n");
//        for (int index = 0; index < pID.length; index++) {
//            System.out.print("\n "+ p[index]);
//            System.out.print("\n "+ pID[index] + "\t\t    " + n[index] + "\t\t    " + u[index]);
//            System.out.print("\n Discount per product \t\t\t     -" + d[index]);
//        }
//        System.out.print("\n\n Sub Total      : Rs." + String.format("%.2f", subTotal));
//        System.out.print("\n Total Discount : Rs." + String.format("%.2f", totalDiscount));
//        System.out.print("\n\n Total : Rs." + String.format("%.2f", totalPrice));
//        if(Objects.equals(paymentMethod, "Cash")){
//            System.out.print("\n Cash : Rs." + cashAmount);
//            System.out.print("\n Change : Rs." + String.format("%.2f", balanceAmount));
//        }else{
//            System.out.print("\n Card");
//        }
//        System.out.print("\n\n Customer ID : " + customerID);
//        System.out.print("\n Customer Name : " + customerName);
//        System.out.print("\n Customer Contact Number : " + customerContactNumber);

        TableViewer tableViewer = new TableViewer();
        tableViewer.setShowVerticalLines(true);
        tableViewer.setHeaders("Invoice Number", "Date", "Check in Time", "Check out Time","Product ID", "Product Name","Quantity","Unit Price(Rs.)","Total Price(Rs.)","Discount per product","Sub Total", "Total Discount","Total","Payment Method","Balance", "Customer ID","Customer Name","Customer Contact Number", "Cashier ID");
        tableViewer.addRow(invoiceNumber, currentDate, checkInTime,checkOutTime,pIDs,ps,ns,us,ts,ds,String.format("%.2f", subTotal),String.format("%.2f", totalDiscount),String.format("%.2f", totalPrice),paymentMethod,String.format("%.2f", balanceAmount),customerID,customerName,customerContactNumber,cashierID);
        tableViewer.print();
        System.out.println("\n");

    }
}
