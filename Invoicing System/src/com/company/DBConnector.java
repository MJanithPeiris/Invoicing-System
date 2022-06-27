package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DBConnector {

    private Connection connection;

    public DBConnector() { }

    public void setDBConnection() throws ClassNotFoundException, SQLException {
        // System.out.println("Program initiated");
        Class.forName("com.mysql.jdbc.Driver");
        // System.out.println("Published the driver");
        String[] connectionString = readConnectionString();
        connection = DriverManager.getConnection(connectionString[0], connectionString[1], connectionString[2]);
        // System.out.println("Connected");
    }

    public Connection getDBConnection() {
        return connection;
    }

    // get the connection string reading a file
    private String[] readConnectionString() {
        String[] data = {"", "", ""};
        try {
            File myObj = new File("ConnectionString.txt");
            Scanner reader = new Scanner(myObj);
            int i = 0;
            while (reader.hasNextLine()) {
                data[i] = reader.nextLine();
                i++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not Found");
        }
        return data;
    }
}
