package com.company;

public class User {

    private String userID;
    private String name;
    private String userName;
    private String password;
    private String contactNumber;
    private String email;

    public User() {
        this.userID = "";
        this.name = "";
        this.userName = "";
        this.password = "";
        this.contactNumber = "";
        this.email = "";
    }

    public User(String userID, String name, String userName, String password, String contactNumber, String email) {
        this.userID = userID;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setHeader(TableViewer tableViewer) {
        tableViewer.setShowVerticalLines(true);
        tableViewer.setHeaders("User ID", "Name", "User Name", "Contact Number", "Email");
    }

    public void addRows(TableViewer tableViewer) {
        tableViewer.addRow(userID, name, userName, contactNumber, email);
    }

    public void displayUser() {
        TableViewer tableViewer = new TableViewer();
        tableViewer.setShowVerticalLines(true);
        tableViewer.setHeaders("User ID", "Name", "User Name", "Contact Number", "Email");
        tableViewer.addRow(userID, name, userName, contactNumber, email);
        tableViewer.print();
    }
}
