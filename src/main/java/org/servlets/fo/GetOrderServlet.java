package org.servlets.fo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hibernate.OrderFunctiontality;
import model.FranchiseOwner;
import model.Order;
import model.UserAccount;
import org.securityfilter.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet({"/getOrder"})
public class GetOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String orderID = request.getParameter("orderID");
        final Order order = OrderFunctiontality.getOrder(Integer.parseInt(orderID));

        final FranchiseOwner franchiseOwner = (FranchiseOwner)AppUtils.getLoginedUser(request.getSession());
        if (!order.getFranchiseOwner().getEmail().equals(franchiseOwner.getEmail())) {
            return; //TODO somehow redirect to accessDenied?
        }

        request.getSession().setAttribute("order", order);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(order);

        PrintWriter out = response.getWriter();
        out.print(json);
        out.flush();
    }
}
