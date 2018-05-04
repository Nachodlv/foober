package org.servlets.dg;

import hibernate.DGFunctionality;
import model.DeliveryGuy;
import model.StateDG;
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
                .getRequestDispatcher("/WEB-INF/views/dg/dgMenuView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DeliveryGuy deliveryGuy = (DeliveryGuy) AppUtils.getLoginedUser(request.getSession());
        final String state = request.getParameter("state");

        if (state != null) changeState(state, deliveryGuy, response, request);
    }

    private void changeState(String state, DeliveryGuy deliveryGuy, HttpServletResponse response, HttpServletRequest request) throws IOException {
        if (state.equals("offline")) {
            deliveryGuy.setState(StateDG.OFFLINE);
        } else {
            if (state.equals("online")) {
                deliveryGuy.setState(StateDG.ONLINE_WAITING);
            }
        }

        DGFunctionality.modifyModel(deliveryGuy);
        response.sendRedirect(request.getContextPath() + "/dgMenu");
    }
}
