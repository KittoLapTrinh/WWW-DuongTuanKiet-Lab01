package com.example.wwwduongtuankietlab01.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static Connection con;

    public static Connection getInstance() {
        // Initialize the connection in the constructor
        try {
            // Load the MariaDB JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            // Establish the database connection
            String url = "jdbc:mariadb://localhost:3306/mydb"; // Replace with your database URL
            String username = "sa"; // Replace with your MariaDB username
            String password = "sapassword"; // Replace with your MariaDB password

            con = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }


}
