package org.servlets.dg;

import hibernate.DGFunctionality;
import hibernate.OrderFunctiontality;
import model.DeliveryGuy;
import model.Order;
import model.StateOrder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dgReview")
public class DGReviewServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String review = request.getParameter("review");
        String dgEmail = request.getParameter("dgEmail");
        String orderID = request.getParameter("orderID");

        final Order order = OrderFunctiontality.getOrder(Integer.parseInt(orderID));
        order.setRatingDG(Double.valueOf(review));
        order.setStateOrder(StateOrder.REVIEWED);
        OrderFunctiontality.modifyModel(order);

        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy(dgEmail);
        Double reviewDouble = Double.valueOf(review);

        deliveryGuy.addRating(reviewDouble);
        DGFunctionality.modifyModel(deliveryGuy);
    }
}
