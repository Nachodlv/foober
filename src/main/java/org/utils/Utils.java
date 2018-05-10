package org.utils;

import model.Product;
import webSocket.OrderMessage;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Gonzalo de Achaval
 */
public class Utils {

    private static byte[] convertStreamToByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();
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


    public static List<Product> filterProducts(List<Product> products, HttpServletRequest request) {
        String searchBy = request.getParameter("searchProduct");
        Pattern pattern;
        if (searchBy != null) pattern = Pattern.compile(".*" + searchBy.toLowerCase() + ".*");
        else pattern = Pattern.compile(".*");

        List<Product> activeProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isActive() && pattern.matcher(product.getName().toLowerCase()).matches()) {
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }

    public static void sendEmail(OrderMessage message) {
        String titleEmail = "Order received";
        String messageEmail = "You have received an order!";
        try {
            GoogleMail.send(message.getDgEmail(), titleEmail, messageEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
