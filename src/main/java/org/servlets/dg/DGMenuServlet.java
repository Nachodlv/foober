package org.servlets.dg;

import com.google.gson.Gson;
import hibernate.DGFunctionality;
import model.DeliveryGuy;
import model.Order;
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
import java.io.PrintWriter;
import java.util.Set;

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
        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy(loginedUser.getEmail());
        AppUtils.storeLoginedUser(request.getSession(), deliveryGuy);

        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/dg/dgMenuView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        final String dgEmail = request.getParameter("dgEmail");
        final String state = request.getParameter("state");

        DeliveryGuy deliveryGuy = DGFunctionality.getDeliveryGuy(dgEmail);

        if (state != null) changeState(state, deliveryGuy, response, request);
    }

    private void changeState(String state, DeliveryGuy deliveryGuy, HttpServletResponse response, HttpServletRequest request) throws IOException {
        StateDG stateDG;
        switch (state){
            case "ONLINE_WAITING":
                stateDG = StateDG.ONLINE_WAITING;
                break;
            case "ONLINE_WORKING":
                stateDG = StateDG.ONLINE_WORKING;
                break;
            default:
                stateDG = StateDG.OFFLINE;
                break;
        }
        deliveryGuy.setState(stateDG);
        DGFunctionality.modifyModel(deliveryGuy);

//        response.setContentType("text/plain");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(state);
        //response.sendRedirect(request.getContextPath() + "/dgMenu");
    }
}
