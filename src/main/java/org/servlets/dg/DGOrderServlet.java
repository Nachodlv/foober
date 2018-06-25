package org.servlets.dg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hibernate.OrderFunctiontality;
import model.*;
import webSocket.OrderMessage;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/order")
public class DGOrderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String dgEmail = request.getParameter("dgEmail");
        final List<Order> orders = OrderFunctiontality.getOrdersByDG(dgEmail);
        if(orders == null){
            out.print("");
            out.flush();
            return;
        }

        Order activeOrder = new Order();
        boolean hasActiveOrder = false;

        for(Order order: orders){
            if(order.getStateOrder() == StateOrder.DELIVERING){
                activeOrder = order;
                hasActiveOrder = true;
                break;
            }
        }

        if(!hasActiveOrder){
            out.print("");
            out.flush();
            return;
        }

        FranchiseOwner franchiseOwner = activeOrder.getFranchiseOwner();
        Client client = activeOrder.getClient();
        DeliveryGuy dg = activeOrder.getDeliveryGuy();
        OrderMessage orderMessage = new OrderMessage(false, activeOrder.getId(), activeOrder.getElaborationTime(),
                activeOrder.getStateOrder().toString(), null, activeOrder.getTotalCost(),
                franchiseOwner.getTippingPercentage(), client.getPhone(), franchiseOwner.getName(), franchiseOwner.getPhone(),
                client.getAddress(), client.getEmail(), dg.getName(), dg.getPhone(), client.getName(), franchiseOwner.getAddress());

        if(activeOrder.getDeliveryGuy() != null) orderMessage.setDgEmail(activeOrder.getDeliveryGuy().getEmail());

        String json = new Gson().toJson(orderMessage);
        out.print(json);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String postId = request.getParameter("orderId");
        String state = request.getParameter("state");
        if(postId != null && state != null){
            Order order = OrderFunctiontality.getOrder(Integer.parseInt(postId));
            StateOrder stateOrder;
            switch (state){
                case "DELIVERING":
                    stateOrder = StateOrder.DELIVERING;
                    break;
                case "DELIVERED":
                    stateOrder = StateOrder.DELIVERED;
                    break;
                case "REVIEWED":
                    stateOrder = StateOrder.REVIEWED;
                    break;
                case "CANCELED":
                    stateOrder = StateOrder.CANCELED;
                    break;
                default:
                    stateOrder = StateOrder.WAITING;
                    break;
            }
            order.setStateOrder(stateOrder);
            OrderFunctiontality.modifyModel(order);
        }
    }
}
