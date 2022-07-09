package com.company;

import java.io.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner userOption = new Scanner(System.in); // to get user input
        int userInput;
        String userName;
        String password;
        boolean isRunning = true;

        System.out.println(" \n\t\t ---ABC Company--- ");
        System.out.println(" ---Welcome to Invoice System---");
        System.out.println("\n Sign in to the system");
        while (isRunning) {
            try {
                Cashier cashier = new Cashier();
                // get user credentials to start the program
                while (true) {
                    System.out.print(" Enter User Name : ");
                    userName = userOption.nextLine();
                    cashier.setUserName(userName);
                    if (CashierController.selectCashier(cashier)) {
                        System.out.print(" Enter Password : ");
                        password = userOption.nextLine();
                        if (Objects.equals(cashier.getPassword(), password))
                            break;
                        else
                            System.out.println(" Wrong password entered!! Try again...");
                    } else
                        System.out.println(" Wrong user name entered!! Try again...");
                    cashier.setCashierID("");
                    cashier.setUserName("");
                    cashier.setContactNumber("");
                }
                System.out.print("\n Logging.");
                for (int i = 0; i < 10; i++) {
                    System.out.print(".");
                    Thread.sleep(250);
                }

                System.out.print(" \n\n\n\t\t ---ABC Company--- ");

                // main program
                while (isRunning) {
                    System.out.print("\n ---Welcome to Invoice System---");
                    System.out.println("\n\n 1. Manage Products\n 2. Manage Customers\n 3. Manage Invoices\n 4. Admin Tasks\n 5. Manage Cashiers\n 0. Exit");
                    System.out.print(" Your Option : ");
                    userInput = Integer.parseInt(userOption.nextLine());

                    switch (userInput) {
                        case 1:
                            System.out.println("\n\n ---Manage Products---");
                            manageProducts(userOption);
                            break;
                        case 2:
                            System.out.println("\n ---Manage Customers---");
                            manageCustomers(userOption);
                            break;
                        case 3:
                            System.out.println("\n ---Manage Invoices---");
                            manageInvoices(userOption, cashier.getCashierID());
                            break;
                        case 4:
                            System.out.println("\n ---Admin Tasks---");
                            // allow only admins to check admin tasks
                            if(Objects.equals(cashier.getUserName(), "Admin"))
                                adminTask(userOption);
                            else
                                System.out.println(" You are not an authorised person!!");
                            break;
                        case 5:
                            System.out.println("\n ---Manage Cashiers---");
                            manageCashiers(userOption);
                            break;
                        case 0:

                            if (getConfirmation(userOption, "exit")) {
                                isRunning = false;
                            }
                            break;
                        default:
                            System.out.println(" Invalid option entered! Try again \n");
                    }

                }

                System.out.print("\n Exiting the system.");
                for (int i = 0; i < 10; i++) {
                    System.out.print(".");
                    Thread.sleep(250);
                }
                System.out.println("\n Have a nice Day !!");

            } catch (SQLException | ClassNotFoundException exception) {
                System.out.println(" Sever not found!!");
            } catch (IOException exception) {
                System.out.println(" An error occurred.");
            } catch (InterruptedException exception) {
                System.out.println(" Error in starting the program!!");
            }
        }
    }

    private static void manageProducts(Scanner userOption) throws SQLException, ClassNotFoundException, IOException {

        String productDetails;
        String productID;
        String productName;
        String description;
        double purchasePrice;
        double sellingPrice;
        int qty;
        Product product;

        System.out.println(" 1. Add a Product \n 2. Update Product Details \n 3. Remove a Product \n 4. Search a Product \n 5. Display all Products");
        System.out.print(" Your Option : ");

        switch (Integer.parseInt(userOption.nextLine())) {
            case 1:
                product = new Product();

                System.out.println(" ---Add a Product---");
                productID = getNextID('P', ProductController.selectLastProduct(product), product.getProductID());
                System.out.println(" Enter Product ID : " + productID);
                product.setProductID(productID);
                System.out.print(" Enter Product Name : ");
                product.setProductName(userOption.nextLine());
                System.out.print(" Enter Product Description : ");
                product.setDescription(userOption.nextLine());
                System.out.print(" Enter Product Purchase Price : ");
                product.setPurchasePrice(Double.parseDouble(userOption.nextLine()));
                System.out.print(" Enter Product Selling Price : ");
                product.setSellingPrice(Double.parseDouble(userOption.nextLine()));
                System.out.print(" Enter Product Quantity : ");
                product.setQty(Integer.parseInt(userOption.nextLine()));


                if (getConfirmation(userOption, "add")) {
                    System.out.println(ProductController.addProduct(product));
                    // update the report
                    FileOperator.writeFile("Product Report", product.getProductID(), "Insert", getDate(), getTime(), FileOperator.readFile("Product Report"));

                }
                break;
            case 2:
                System.out.println(" ---Update Product Details---");
                product = new Product();

                while (true) {
                    System.out.print(" Enter Product ID or Name : ");
                    productDetails = userOption.nextLine();
                    product.setProductID(productDetails);
                    product.setProductName(productDetails);
                    if (ProductController.selectProduct(product))
                        break;
                    else
                        System.out.println(" Wrong Product ID or Name entered!! Try again...");
                }
                product.displayProduct();

                // if user don't want to change the field can press enter to ignore
                System.out.print("\n Enter updated Product Name : ");
                productName = userOption.nextLine();
                if (!Objects.equals(productName, ""))
                    product.setProductName(productName);

                System.out.print(" Enter updated Product description : ");
                description = userOption.nextLine();
                if (!Objects.equals(description, ""))
                    product.setDescription(description);

                System.out.print(" Enter updated Product purchase price : ");
                purchasePrice = userOption.nextDouble();
                if (purchasePrice != 0)
                    product.setPurchasePrice(purchasePrice);

                System.out.print(" Enter updated Product selling price : ");
                sellingPrice = userOption.nextDouble();
                if (sellingPrice != 0)
                    product.setSellingPrice(sellingPrice);

                System.out.print(" Enter updated Product quantity : ");
                qty = userOption.nextInt();
                if (qty != 0)
                    product.setQty(product.getQty() + qty);
                userOption.nextLine();

                if (getConfirmation(userOption, "update")) {
                    System.out.println(ProductController.updateProduct(product));
                    // update the report
                    FileOperator.writeFile("Product Report", product.getProductID(), "Update", getDate(), getTime(), FileOperator.readFile("Product Report"));
                }
                break;
            case 3:
                System.out.println(" ---Remove a Product---");
                product = new Product();

                while (true) {
                    System.out.print(" Enter Product ID or Name : ");
                    productDetails = userOption.nextLine();
                    product.setProductID(productDetails);
                    product.setProductName(productDetails);
                    if (ProductController.selectProduct(product))
                        break;
                    else
                        System.out.println(" Wrong Product ID or Name entered!! Try again...");
                }
                product.displayProduct();


                if (getConfirmation(userOption, "delete")) {
                    System.out.println(ProductController.deleteProduct(product));
                    // update the report
                    FileOperator.writeFile("Product Report", product.getProductID(), "Delete", getDate(), getTime(), FileOperator.readFile("Product Report"));
                }
                break;
            case 4:
                System.out.println(" ---Search a Product---");
                product = new Product();

                while (true) {
                    System.out.print(" Enter Product ID or Name : ");
                    productDetails = userOption.nextLine();
                    product.setProductID(productDetails);
                    product.setProductName(productDetails);
                    if (ProductController.selectProduct(product))
                        break;
                    else
                        System.out.println(" Wrong Product ID or Name entered!! Try again...");
                }
                product.displayProduct();
                break;
            case 5:
                System.out.println(" ---Display all Products---");
                product = new Product();
                ProductController.selectAllProducts(product);
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }
    }


    private static void manageCustomers(Scanner userOption) throws SQLException, ClassNotFoundException, IOException {

        String customerDetails;
        String customerID;
        String customerName;
        String email;
        String address;
        String contactNumber;
        String dob;
        String gender;
        Customer customer;

        System.out.println(" 1. Add a Customer \n 2. Update Customer Details \n 3. Remove a Customer \n 4. Search a Customer \n 5. Display all Customers");
        System.out.print(" Your Option : ");

        switch (Integer.parseInt(userOption.nextLine())) {
            case 1:
                customer = new Customer();

                System.out.println(" ---Add a Customer---");
                customerID = getNextID('C', CustomerController.selectLastCustomer(customer), customer.getCustomerID());
                System.out.println(" Enter Customers' ID : " + customerID);
                customer.setCustomerID(customerID);
                System.out.print(" Enter Customers' Name : ");
                customer.setCustomerName(userOption.nextLine());
                System.out.print(" Enter Customers' Email : ");
                customer.setEmail(userOption.nextLine());
                System.out.print(" Enter Customers' Address : ");
                customer.setAddress(userOption.nextLine());
                System.out.print(" Enter Customers' Contact Number : ");
                customer.setContactNumber(userOption.nextLine());
                System.out.print(" Enter Customers' Date of Birth (YYYY-MM-DD) : ");
                customer.setDob(userOption.nextLine());
                System.out.print(" Enter Customers' Gender : ");
                customer.setGender(userOption.nextLine());


                if (getConfirmation(userOption,"add")) {
                    System.out.println(CustomerController.addCustomer(customer));
                    // update the report
                    FileOperator.writeFile("Customer Report", customer.getCustomerID(), "Insert", getDate(), getTime(), FileOperator.readFile("Customer Report"));
                }
                break;
            case 2:
                System.out.println(" ---Update Customer Details---");
                customer = new Customer();

                while (true) {
                    System.out.print(" Enter Customer ID or Contact Number : ");
                    customerDetails = userOption.nextLine();
                    customer.setCustomerID(customerDetails);
                    customer.setContactNumber(customerDetails);
                    if (CustomerController.selectCustomer(customer))
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                customer.displayCustomer();

                // if user don't want to change the field can press enter to ignore
                System.out.print(" Enter updated Customers' Name : ");
                customerName = userOption.nextLine();
                if (!Objects.equals(customerName, ""))
                    customer.setCustomerName(customerName);

                System.out.print(" Enter Customers' Email : ");
                email = userOption.nextLine();
                if (!Objects.equals(email, ""))
                    customer.setEmail(email);

                System.out.print(" Enter Customers' Address : ");
                address = userOption.nextLine();
                if (!Objects.equals(address, ""))
                    customer.setAddress(address);

                System.out.print(" Enter Customers' Contact Number : ");
                contactNumber = userOption.nextLine();
                if (!Objects.equals(contactNumber, ""))
                    customer.setContactNumber(contactNumber);

                System.out.print(" Enter Customers' Date of Birth (YYYY-MM-DD) : ");
                dob = userOption.nextLine();
                if (!Objects.equals(dob, ""))
                    customer.setDob(dob);

                System.out.print(" Enter Customers' Gender : ");
                gender = userOption.nextLine();
                if (!Objects.equals(gender, ""))
                    customer.setGender(gender);


                if (getConfirmation(userOption,"update")) {
                    System.out.println(CustomerController.updateCustomer(customer));
                    // update the report
                    FileOperator.writeFile("Customer Report", customer.getCustomerID(), "Update", getDate(), getTime(), FileOperator.readFile("Customer Report"));
                }
                break;
            case 3:
                System.out.println(" ---Remove a Customer---");
                customer = new Customer();

                while (true) {
                    System.out.print(" Enter Customer ID or Contact Number : ");
                    customerDetails = userOption.nextLine();
                    customer.setCustomerID(customerDetails);
                    customer.setContactNumber(customerDetails);
                    if (CustomerController.selectCustomer(customer))
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                customer.displayCustomer();

                if (getConfirmation(userOption,"delete")) {
                    System.out.println(CustomerController.deleteCustomer(customer));
                    // update the report
                    FileOperator.writeFile("Customer Report", customer.getCustomerID(), "Delete", getDate(), getTime(), FileOperator.readFile("Customer Report"));
                }
                break;
            case 4:
                System.out.println(" ---Search a Customer---");
                customer = new Customer();

                while (true) {
                    System.out.print(" Enter Customer ID or Contact Number : ");
                    customerDetails = userOption.nextLine();
                    customer.setCustomerID(customerDetails);
                    customer.setContactNumber(customerDetails);
                    if (CustomerController.selectCustomer(customer))
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                customer.displayCustomer();

                break;
            case 5:
                System.out.println(" ---Display all Customers---");
                customer = new Customer();
                CustomerController.selectAllCustomers(customer);
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }
    }


    private static void manageInvoices(Scanner userOption, String userID) throws SQLException, ClassNotFoundException, IOException {

        Invoice invoice = new Invoice();

        System.out.println(" 1. Generate an Invoice \n 2. Search an Invoice \n 3. View all Invoices");
        System.out.print(" Your Option : ");

        switch (Integer.parseInt(userOption.nextLine())){
            case 1:
                String invoiceNumber;
                String customerDetails;
                String productDetails;
                double discountPercentage;
                double subTotal = 0;
                double totalDiscount = 0;
                double cashAmount;
                int buyingQty;
                int userInput;

                ArrayList<String> productIDs = new ArrayList<>();
                ArrayList<String> products = new ArrayList<>();
                ArrayList<Integer> numberOfUnits = new ArrayList<>();
                ArrayList<Double> unitPrice = new ArrayList<>();
                ArrayList<Double> discountPerUnit = new ArrayList<>();
                ArrayList<Double> totalPricePerProduct = new ArrayList<>();

                Customer customer = new Customer();
                Product product = new Product();

                invoiceNumber = getNextID('I', InvoiceController.selectLastInvoice(invoice), invoice.getInvoiceNumber());
                invoice.setInvoiceNumber(invoiceNumber);

                invoice.setCurrentDate(getDate());
                invoice.setCheckInTime(getTime());

                while (true) {
                    System.out.print(" Enter customer ID or contact number : ");
                    customerDetails = userOption.nextLine();
                    customer.setCustomerID(customerDetails);
                    customer.setContactNumber(customerDetails);
                    if (CustomerController.selectCustomer(customer)) {
                        customer.displayCustomer();
                        invoice.setCustomerID(customer.getCustomerID());
                        invoice.setCustomerName(customer.getCustomerName());
                        invoice.setCustomerContactNumber(customer.getContactNumber());
                        break;
                    } else
                        System.out.println(" Wrong Customer ID or Contact Number!! Try again...");
                }

                while (true) {
                    System.out.print("\n Enter the Product ID or Name : ");
                    productDetails = userOption.nextLine();
                    product.setProductID(productDetails);
                    product.setProductName(productDetails);
                    if (ProductController.selectProduct(product)) {
                        System.out.print(" Number of Units : ");
                        buyingQty = Integer.parseInt(userOption.nextLine());

                        if (product.getQty() - buyingQty > 0) { // check sufficient stock is available to buy
                            System.out.print(" Discount Percentage : ");
                            discountPercentage = Double.parseDouble(userOption.nextLine());

                            productIDs.add(product.getProductID());
                            products.add(product.getProductName());
                            unitPrice.add(product.getSellingPrice());
                            discountPerUnit.add((product.getSellingPrice() * discountPercentage) / 100);
                            numberOfUnits.add(buyingQty);
                            totalPricePerProduct.add(product.getSellingPrice() * buyingQty);

                            product.setQty(product.getQty() - buyingQty);
                            ProductController.updateProduct(product);

                            totalDiscount += (product.getSellingPrice() * discountPercentage) * buyingQty / 100;
                            subTotal += product.getSellingPrice() * buyingQty;
                        } else {
                            System.out.println(product.getQty() + " only left in the stock!!");
                        }

                    } else {
                        System.out.println(" Wrong product ID or Name!! Try again...");
                        continue;
                    }

                    System.out.print(" Do you want to add more items?\n 1. Yes\n 2. No\n Your Option : ");
                    userInput = Integer.parseInt(userOption.nextLine());
                    if (userInput != 1)
                        break;
                }

                invoice.setProductIDs(productIDs);
                invoice.setProducts(products);
                invoice.setNumberOfUnits(numberOfUnits);
                invoice.setUnitPrice(unitPrice);
                invoice.setDiscountPerUnit(discountPerUnit);
                invoice.setTotalPricePerProduct(totalPricePerProduct);
                invoice.setSubTotal(subTotal);
                invoice.setTotalDiscount(totalDiscount);
                invoice.setTotalPrice(subTotal - totalDiscount);
                invoice.setCashierID(userID);

                invoice.displayBill();

                System.out.print("\n\n Enter your Payment Method\n 1. Cash \n 2. Card \n Your Option : ");
                userInput = Integer.parseInt(userOption.nextLine());

                if (userInput == 1) {
                    invoice.setPaymentMethod("Cash");
                    System.out.print(" Enter the cash amount :");
                    cashAmount = Double.parseDouble(userOption.nextLine());
                    invoice.setCashAmount(cashAmount);
                    invoice.setBalanceAmount(cashAmount - (subTotal - totalDiscount));
                    System.out.println(" Balance : " + String.format("%.2f", invoice.getBalanceAmount()));
                } else {
                    invoice.setPaymentMethod("Card");
                    invoice.setCashAmount(0.0);
                    invoice.setBalanceAmount(0.0);
                }

                invoice.setCheckOutTime(getTime());

                System.out.println(InvoiceController.addInvoice(invoice));
                // update the report
                FileOperator.writeFile("Invoice Report", invoice.getInvoiceNumber(), "Insert", invoice.getCurrentDate(), invoice.getCheckInTime(), FileOperator.readFile("Invoice Report"));

                FileOperator.printBill(invoice);
                break;
            case 2:
                System.out.println(" ---Search for an Invoices---");
                while (true) {
                    System.out.print(" Enter Invoice Number : ");
                    invoice.setInvoiceNumber(userOption.nextLine());
                    if (InvoiceController.selectInvoice(invoice))
                        break;
                    else
                        System.out.println(" Wrong Invoice id has been entered!! Try again...");
                }
                invoice.displayInvoice();
                break;
            case 3:
                System.out.println(" ---View all Invoices---");
                invoice = new Invoice();
                InvoiceController.selectAllInvoices(invoice, 0, "None", "None");
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }
    }


    private static void manageCashiers(Scanner userOption) throws SQLException, ClassNotFoundException, IOException {

        String userDetails;
        String userID;
        String name;
        String userName;
        String password;
        String confirmPassword;
        String address;
        String contactNumber;
        String email;

        Cashier cashier;

        System.out.println(" 1. Add a Cashier\n 2. Reset Cashier Password\n 3. Update Cashier Details\n 4. Remove a Cashier\n 5. Search a Cashier\n 6. Display all Cashiers");
        System.out.print(" Your Option : ");

        switch (Integer.parseInt(userOption.nextLine())) {
            case 1:
                cashier = new Cashier();

                System.out.println(" ---Add a Cashier---");
                userID = getNextID('U', CashierController.selectLastCashier(cashier), cashier.getCashierID());
                System.out.println(" Enter Cashier ID : " + userID);
                cashier.setCashierID(userID);
                System.out.print(" Enter Cashier Name : ");
                cashier.setCashierName(userOption.nextLine());
                System.out.print(" Enter User Name : ");
                cashier.setUserName(userOption.nextLine());
                do {
                    System.out.print(" Enter Password : ");
                    password = userOption.nextLine();
                    System.out.print(" Enter Confirm Password : ");
                    confirmPassword = userOption.nextLine();
                } while (!Objects.equals(password, confirmPassword));
                cashier.setPassword(password);
                System.out.print(" Enter Address : ");
                cashier.setAddress(userOption.nextLine());
                System.out.print(" Enter Contact Number : ");
                cashier.setContactNumber(userOption.nextLine());
                System.out.print(" Enter Email : ");
                cashier.setEmail(userOption.nextLine());


                if (getConfirmation(userOption, "add")) {
                    System.out.println(CashierController.addCashier(cashier));
                    // update the report
                    FileOperator.writeFile("Cashier Report", cashier.getCashierID(), "Insert", getDate(), getTime(), FileOperator.readFile("Cashier Report"));
                }
                break;
            case 2:
                System.out.println(" ---Reset Cashier Password---");
                cashier = new Cashier();
                while (true) {
                    System.out.print(" Enter Cashier ID or Contact Number : ");
                    userDetails = userOption.nextLine();
                    cashier.setCashierID(userDetails);
                    cashier.setContactNumber(userDetails);
                    if (CashierController.selectCashier(cashier))
                        break;
                    else
                        System.out.println(" Wrong Cashier ID or Contact Number entered!! Try again...");
                }
                cashier.displayCashier();

                while (true) {
                    System.out.print(" Enter New Password : ");
                    password = userOption.nextLine();
                    System.out.print(" Enter Confirm Password : ");
                    confirmPassword = userOption.nextLine();
                    if (Objects.equals(password, confirmPassword))
                        break;
                    else
                        System.out.println(" Password and Confirm password didn't match!! Try again..,");
                }
                cashier.setPassword(password);

                if (getConfirmation(userOption, "reset the password")) {
                    System.out.println(CashierController.updateCashier(cashier));
                    // update the report
                    FileOperator.writeFile("Cashier Report", cashier.getCashierID(), "Reset", getDate(), getTime(), FileOperator.readFile("Cashier Report"));
                }
                break;
            case 3:
                System.out.println(" ---Update Cashier Details---");

                cashier = new Cashier();
                while (true) {
                    System.out.print(" Enter Cashier ID or Contact Number : ");
                    userDetails = userOption.nextLine();
                    cashier.setCashierID(userDetails);
                    cashier.setContactNumber(userDetails);
                    if (CashierController.selectCashier(cashier))
                        break;
                    else
                        System.out.println(" Wrong Cashier ID or Contact Number entered!! Try again...");
                }
                cashier.displayCashier();

                // if user don't want to change the field can press enter to ignore
                System.out.print("\n Enter updated Name : ");
                name = userOption.nextLine();
                if (!Objects.equals(name, ""))
                    cashier.setCashierName(name);

                System.out.print(" Enter updated User Name : ");
                userName = userOption.nextLine();
                if (!Objects.equals(userName, ""))
                    cashier.setUserName(userName);

                System.out.print(" Enter updated Address : ");
                address = userOption.nextLine();
                if (!Objects.equals(address, ""))
                    cashier.setAddress(address);

                System.out.print(" Enter updated Contact Number : ");
                contactNumber = userOption.nextLine();
                if (!Objects.equals(contactNumber, ""))
                    cashier.setContactNumber(contactNumber);

                System.out.print(" Enter updated Email : ");
                email = userOption.nextLine();
                if (!Objects.equals(email, ""))
                    cashier.setEmail(email);

                if (getConfirmation(userOption, "update")) {
                    System.out.println(CashierController.updateCashier(cashier));
                    // update the report
                    FileOperator.writeFile("Cashier Report", cashier.getCashierID(), "Update", getDate(), getTime(), FileOperator.readFile("Cashier Report"));
                }
                break;
            case 4:
                System.out.println(" ---Remove a Cashier---");
                cashier = new Cashier();
                while (true) {
                    System.out.print(" Enter Cashier ID or User Name : ");
                    userDetails = userOption.nextLine();
                    cashier.setCashierID(userDetails);
                    cashier.setUserName(userDetails);
                    if (CashierController.selectCashier(cashier))
                        break;
                    else
                        System.out.println(" Wrong Cashier ID or Contact Number entered!! Try again...");
                }
                cashier.displayCashier();


                if (getConfirmation(userOption, "delete")) {
                    System.out.println(CashierController.deleteCashier(cashier));
                    FileOperator.writeFile("Cashier Report", cashier.getCashierID(), "Delete", getDate(), getTime(), FileOperator.readFile("Cashier Report"));
                }
                break;
            case 5:
                System.out.println(" ---Search a Cashier---");
                cashier = new Cashier();

                while (true) {
                    System.out.print(" Enter Cashier ID or User Name or Contact Number : ");
                    userDetails = userOption.nextLine();
                    cashier.setCashierID(userDetails);
                    cashier.setUserName(userDetails);
                    cashier.setContactNumber(userDetails);
                    if (CashierController.selectCashier(cashier))
                        break;
                    else
                        System.out.println(" Wrong Cashier ID or Contact Number entered!! Try again...");
                }
                cashier.displayCashier();
                break;
            case 6:
                System.out.println(" ---Display all Cashiers---");
                cashier = new Cashier();
                CashierController.selectAllCashiers(cashier);
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }

    }


    private static void adminTask(Scanner userOption) throws SQLException, ClassNotFoundException, FileNotFoundException {

        Product product;
        Customer customer;
        Invoice invoice;
        Cashier cashier;

        System.out.print("""
                1. View all the generated invoices for a particular time duration.
                2. View all the generated invoices for a particular customer.
                3. Check Product Report.
                4. Check Customer Report.
                5. Check Invoice Report.
                6. Check Cashier Report.
                7. Check last insert Product details.
                8. Check last insert Customer details.
                9. Check last insert Invoice details.
                10. Check last insert Cashier details.
                Your option : """);

        switch (Integer.parseInt(userOption.nextLine())) {
            case 1:
                System.out.println(" ---View all the generated invoices for a particular time duration---");
                invoice = new Invoice();
                System.out.print(" Enter the starting date (YYYY-MM-DD) : ");
                String startingDate = userOption.nextLine();
                System.out.print(" Enter the ending date (YYYY-MM-DD) : ");
                String endingDate = userOption.nextLine();
                InvoiceController.selectAllInvoices(invoice, 1, startingDate, endingDate);
                break;
            case 2:
                System.out.println(" ---View all the generated invoices for a particular customer---");
                invoice = new Invoice();
                System.out.print(" Enter customer ID or Contact number : ");
                String customerDetails = userOption.nextLine();
                invoice.setCustomerID(customerDetails);
                invoice.setCustomerContactNumber(customerDetails);
                InvoiceController.selectAllInvoices(invoice, 2, "", "");
                break;
            case 3:
                System.out.println(" ---Product Report---\n");
                System.out.println(FileOperator.readFile("Product Report"));
                break;
            case 4:
                System.out.println(" ---Customer Report---\n");
                System.out.println(FileOperator.readFile("Customer Report"));
                break;
            case 5:
                System.out.println(" ---Invoice Report---\n");
                System.out.println(FileOperator.readFile("Invoice Report"));
                break;
            case 6:
                System.out.println(" ---Cashier Report---\n");
                System.out.println(FileOperator.readFile("Cashier Report"));
                break;
            case 7:
                System.out.println(" ---Check last insert Product details---");
                product = new Product();
                ProductController.selectLastProduct(product);
                product.displayProduct();
                break;
            case 8:
                System.out.println(" ---Check last insert Customer details---");
                customer = new Customer();
                CustomerController.selectLastCustomer(customer);
                customer.displayCustomer();
                break;
            case 9:
                System.out.println(" ---Check last insert Invoice details---");
                invoice = new Invoice();
                InvoiceController.selectLastInvoice(invoice);
                invoice.displayInvoice();
                break;
            case 10:
                System.out.println(" ---Check last insert Cashier details---");
                cashier = new Cashier();
                CashierController.selectLastCashier(cashier);
                cashier.displayCashier();
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }
    }


    private static String getNextID(char startingLetter, boolean haveLast, String lastID) {

        if (haveLast) {
            String[] id = lastID.split("-");
            int num = Integer.parseInt(id[1]);
            num++;
            return startingLetter + "-" + String.format("%06d", num);
        }
        return startingLetter + "-" + String.format("%06d", 1);
    }


    private static boolean getConfirmation(Scanner userOption, String question){
        System.out.print("\n Are you sure you want to " + question + "? \n 1. Yes \n 2. No \n Your Option : ");
        return Objects.equals(userOption.nextLine(), "1");
    }

    private static String getDate(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime currentDate = LocalDateTime.now();
        return date.format(currentDate);
    }

    private static String getTime(){
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime currentTime = LocalDateTime.now();
        return time.format(currentTime);
    }

}
