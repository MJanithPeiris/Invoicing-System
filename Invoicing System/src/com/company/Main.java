package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //        Scanner userOption = new Scanner(System.in);
//        int userInput;
//        String userName;
//        String password;
//        boolean isRunning = true;
//
//        System.out.println(" \n\t\t ---ABC Company--- ");
//        System.out.println(" ---Welcome to Invoice System---");
//
//        try {
//
//            User user = new User();
//            UserController userController = new UserController(user);
//
//            while (true) {
//                System.out.print("\n\n Enter User Name : ");
//                userName = userOption.nextLine();
//                user.setUserName(userName);
//                if (userController.selectUser()) {
//                    System.out.print("\n Enter Password : ");
//                    password = userOption.nextLine();
//                    if (Objects.equals(user.getPassword(), password))
//                        break;
//                    else
//                        System.out.println(" Wrong password entered!! Try again...");
//                } else
//                    System.out.println(" Wrong user name entered!! Try again...");
//            }
//            System.out.print("\n\n Logging.");
//            for (int i = 0; i < 10; i++) {
//                System.out.print(".");
//                Thread.sleep(500);
//
//            }
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        } catch (ClassNotFoundException exception) {
//            exception.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


//        while (isRunning) {
//            System.out.println("\n 1. Manage Products\n 2. Manage Customers\n 3. Invoice Generation\n 4. Admin Tasks\n 5. Manage Users\n 0. Exit");
//            System.out.print(" Your Option : ");
//            userInput = userOption.nextInt();
//            try {
//                switch (userInput) {
//                    case 1:
//                        System.out.println("\n ---Manage Products---");
//                        manageProducts();
//                        break;
//                    case 2:
//                        System.out.println("\n ---Manage Customers---");
//                        manageCustomers();
//                        break;
//                    case 3:
//                        System.out.println("\n ---Invoice Generation---");
//                        generateInvoice();
//                        break;
//                    case 4:
//                        System.out.println("\n ---Admin Tasks---");
//                        adminTask();
//                        break;
//                    case 5:
//                        System.out.println("\n ---Manage Users---");
//                        manageUsers();
//                        break;
//                    case 0:
//                        System.out.print("\n Are you sure you want to exit? \n 1. Yes \n 2. No \n Your Option : ");
//                        if (userOption.nextInt() == 1) {
//                            isRunning = false;
//                        }
//                        break;
//                    default:
//                        System.out.println(" Invalid option entered! Try again \n");
//                }
//            } catch (SQLException | ClassNotFoundException exception) {
//                System.out.println(" Sever not found!!");
//            } catch (IOException exception) {
//                System.out.println("An error occurred in printing the bill.");
//            }
//        }
//
//        System.out.println("\n Exiting the system...... \n Have a nice Day !!");
    }

    private static void manageProducts() throws SQLException, ClassNotFoundException {
        Scanner userOption = new Scanner(System.in);
        int userInput;
        String productDetails;
        String productID;
        String productName;
        String description;
        double purchasePrice;
        double sellingPrice;
        int qty;
        Product product;
        ProductController productController;

        System.out.println(" 1. Add a Product \n 2. Update Product Details \n 3. Remove a Product \n 4. Search a Product \n 5. Display all Products");
        System.out.print(" Your Option : ");
        userInput = userOption.nextInt();
        userOption.nextLine();

        switch (userInput) {
            case 1:
                product = new Product();
                productController = new ProductController(product);

                System.out.println(" ---Add a Product---");
                productID = getNextID('P', productController.selectLastProduct(), product.getProductID());
                System.out.print(" Enter Product ID : " + productID);
                product.setProductID(productID);
                System.out.print(" Enter Product Name : ");
                product.setProductName(userOption.nextLine());
                System.out.print(" Enter Product Description : ");
                product.setDescription(userOption.nextLine());
                System.out.print(" Enter Product Purchase Price : ");
                product.setPurchasePrice(userOption.nextDouble());
                System.out.print(" Enter Product Selling Price : ");
                product.setSellingPrice(userOption.nextDouble());
                System.out.print(" Enter Product Quantity : ");
                product.setQty(userOption.nextInt());

                System.out.print("\n Are you sure you want to add? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(productController.addProduct());
                }
                break;
            case 2:
                System.out.println(" ---Update Product Details---");
                product = new Product();
                productController = new ProductController(product);

                while (true) {
                    System.out.print(" Enter Product ID or Name : ");
                    productDetails = userOption.nextLine();
                    product.setProductID(productDetails);
                    product.setProductName(productDetails);
                    if (productController.selectProduct())
                        break;
                    else
                        System.out.println(" Wrong Product ID or Name entered!! Try again...");
                }
                product.displayProduct();

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

                System.out.print("\n Are you sure you want to update? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(productController.updateProduct());
                }
                break;
            case 3:
                System.out.println(" ---Remove a Product---");
                product = new Product();
                productController = new ProductController(product);

                while (true) {
                    System.out.print(" Enter Product ID or Name : ");
                    productDetails = userOption.nextLine();
                    product.setProductID(productDetails);
                    product.setProductName(productDetails);
                    if (productController.selectProduct())
                        break;
                    else
                        System.out.println(" Wrong Product ID or Name entered!! Try again...");
                }
                product.displayProduct();

                System.out.print("\n Are you sure you want to delete? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(productController.deleteProduct());
                }
                break;
            case 4:
                System.out.println(" ---Search a Product---");
                product = new Product();
                productController = new ProductController(product);

                while (true) {
                    System.out.print(" Enter Product ID or Name : ");
                    productDetails = userOption.nextLine();
                    product.setProductID(productDetails);
                    product.setProductName(productDetails);
                    if (productController.selectProduct())
                        break;
                    else
                        System.out.println(" Wrong Product ID or Name entered!! Try again...");
                }
                product.displayProduct();
                break;
            case 5:
                System.out.println(" ---Display all Products---");
                product = new Product();
                productController = new ProductController(product);
                productController.selectAllProducts();
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }
    }


    private static void manageCustomers() throws SQLException, ClassNotFoundException {
        Scanner userOption = new Scanner(System.in);
        int userInput;
        String customerDetails;
        String customerID;
        String customerName;
        String email;
        String address;
        String contactNumber;
        String dob;
        String gender;
        Customer customer;
        CustomerController customerController;


        System.out.println(" 1. Add a Customer \n 2. Update Customer Details \n 3. Remove a Customer \n 4. Search a Customer \n 5. Display all Customers");
        System.out.print(" Your Option : ");
        userInput = userOption.nextInt();
        userOption.nextLine();

        switch (userInput) {
            case 1:
                customer = new Customer();
                customerController = new CustomerController(customer);

                System.out.println(" ---Add a Customer---");
                customerID = getNextID('C', customerController.selectLastCustomer(), customer.getCustomerID());
                System.out.print(" Enter Customers' ID : " + customerID);
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

                System.out.print("\n Are you sure you want to add? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(customerController.addCustomer());
                }
                break;
            case 2:
                System.out.println(" ---Update Customer Details---");
                customer = new Customer();
                customerController = new CustomerController(customer);

                while (true) {
                    System.out.print(" Enter Customer ID or Contact Number : ");
                    customerDetails = userOption.nextLine();
                    customer.setCustomerID(customerDetails);
                    customer.setContactNumber(customerDetails);
                    if (customerController.selectCustomer())
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                customer.displayCustomer();

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

                System.out.print("\n Are you sure you want to update? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(customerController.updateCustomer());
                }
                break;
            case 3:
                System.out.println(" ---Remove a Customer---");
                customer = new Customer();
                customerController = new CustomerController(customer);

                while (true) {
                    System.out.print(" Enter Customer ID or Contact Number : ");
                    customerDetails = userOption.nextLine();
                    customer.setCustomerID(customerDetails);
                    customer.setContactNumber(customerDetails);
                    if (customerController.selectCustomer())
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                customer.displayCustomer();

                System.out.print("\n Are you sure you want to delete? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(customerController.deleteCustomer());
                }
                break;
            case 4:
                System.out.println(" ---Search a Customer---");
                customer = new Customer();
                customerController = new CustomerController(customer);

                while (true) {
                    System.out.print(" Enter Customer ID or Contact Number : ");
                    customerDetails = userOption.nextLine();
                    customer.setCustomerID(customerDetails);
                    customer.setContactNumber(customerDetails);
                    if (customerController.selectCustomer())
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                customer.displayCustomer();

                break;
            case 5:
                System.out.println(" ---Display all Customers---");
                customer = new Customer();
                customerController = new CustomerController(customer);
                customerController.selectAllCustomers();
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }
    }


    private static void generateInvoice() throws SQLException, ClassNotFoundException, IOException {
        Scanner userOption = new Scanner(System.in);
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime entryDateTime = LocalDateTime.now();

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

        Customer customer = new Customer();
        CustomerController customerController = new CustomerController(customer);
        Product product = new Product();
        ProductController productController = new ProductController(product);

        Invoice invoice = new Invoice();
        InvoiceController invoiceController = new InvoiceController(invoice);

        invoiceNumber = getNextID('I', invoiceController.selectLastInvoice(), invoice.getInvoiceNumber());
        invoice.setInvoiceNumber(invoiceNumber);

        invoice.setCurrentDate(date.format(entryDateTime));
        invoice.setCheckInTime(time.format(entryDateTime));

        while (true) {
            System.out.print(" Enter customer ID or contact number : ");
            customerDetails = userOption.nextLine();
            customer.setCustomerID(customerDetails);
            customer.setContactNumber(customerDetails);
            if (customerController.selectCustomer()) {
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
            if (productController.selectProduct()) {
                System.out.print(" Number of Units : ");
                buyingQty = Integer.parseInt(userOption.nextLine());

                if (product.getQty() - buyingQty > 0) { // check sufficient stock is available to buy
                    System.out.print(" Discount Percentage : ");
                    discountPercentage = Double.parseDouble(userOption.nextLine());

                    productIDs.add(product.getProductID());
                    products.add(product.getProductName());
                    unitPrice.add(product.getSellingPrice());
                    discountPerUnit.add((product.getSellingPrice() * discountPercentage) / 100); /// discount eka product by product denna oned naththn
                    numberOfUnits.add(buyingQty); /// over all price ekata discount ekak denawada

                    product.setQty(product.getQty() - buyingQty);
                    productController.updateProduct();

                    totalDiscount += (product.getSellingPrice() * discountPercentage) / 100;
                    subTotal += product.getSellingPrice() * buyingQty;
                } else {
                    System.out.println(product.getQty() + " only left in the stock!!");
                }

            } else {
                System.out.println(" Wrong product ID or Name!! Try again...");
                continue;
            }

            System.out.println(" Do you want to add more items?\n 1. Yes\n 2. No\n Your Option : ");
            userInput = Integer.parseInt(userOption.nextLine());
            if (userInput != 1)
                break;
        }

        invoice.setProductIDs(productIDs);
        invoice.setProducts(products);
        invoice.setNumberOfUnits(numberOfUnits);
        invoice.setUnitPrice(unitPrice);
        invoice.setDiscountPerUnit(discountPerUnit);
        invoice.setSubTotal(subTotal);
        invoice.setTotalDiscount(totalDiscount);
        invoice.setTotalPrice(subTotal - totalDiscount);

        invoice.displayBill();

        System.out.print("\n\n Enter your Payment Method\n 1. Cash \n 2. Card \n Your Option : ");
        userInput = Integer.parseInt(userOption.nextLine());

        if (userInput == 1) {
            invoice.setPaymentMethod("Cash");
            System.out.print(" Enter the cash amount :");
            cashAmount = Double.parseDouble(userOption.nextLine());
            invoice.setCashAmount(cashAmount);
            invoice.setBalanceAmount(cashAmount - (subTotal - totalDiscount));
        } else
            invoice.setPaymentMethod("Card");

        System.out.println(" Balance : " + String.format("%.2f", invoice.getBalanceAmount()));
        LocalDateTime exitDateTime = LocalDateTime.now();
        invoice.setCheckOutTime(time.format(exitDateTime));


        System.out.println(invoiceController.addInvoice());

        printBill(invoice);
    }

    private static void manageUsers() throws SQLException, ClassNotFoundException {
        Scanner userOption = new Scanner(System.in);
        int userInput;
        String userDetails;
        String userID;
        String name;
        String userName;
        String password;
        String confirmPassword;
        String contactNumber;
        String email;

        User user;
        UserController userController;

        System.out.println(" 1. Add a User\n 2. Reset User Password\n 3. Update User Details\n 4. Remove a User\n 5. Search a User\n 6. Display all Users");
        System.out.print(" Your Option : ");
        userInput = userOption.nextInt();
        userOption.nextLine();

        switch (userInput) {
            case 1:
                user = new User();
                userController = new UserController(user);

                System.out.println(" ---Add a User---");
                userID = getNextID('U', userController.selectLastUser(), user.getUserID());
                System.out.print(" Enter User ID : " + userID);
                user.setUserID(userID);
                System.out.print(" Enter Name : ");
                user.setName(userOption.nextLine());
                System.out.print(" Enter User Name : ");
                user.setUserName(userOption.nextLine());
                do {
                    System.out.print(" Enter Password : ");
                    password = userOption.nextLine();
                    System.out.print(" Enter Confirm Password : ");
                    confirmPassword = userOption.nextLine();
                } while (!Objects.equals(password, confirmPassword));
                user.setPassword(password);
                System.out.print(" Enter Contact Number : ");
                user.setContactNumber(userOption.nextLine());
                System.out.print(" Enter Email : ");
                user.setEmail(userOption.nextLine());

                System.out.print("\n Are you sure you want to add? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(userController.addUser());
                }
                break;
            case 2:
                System.out.println(" ---Reset User Password---");
                user = new User();
                userController = new UserController(user);
                while (true) {
                    System.out.print(" Enter User ID or Contact Number : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setContactNumber(userDetails);
                    if (userController.selectUser())
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                // display karanna

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
                user.setPassword(password);

                System.out.print("\n Are you sure you want to update? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(userController.updateUser());
                }
                break;
            case 3:
                System.out.println(" ---Update User Details---");

                user = new User();
                userController = new UserController(user);
                while (true) {
                    System.out.print(" Enter User ID or Contact Number : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setContactNumber(userDetails);
                    if (userController.selectUser())
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                //user.displayDetails();

                System.out.print("\n Enter updated Name : ");
                name = userOption.nextLine();
                if (!Objects.equals(name, ""))
                    user.setName(name);

                System.out.print(" Enter updated User Name : ");
                userName = userOption.nextLine();
                if (!Objects.equals(userName, ""))
                    user.setUserName(userName);

                System.out.print(" Enter updated Contact Number : ");
                contactNumber = userOption.nextLine();
                if (!Objects.equals(contactNumber, ""))
                    user.setContactNumber(contactNumber);

                System.out.print(" Enter updated Email : ");
                email = userOption.nextLine();
                if (!Objects.equals(email, ""))
                    user.setEmail(email);


                System.out.print("\n Are you sure you want to update? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(userController.updateUser());
                }
                break;
            case 4:
                System.out.println(" ---Remove a User---");
                user = new User();
                userController = new UserController(user);
                while (true) {
                    System.out.print(" Enter User ID or User Name : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setUserName(userDetails);
                    if (userController.selectUser())
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                // product.displayDetails();

                System.out.print("\n Are you sure you want to delete? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(userController.deleteUser());
                }
                break;
            case 5:
                System.out.println(" ---Search a User---");
                user = new User();
                userController = new UserController(user);

                while (true) {
                    System.out.print(" Enter User ID or User Name or Contact Number : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setUserName(userDetails);
                    user.setContactNumber(userDetails);
                    if (userController.selectUser())
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                // display
                break;
            case 6:
                System.out.println(" ---Display all Users---");
                user = new User();
                userController = new UserController(user);
                userController.selectAllUsers();
                break;
            default:
                System.out.println(" Invalid option entered! Try again");
        }

    }

    // admin task complete karanna
    private static void adminTask() throws SQLException, ClassNotFoundException {
        Scanner userOption = new Scanner(System.in);
        int userInput;

        System.out.print(" 1. Check last insert Product details.\n 2. Check last insert Customer details.\n 3. Check last insert Invoice details.\n 4. View all Invoices.\n 5. Search for an Invoice\n 6. Check last updated table.\n Your option : ");
        userInput = userOption.nextInt();
        switch (userInput) {
            case 1:
                System.out.println(" ---Check last insert Product details---");
                Product product = new Product();
                ProductController productController = new ProductController(product);
                productController.selectLastProduct();
                product.displayProduct();
                break;
            case 2:
                System.out.println(" ---Check last insert Customer details---");
                Customer customer = new Customer();
                CustomerController customerController = new CustomerController(customer);
                customerController.selectLastCustomer();
                customer.displayCustomer();
                break;
            case 3:
                System.out.println(" ---Check last insert Invoice details---");
                Invoice invoice = new Invoice();
                InvoiceController invoiceController = new InvoiceController(invoice);
                invoiceController.selectLastInvoice();
                invoice.displayAllInvoices();
                break;
            case 4:
            case 5:
            case 6:

                break;
            default:
        }


        /*SELECT UPDATE_TIME, TABLE_SCHEMA, TABLE_NAME
        FROM information_schema.tables
        WHERE TABLE_SCHEMA = 'abc' AND TABLE_NAME = 'product_details'
        ORDER BY UPDATE_TIME DESC;*/

    }

    private static void printBill(Invoice invoice) throws IOException {
        File temp = Paths.get("Invoices").toAbsolutePath().normalize().toFile();
        FileWriter writer = new FileWriter(temp + "\\" + invoice.getInvoiceNumber() + ".txt");
        writer.write(" \n\t\t ---ABC Company--- ");
        writer.write("\n\n\n Invoice Number : " + invoice.getInvoiceNumber() + "\n Date : " + invoice.getCurrentDate());
        writer.write("\n Check in Time : " + invoice.getCheckInTime() + "\n Check out Time : " + invoice.getCheckOutTime());
        writer.write("\n\n Product Name \t\t Quantity \t Unit Price(Rs.)\n");
        for (int index = 0; index < invoice.getProducts().size(); index++) {
            writer.write("\n " + invoice.getProducts().get(index));
            writer.write("\n " + invoice.getProductIDs().get(index) + "\t\t    " + invoice.getNumberOfUnits().get(index) + "\t\t    " + invoice.getUnitPrice().get(index));
            writer.write("\n Discount for product \t\t\t     -" + invoice.getDiscountPerUnit().get(index));
        }
        writer.write("\n\n Sub Total      : Rs." + String.format("%.2f", invoice.getSubTotal()));
        writer.write("\n Total Discount : Rs." + String.format("%.2f", invoice.getTotalDiscount()));
        writer.write("\n\n Total : Rs." + String.format("%.2f", (invoice.getTotalPrice())));
        if (Objects.equals(invoice.getPaymentMethod(), "Cash")) {
            writer.write("\n Cash : Rs." + invoice.getCashAmount());
            writer.write("\n Change : Rs." + String.format("%.2f", (invoice.getBalanceAmount())));
        } else {
            writer.write("\n Card");
        }
        writer.write("\n\n Customer ID : " + invoice.getCustomerID());
        writer.write("\n Customer Name : " + invoice.getCustomerName());
        writer.write("\n Customer Contact Number : " + invoice.getCustomerContactNumber());
        writer.write("\n\n\n\t   Thank you for Shopping with us \n\t\t   Come again!!");
        writer.close();
        System.out.println("Bill has been printed successfully!!.");
    }


    private static String getNextID(char startingLetter, boolean haveLast, String lastID) throws SQLException, ClassNotFoundException {

        if (haveLast) {
            String[] id = lastID.split("-");
            int num = Integer.parseInt(id[1]);
            num++;
            return startingLetter + "-" + String.format("%06d", num);
        }
        return startingLetter + "-" + String.format("%06d", 1);
    }


}
