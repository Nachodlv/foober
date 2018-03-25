package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        int result = 0;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();

            result = stmt.executeUpdate("CREATE TABLE person (" +
                    "id INT NOT NULL," +
                    "name VARCHAR(50) NOT NULL, " +
                    "PRIMARY KEY (id));" +
                    "");

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Table created successfully");
    }
}