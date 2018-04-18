package org.servlets;

import hibernate.DGFunctionality;
import model.UserAccount;
import org.securityfilter.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dgMenu")
public class DGMenuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DGMenuServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserAccount loginedUser = AppUtils.getLoginedUser(request.getSession());
        AppUtils.storeLoginedUser(request.getSession(), DGFunctionality.getDeliveryGuy(loginedUser.getEmail()));

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/dgMenuView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }
}
