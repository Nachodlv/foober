package org.servlets.fo;

import hibernate.OrderFunctiontality;
import model.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/orderInfo"})
public class OrderInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String orderID = request.getParameter("orderID");
        final Order order = OrderFunctiontality.getOrder(Integer.parseInt(orderID));

        request.getSession().setAttribute("order", order);

        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/fo/orderInfoView.jsp");

        dispatcher.forward(request, response);
    }
}
