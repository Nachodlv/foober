package org.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

/**
 * @author Gonzalo de Achaval
 */
public class Utils {

    public static byte[] convertStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
    }

    public static Connection getHSQLConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        String url = "jdbc:hsqldb:hsql://localhost/testdb";
        return DriverManager.getConnection(url, "SA", "");
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                stmt.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void writeImage(String picName, Part part, ServletContext servletContext) throws IOException {
        final BufferedImage img = getImageFromPart(part);
        final String path = servletContext.getRealPath("/");
        String finalPath = path + "images/" + picName + ".png";
        final File file = new File(finalPath);
        ImageIO.write(img, "png", file);
    }

    private static BufferedImage getImageFromPart(Part image) throws IOException {
        InputStream fileContent = image.getInputStream();
        byte[] imgBytes = convertStreamToByteArray(fileContent);
        return ImageIO.read(new ByteArrayInputStream(imgBytes));
    }


}
