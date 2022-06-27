package com.company;

public class User {

    private String userID;
    private String name;
    private String userName;
    private String password;
    private String contactNumber;
    private String email;

    public User(){

    }

    public User(String user_ID, String name, String user_Name, String password, String tel_No, String email) {
        userID = user_ID;
        this.name = name;
        userName = user_Name;
        this.password = password;
        contactNumber = tel_No;
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
}
