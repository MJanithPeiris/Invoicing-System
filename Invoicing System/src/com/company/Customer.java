package com.company;

public class Customer {
    private String customerID;
    private String customerName;
    private String email;
    private String address;
    private String contactNumber;
    private String dob;
    private String gender;

    public Customer(){

    }

    public Customer(String customerID, String customerName,String email, String address, String contactNumber, String dob, String gender){
        this.customerID = customerID.toUpperCase();
        this.customerName = customerName;
        this.email = email;
        this.address = address;
        this.contactNumber = contactNumber;
        this.dob = dob;
        this.gender = gender;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID.toUpperCase();
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public void displayAllCustomers(){
        System.out.println(" Customer ID : " + customerID + " | Customer Name : " + customerName + " | Email : " + email + " | Address : " + address + " | Contact Number : " + contactNumber + " | Date of Birth : " + dob +" | Gender : " + gender);
    }

    public void displayCustomer(){
        System.out.println("\n Customer ID : " + customerID + "\n Customer Name : " + customerName + "\n Email : " + email + "\n Address : " + address + "\n Contact Number : " + contactNumber + "\n Date of Birth : " + dob +"\n Gender : " + gender);
    }

}
