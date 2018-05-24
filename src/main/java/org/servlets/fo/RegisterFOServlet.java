package org.servlets.fo;

import hibernate.FOFunctionality;
import model.FranchiseOwner;
import org.securityfilter.AppUtils;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/foRegister")
public class RegisterFOServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterFOServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/fo/foRegisterView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String password = request.getParameter("password");
        String passwordRepeated = request.getParameter("passwordRepeated");
        if (!password.equals(passwordRepeated)) {
            response.sendRedirect(request.getContextPath() + "/foRegister?error=Passwords do not match");
            return;
        }

        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String name = request.getParameter("name");
        FranchiseOwner franchiseOwner = new FranchiseOwner(email, name, password, Long.parseLong(phone), address);
        try {
            FOFunctionality.addModel(franchiseOwner);
        } catch (PersistenceException e) {
            response.sendRedirect(request.getContextPath() + "/foRegister?error=Email not available");
            return;
        }

        AppUtils.storeLoginedUser(request.getSession(), franchiseOwner);
        response.sendRedirect(request.getContextPath() + "/editMenu");
    }
}
