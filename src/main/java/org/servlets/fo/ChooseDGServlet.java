package org.servlets.fo;

import hibernate.DGFunctionality;
import hibernate.FOFunctionality;
import model.DeliveryGuy;
import model.FranchiseOwner;
import model.StateDG;
import org.securityfilter.AppUtils;
import org.utils.GoogleMail;
import org.utils.Utils;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/chooseDG")
public class ChooseDGServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FranchiseOwner franchiseOwner = FOFunctionality.getFranchiseOwner(AppUtils.getLoginedUser(request.getSession()).getEmail());
        List<DeliveryGuy> deliveryGuys = DGFunctionality.getAllDeliveryGuys();
        if (deliveryGuys != null) deliveryGuys = filterDeliveryGuys(deliveryGuys);

        request.setAttribute("deliveryGuys", deliveryGuys);
        RequestDispatcher dispatcher //
                = this.getServletContext()//
                .getRequestDispatcher("/WEB-INF/views/fo/chooseDGView.jsp");


        dispatcher.forward(request, response);
    }

    private List<DeliveryGuy> filterDeliveryGuys(List<DeliveryGuy> deliveryGuys) {
        ArrayList<DeliveryGuy> onlineDGs = new ArrayList<>();
        for (DeliveryGuy deliveryGuy : deliveryGuys) {
            if (deliveryGuy.getState().equals(StateDG.ONLINE_WAITING)) {
                onlineDGs.add(deliveryGuy);
            }
        }
        return onlineDGs;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String clientMail = request.getParameter("mail");

        if(clientMail != null) {
            try {
                GoogleMail.send("iFoober", "fooberlab1", "ignacio.delavega@ing.austral.edu.ar", "TEST", "msg");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect(request.getContextPath() + "/chooseDG");
    }
}
