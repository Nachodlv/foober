package hibernate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTables {

    public static void main(String[] args) {

        Connection con = null;
        Statement stmt = null;
        int result = 0;

        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver");
            con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
            stmt = con.createStatement();

            result = stmt.executeUpdate("CREATE TABLE franchiseOwner (" +
                    "email VARCHAR (100) NOT NULL," +
                    "name VARCHAR(50) NOT NULL, " +
                    "password VARCHAR (20) NOT NULL, " +
                    "phone INT NOT NULL, " +
                    "address VARCHAR (50) NOT NULL, " +
                    "menuId INT," +
                    "tippingPercentage INT NOT NULL, " +
                    "PRIMARY KEY (email));" +
                    "");

        }  catch (Exception e) {
            e.printStackTrace(System.out);
        }
        System.out.println("Table created successfully");
    }
}