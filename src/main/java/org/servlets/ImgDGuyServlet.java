package org.servlets;

import model.UserAccount;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import static org.securityfilter.AppUtils.getLoginedUser;

/**
 * @author Gonzalo de Achaval
 */
@WebServlet("/imgDGuy")
public class ImgDGuyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Blob photo;
        Connection conn;
        Statement stmt = null;
        ResultSet rs = null;
        UserAccount ua = getLoginedUser(request.getSession());
        String email = ua.getEmail();

        String query = "SELECT ID FROM DELIVERYGUY WHERE EMAIL=";
        String finalQuery = query + "'" + email + "'";
        ServletOutputStream out = response.getOutputStream();

        try {
            conn = getHSQLConnection();
        } catch (Exception e) {
            response.setContentType("text/html");
            out.println("<html><head><title>Person Photo</title></head>");
            out.println("<body><h1>Database Connection Problem.</h1></body></html>");
            return;
        }

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(finalQuery);
            if (rs.next()) {
                photo = rs.getBlob(1);
            } else {
                response.setContentType("text/html");
                out.println("<html><head><title>Person Photo</title></head>");
                out.println("<body><h1>No photo found for id= 001 </h1></body></html>");
                return;
            }

            response.setContentType("image/gif");
            InputStream in = photo.getBinaryStream();
            int length;

            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            while ((length = in.read(buffer)) != -1) {
                System.out.println("writing " + length + " bytes");
                out.write(buffer, 0, length);
            }

            in.close();
            out.flush();
        } catch (SQLException e) {
            response.setContentType("text/html");
            out.println("<html><head><title>Error: Person Photo</title></head>");
            out.println("<body><h1>Error=" + e.getMessage() + "</h1></body></html>");
        } finally {
            try {
                assert rs != null;
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getHSQLConnection() throws Exception {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        System.out.println("Driver Loaded.");
        String url = "jdbc:hsqldb:hsql://localhost/testdb";
        return DriverManager.getConnection(url, "SA", "");
    }
}
