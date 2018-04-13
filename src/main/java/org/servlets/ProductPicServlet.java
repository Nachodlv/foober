package org.servlets;

import org.utils.Utils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

import static org.utils.Utils.getHSQLConnection;

/**
 * @author Gonzalo de Achaval
 */
@WebServlet("/productPic")
public class ProductPicServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,
            ServletException {
        Blob photo;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        final String idProduct = request.getParameter("id");

        String query = "SELECT IMAGE FROM PRODUCT WHERE ID=";
        String finalQuery = query + idProduct;
        ServletOutputStream out = response.getOutputStream();

        try {
            conn = getHSQLConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(finalQuery);
            if (rs.next()) {
                photo = rs.getBlob(1);
                photo.getBinaryStream();
            } else {
                throw new RuntimeException("Img was not found");
            }
            response.setContentType("image/gif");
            InputStream in = photo.getBinaryStream();
            int length;
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];

            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            Utils.close(conn, stmt, rs);
        }
    }
}
