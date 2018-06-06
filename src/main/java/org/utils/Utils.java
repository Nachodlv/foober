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
        String path = System.getProperty("imagesHome");
        String finalPath = path + "/" + picName + ".png";
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

    public static void sendEmailDG(OrderMessage message) {
        String titleEmail = "Order received";
        String bodyTitle = "You have received an order!";
        String bodyInfo = "\nHere you have some basic information:\n<ul><li>Client address: " + message.getClientAddress() +
                "</li><li>Client name: " + message.getClientName() + "</li></ul>\nGo to the app to get full order data!\n";
        String messageBody = bodyTitle + bodyInfo;
        try {
            GoogleMail.send(message.getDgEmail(), titleEmail, messageBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailClient(OrderMessage message) {
        String titleEmail = "Order on its way";
        String bodyTitle = "Your order has already been accepted by one of our delivery guys, and it is on its way!";
        String bodyInfo = "\nIn case you need it, here is more information:\n<ul><li>Delivery-guy name: " + message.getDgName() +
                "</li><li>Delivery-guy phone: " + message.getDgPhone() + "</li></ul>\n";
        String messageBody = bodyTitle + bodyInfo;
        try {
            GoogleMail.send(message.getClientEmail(), titleEmail, messageBody);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void sendEmailDGCancelled(OrderMessage message) {
        String titleEmail = "Order cancelled";
        String bodyTitle = "Your current order has been cancelled!";
        try {
            GoogleMail.send(message.getDgEmail(), titleEmail, bodyTitle);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
