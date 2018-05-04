package org.servlets.dg;

import hibernate.DGFunctionality;
import model.DeliveryGuy;
import org.securityfilter.AppUtils;
import org.utils.Utils;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@MultipartConfig
@WebServlet("/dgRegister")
public class RegisterDGServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterDGServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dg/dgRegisterView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String passwordRepeated = request.getParameter("passwordRepeated");
        if (!password.equals(passwordRepeated)) {
            request.setAttribute("errorPassword", "Passwords do not match");

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dg/dgRegisterView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String meansOfTransport = request.getParameter("meansOfTransport");


        Part part = request.getPart("id");
        try {
            Utils.writeImage(mail, part, getServletContext());
        } catch (IllegalArgumentException e2) {
            request.setAttribute("nullImg", "Please choose an image");

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dg/dgRegisterView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        DeliveryGuy deliveryGuy = new DeliveryGuy(mail, name, password, Integer.parseInt(phone), Integer.parseInt(meansOfTransport));

        try {
            DGFunctionality.addModel(deliveryGuy);
        } catch (PersistenceException e) {
            request.setAttribute("errorEmail", "Email not available");

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dg/dgRegisterView.jsp");

            dispatcher.forward(request, response);
            return;
        }
        AppUtils.storeLoginedUser(request.getSession(), deliveryGuy);
        response.sendRedirect(request.getContextPath() + "/dgMenu");
    }
}
