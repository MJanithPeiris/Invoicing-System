package com.company;

public class Cashier {

    private String cashierID;
    private String cashierName;
    private String userName;
    private String password;
    private String address;
    private String contactNumber;
    private String email;

    public Cashier() {
        this.cashierID = "";
        this.cashierName = "";
        this.userName = "";
        this.password = "";
        this.address = "";
        this.contactNumber = "";
        this.email = "";
    }

    public Cashier(String cashierID, String cashierName, String userName, String password, String address, String contactNumber, String email) {
        this.cashierID = cashierID;
        this.cashierName = cashierName;
        this.userName = userName;
        this.password = password;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCashierID() {
        return cashierID;
    }

    public String getCashierName() {
        return cashierName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setHeader(TableViewer tableViewer) {
        tableViewer.setShowVerticalLines(true);
        tableViewer.setHeaders("Cashier ID", "Cashier Name", "User Name", "Address", "Contact Number", "Email");
    }

    public void addRows(TableViewer tableViewer) {
        tableViewer.addRow(cashierID, cashierName, userName, address, contactNumber, email);
    }

    public void displayCashier() {
        TableViewer tableViewer = new TableViewer();
        tableViewer.setShowVerticalLines(true);
        tableViewer.setHeaders("Cashier ID", "Cashier Name", "User Name", "Address", "Contact Number", "Email");
        tableViewer.addRow(cashierID, cashierName, userName,address, contactNumber, email);
        tableViewer.print();
    }
}
