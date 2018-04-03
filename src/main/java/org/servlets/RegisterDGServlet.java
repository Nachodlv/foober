package org.servlets;

import hibernate.DGFunctionality;
import hibernate.FOFunctionality;
import model.DeliveryGuy;
import org.securityfilter.AppUtils;

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
import java.io.InputStream;
import java.nio.file.Paths;

/**
 * @author Gonzalo de Achaval
 */

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
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dgRegisterView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String passwordRepeated = request.getParameter("passwordRepeated");
        if (!password.equals(passwordRepeated)) {
            request.setAttribute("errorPassword", "Passwords do not match");

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dgRegisterView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        Part filePart = request.getPart("id");
        InputStream fileContent = filePart.getInputStream();
        String meansOfTransport = request.getParameter("meansOfTransport");

        DeliveryGuy deliveryGuy = new DeliveryGuy(mail, name, password, Integer.parseInt(phone), convertStreamToString(fileContent), Integer.parseInt(meansOfTransport));


        try {
            DGFunctionality.addModel(deliveryGuy);
        } catch (PersistenceException e) {
            request.setAttribute("errorEmail", "Email not available");

            RequestDispatcher dispatcher
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/dgRegisterView.jsp");

            dispatcher.forward(request, response);
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), deliveryGuy);
        response.sendRedirect(request.getContextPath() + "/dgMenu");
    }

    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
