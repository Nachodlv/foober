package org.servlets.fo;

import model.FranchiseOwner;
import org.securityfilter.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/foOrderHistory"})
public class OrderHistoryFOServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/fo/orderHistoryFO.jsp");

        dispatcher.forward(request, response);
    }
}