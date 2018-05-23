package org.servlets.fo;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/orders")
public class FOOrdersServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String foEmail = request.getParameter("foID");
        List<Order> orders = OrderFunctiontality.getOrdersByFO(foEmail);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        if(orders!= null) orders = reArrangeOrders(orders);
        String json = gson.toJson(orders);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }

    private List<Order> reArrangeOrders(List<Order> orders){
        List<Order> reArrangeOrders = new ArrayList<>();
        int waiting = 0;
        int delivering = 0;
        int delivered = 0;

        for (int i = orders.size()-1; i>=0; i--){
            Order order = orders.get(i);
            if(order.getStateOrder() == StateOrder.WAITING){
                reArrangeOrders.add(waiting, order);
                waiting ++;
                delivering ++;
                delivered ++;
            } else if (order.getStateOrder() == StateOrder.DELIVERING){
                reArrangeOrders.add(delivering, order);
                delivering++;
                delivered++;
            } else if (order.getStateOrder() == StateOrder.DELIVERED){
                reArrangeOrders.add(delivered, order);
                delivered ++;
            } else {
                reArrangeOrders.add(delivered, order);
            }
        }

        return reArrangeOrders;
    }
}
