package org.servlets.dg;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hibernate.OrderFunctiontality;
import model.Order;
import model.StateOrder;

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

        final List<Order> orders = OrderFunctiontality.getOrdersByDG(request.getParameter("dgEmail"));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        if(orders == null) return;

        Order activeOrder = new Order();

        for(Order order: orders){
            if(order.getStateOrder() == StateOrder.DELIVERING){
                activeOrder = order;
                break;
            }
        }

        String json = gson.toJson(activeOrder);
        PrintWriter out = response.getWriter();
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
                default:
                    stateOrder = StateOrder.WAITING;
                    break;
            }
            order.setStateOrder(stateOrder);
            OrderFunctiontality.modifyModel(order);
        }
    }
}
