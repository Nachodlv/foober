package org.servlets.dg;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/dgOrderHistory"})
public class OrderHistoryDGServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/dg/orderHistoryDGView.jsp");

        dispatcher.forward(request, response);
    }
}