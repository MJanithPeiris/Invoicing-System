package com.company;

public class Product {
    private String productID;
    private String productName;
    private String description;
    private double purchasePrice;
    private double sellingPrice;
    private int qty;

    public Product() {

    }

    public Product(String productID, String productName, String description, double purchasePrice, double sellingPrice, int qty) {
        this.productID = productID.toUpperCase();
        this.productName = productName;
        this.description = description;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.qty = qty;
    }

    public void setProductID(String productID) {
        this.productID = productID.toUpperCase();
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public int getQty() {
        return qty;
    }

    public void displayAllProducts() {
        System.out.println(" Product ID : " + productID + " | Product Name : " + productName + " | Description : " + description + " | Purchase Price : " + purchasePrice + " | Selling Price : " + sellingPrice + " | Quantity : " + qty);
    }

    public void displayProduct() {
        System.out.println(" Product ID : " + productID + " \n Product Name : " + productName + " \n Description : " + description + " \n Purchase Price : " + purchasePrice + " \n Selling Price : " + sellingPrice + " \n Quantity : " + qty);
    }
}
