package org.servlets.fo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hibernate.OrderFunctiontality;
import model.Order;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/orders")
public class FOOrdersServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String foEmail = request.getParameter("foID");
        final List<Order> orders = OrderFunctiontality.getOrdersByFO(foEmail);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(orders);
        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
