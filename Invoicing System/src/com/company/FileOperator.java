package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class FileOperator {

    public static void printBill(Invoice invoice) throws IOException {
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

    public static String readFile(String fileName) throws FileNotFoundException {

        String content = "";
        File temp = Paths.get("Reports").toAbsolutePath().normalize().toFile();
        File myObj = new File(temp + "\\" + fileName + ".txt");
        Scanner myReader = new Scanner(myObj);
        myReader.nextLine();
        while (myReader.hasNextLine()) {
            content = content + myReader.nextLine() + "\n";
        }
        myReader.close();

        return content;
    }

    public static void writeFile(String fileName, String id, String operation, String date, String time, String previousDetails) throws IOException {
        File temp = Paths.get("Reports").toAbsolutePath().normalize().toFile();
        FileWriter writer = new FileWriter(temp + "\\" + fileName + ".txt");
        writer.write("   ID\t     Operation \t    Date \t  Time \n" );
        writer.write(id + "      " + operation + "\t " + date + "\t" + time + "\n" + previousDetails );
        writer.close();
    }

}
