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

        Scanner userOption = new Scanner(System.in);
        int userInput;
        String userName;
        String password;
        boolean isRunning = true;

        System.out.println(" \n\t\t ---ABC Company--- ");
        System.out.println(" ---Welcome to Invoice System---");
        System.out.println("\n Sign in to the system");

        try {
            User user = new User();

            // get user credentials to start the program
            while (true) {
                System.out.print(" Enter User Name : ");
                userName = userOption.nextLine();
                user.setUserName(userName);
                if (UserController.selectUser(user)) {
                    System.out.print(" Enter Password : ");
                    password = userOption.nextLine();
                    if (Objects.equals(user.getPassword(), password))
                        break;
                    else
                        System.out.println(" Wrong password entered!! Try again...");
                } else
                    System.out.println(" Wrong user name entered!! Try again...");
                user.setUserID("");
                user.setUserName("");
                user.setContactNumber("");
            }
            System.out.print("\n Logging.");
            for (int i = 0; i < 10; i++) {
                System.out.print(".");
                Thread.sleep(250);
            }

            System.out.println(" \n\n\n\t\t ---ABC Company--- ");
            System.out.println(" ---Welcome to Invoice System---");

            // main program
            while (isRunning) {
                System.out.println("\n 1. Manage Products\n 2. Manage Customers\n 3. Invoice Generation\n 4. Admin Tasks\n 5. Manage Users\n 0. Exit");
                System.out.print(" Your Option : ");
                userInput = userOption.nextInt();

                switch (userInput) {
                    case 1:
                        System.out.println("\n ---Manage Products---");
                        manageProducts();
                        break;
                    case 2:
                        System.out.println("\n ---Manage Customers---");
                        manageCustomers();
                        break;
                    case 3:
                        System.out.println("\n ---Invoice Generation---");
                        generateInvoice();
                        break;
                    case 4:
                        System.out.println("\n ---Admin Tasks---");
                        adminTask();
                        break;
                    case 5:
                        System.out.println("\n ---Manage Users---");
                        manageUsers();
                        break;
                    case 0:
                        System.out.print("\n Are you sure you want to exit? \n 1. Yes \n 2. No \n Your Option : ");
                        if (userOption.nextInt() == 1) {
                            isRunning = false;
                        }
                        break;
                    default:
                        System.out.println(" Invalid option entered! Try again \n");
                }

            }
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(" Sever not found!!");
            exception.printStackTrace();
        } catch (IOException exception) {
            System.out.println(" An error occurred while printing the bill.");
        } catch (InterruptedException exception) {
            System.out.println(" Error in starting the program!!");
        }

        System.out.println("\n Exiting the system...... \n Have a nice Day !!");
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

        System.out.println(" 1. Add a Product \n 2. Update Product Details \n 3. Remove a Product \n 4. Search a Product \n 5. Display all Products");
        System.out.print(" Your Option : ");
        userInput = userOption.nextInt();
        userOption.nextLine();

        switch (userInput) {
            case 1:
                product = new Product();

                System.out.println(" ---Add a Product---");
                productID = getNextID('P', ProductController.selectLastProduct(product), product.getProductID());
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
                    System.out.println(ProductController.addProduct(product));
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
                    System.out.println(ProductController.updateProduct(product));
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

                System.out.print("\n Are you sure you want to delete? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(ProductController.deleteProduct(product));
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


        System.out.println(" 1. Add a Customer \n 2. Update Customer Details \n 3. Remove a Customer \n 4. Search a Customer \n 5. Display all Customers");
        System.out.print(" Your Option : ");
        userInput = userOption.nextInt();
        userOption.nextLine();

        switch (userInput) {
            case 1:
                customer = new Customer();

                System.out.println(" ---Add a Customer---");
                customerID = getNextID('C', CustomerController.selectLastCustomer(customer), customer.getCustomerID());
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
                    System.out.println(CustomerController.addCustomer(customer));
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
                    System.out.println(CustomerController.updateCustomer(customer));
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

                System.out.print("\n Are you sure you want to delete? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(CustomerController.deleteCustomer(customer));
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

        Product product = new Product();

        Invoice invoice = new Invoice();

        invoiceNumber = getNextID('I', InvoiceController.selectLastInvoice(invoice), invoice.getInvoiceNumber());
        invoice.setInvoiceNumber(invoiceNumber);

        invoice.setCurrentDate(date.format(entryDateTime));
        invoice.setCheckInTime(time.format(entryDateTime));

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
                    discountPerUnit.add((product.getSellingPrice() * discountPercentage) / 100); /// discount eka product by product denna oned naththn
                    numberOfUnits.add(buyingQty); /// over all price ekata discount ekak denawada

                    product.setQty(product.getQty() - buyingQty);
                    ProductController.updateProduct(product);

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
            System.out.println(" Balance : " + String.format("%.2f", invoice.getBalanceAmount()));
        } else
            invoice.setPaymentMethod("Card");


        LocalDateTime exitDateTime = LocalDateTime.now();
        invoice.setCheckOutTime(time.format(exitDateTime));


        System.out.println(InvoiceController.addInvoice(invoice));

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

        System.out.println(" 1. Add a User\n 2. Reset User Password\n 3. Update User Details\n 4. Remove a User\n 5. Search a User\n 6. Display all Users");
        System.out.print(" Your Option : ");
        userInput = userOption.nextInt();
        userOption.nextLine();

        switch (userInput) {
            case 1:
                user = new User();

                System.out.println(" ---Add a User---");
                userID = getNextID('U', UserController.selectLastUser(user), user.getUserID());
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
                    System.out.println(UserController.addUser(user));
                }
                break;
            case 2:
                System.out.println(" ---Reset User Password---");
                user = new User();
                while (true) {
                    System.out.print(" Enter User ID or Contact Number : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setContactNumber(userDetails);
                    if (UserController.selectUser(user))
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                user.displayUser();

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
                    System.out.println(UserController.updateUser(user));
                }
                break;
            case 3:
                System.out.println(" ---Update User Details---");

                user = new User();
                while (true) {
                    System.out.print(" Enter User ID or Contact Number : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setContactNumber(userDetails);
                    if (UserController.selectUser(user))
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                user.displayUser();

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
                    System.out.println(UserController.updateUser(user));
                }
                break;
            case 4:
                System.out.println(" ---Remove a User---");
                user = new User();
                while (true) {
                    System.out.print(" Enter User ID or User Name : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setUserName(userDetails);
                    if (UserController.selectUser(user))
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                user.displayUser();

                System.out.print("\n Are you sure you want to delete? \n 1. Yes \n 2. No \n Your Option : ");
                if (userOption.nextInt() == 1) {
                    System.out.println(UserController.deleteUser(user));
                }
                break;
            case 5:
                System.out.println(" ---Search a User---");
                user = new User();

                while (true) {
                    System.out.print(" Enter User ID or User Name or Contact Number : ");
                    userDetails = userOption.nextLine();
                    user.setUserID(userDetails);
                    user.setUserName(userDetails);
                    user.setContactNumber(userDetails);
                    if (UserController.selectUser(user))
                        break;
                    else
                        System.out.println(" Wrong Customer ID or Contact Number entered!! Try again...");
                }
                user.displayUser();
                break;
            case 6:
                System.out.println(" ---Display all Users---");
                user = new User();
                UserController.selectAllUsers(user);
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
                ProductController.selectLastProduct(product);
                product.displayProduct();
                break;
            case 2:
                System.out.println(" ---Check last insert Customer details---");
                Customer customer = new Customer();
                CustomerController.selectLastCustomer(customer);
                customer.displayCustomer();
                break;
            case 3:
                System.out.println(" ---Check last insert Invoice details---");
                Invoice invoice = new Invoice();
                InvoiceController.selectLastInvoice(invoice);
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
