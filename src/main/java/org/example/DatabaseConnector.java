package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

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

    public void selectAllBooks() {
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {

            connection = this.connect();

            String sql = "SELECT * FROM books";

            stmt = connection.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("title") + "\t" +
                        rs.getString("author") + "\t" +
                        rs.getDouble("price"));
            }
        } catch (SQLException e) {
            System.out.println("Error executing SELECT statement");
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DatabaseConnector connector = new DatabaseConnector();
        connector.selectAllBooks();
    }
}
