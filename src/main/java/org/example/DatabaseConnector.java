package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    // Method to establish a connection to the SQLite database
    public Connection connect() {
        Connection connection = null;
        try {
            // Specify the path to your SQLite database file
            String url = "jdbc:sqlite:C:/Users/16313/Downloads/sqlitestudio_x64-3.4.4/SQLiteStudio//Bookstore";
            connection = DriverManager.getConnection(url);
            System.out.println("Successfully connected to the SQLite database!");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
        }
        return connection;
    }

    // Method to execute a SELECT query and print all records from the 'books' table
    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // Establish a connection
            connection = this.connect();
            // SQL query to select all records from the 'books' table
            String sql = "SELECT * FROM books";
            // Create a statement object
            stmt = connection.createStatement();
            // Execute the query and get the result set
            rs = stmt.executeQuery(sql);

            // Iterate over the result set and print each record
            while (rs.next()) {
                System.out.println(rs.getString("title") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of their creation
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Main method to test the connection and query execution
    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.selectAllBooks();
    }
}
