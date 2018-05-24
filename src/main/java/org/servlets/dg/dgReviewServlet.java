package org.servlets.dg;

import hibernate.DGFunctionality;
import model.DeliveryGuy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dgReview")
public class dgReviewServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String review = request.getParameter("review");
        String dgEmail = request.getParameter("dgEmail");

        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy(dgEmail);
        Double reviewDouble = Double.valueOf(review);

        deliveryGuy.addRating(reviewDouble);
        DGFunctionality.modifyModel(deliveryGuy);
    }
}
