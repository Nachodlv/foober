package org.servlets;

import hibernate.UAFunctionality;
import model.DeliveryGuy;
import model.FranchiseOwner;
import model.UserAccount;
import org.securityfilter.AppUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet({"/login", "/index"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserAccount userAccount = UAFunctionality.getUserAccount(email);

        if (userAccount == null || !userAccount.getPassword().equals(password)) {
            String errorMessage = "Invalid email or Password";

            request.setAttribute("errorMessage", errorMessage);

            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");

            dispatcher.forward(request, response);
            return;
        }
        userAccount = userAccount.getRole().equals("FO") ? (FranchiseOwner) userAccount : (DeliveryGuy) userAccount;

        AppUtils.storeLoginedUser(request.getSession(), userAccount);

        //
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception ignored) {
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(redirectId);
        if (requestUri != null) {
            response.sendRedirect(requestUri);
        } else {
            // Default after successful login
            // redirect to /userInfo page
            if (userAccount.getRole().equals("FO")) {
                response.sendRedirect(request.getContextPath() + "/foMenu");
            } else {
                response.sendRedirect(request.getContextPath() + "/dgMenu");
            }

        }

    }

}