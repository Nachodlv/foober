package org.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet({"/images"})
public class ImgServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String imgID = request.getParameter("imgID");
        response.setContentType("image/png");

        final String imgDir = System.getProperty("imagesHome");
        final String pathname = imgDir + "/" + imgID;
        File file = new File(pathname);

        Files.copy(file.toPath(), response.getOutputStream());
    }
}
